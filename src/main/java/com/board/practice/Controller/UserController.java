package com.board.practice.Controller;

import com.board.practice.Service.UserService;
import com.board.practice.Util.StatusCode;
import com.board.practice.Util.ValidationCheck;
import com.board.practice.Vo.Message;
import com.board.practice.Vo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

import static com.board.practice.Util.StatusCode.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    // slj4j (로깅)
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    // 회원 가입
    @PostMapping("/join")
    public ResponseEntity<Message> join(@RequestBody User user) {

        // Validation Check
        ValidationCheck userCheck = new ValidationCheck();
        // return Message 객체
        Message message = new Message();
        // 상태코드 객체
        HashMap<String, Enum> statusCode = new HashMap<String, Enum>();
        try {
            logger.info(user.toString());
            //Validation Check
            statusCode = userCheck.userCheck(user);

            // Insert
            if (statusCode.get("status") != null && statusCode.get("status") == SUCCESS) {
                userService.saveUser(user);
                message.setStatus(SUCCESS);
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message.setStatus((StatusCode) statusCode.get("status"));
                message.setMessage(((StatusCode) statusCode.get("status")).getMessage());
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.info("join Exception: " + e);
            return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
        }
    }

    // 계정 체크
    @PostMapping("/idCheck")
    public ResponseEntity<Message> idCheck(@RequestBody User user) {

        // Validation Check
        ValidationCheck userCheck = new ValidationCheck();
        // return Message 객체
        Message message = new Message();
        // 상태코드 객체
        HashMap<String, Enum> statusCode = new HashMap<String, Enum>();
        try {
            int check = userService.idOverlapCheck(user.getId());
            if (check > 0) {
                message.setStatus(ALREADY_REGISTERED);
                return new ResponseEntity<Message>(message, HttpStatus.OK);
            } else {
                message.setStatus(SUCCESS);
                return new ResponseEntity<Message>(message, HttpStatus.OK);
            }

        } catch (Exception e) {
            logger.error("join Exception: " + e);
            return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Message> login(HttpServletRequest request, @RequestBody User user) {

        Message message = new Message();

        try {
            Optional<User> member = Optional.ofNullable(userService.loginCheck(user));
            if (member.isPresent()) {
                HttpSession session = request.getSession();
                session.setAttribute("user", member.get());
                // Message 로그인 성공
                message.setStatus(LOGIN_SUCCESS);

                System.out.println(member.get().toString());
                return new ResponseEntity<Message>(message, HttpStatus.OK);
            } else {
                message.setStatus(LOGIN_FAIL);
                return new ResponseEntity<Message>(message, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Login error :" + e);
            message.setStatus(LOGIN_ERROR);
            return  new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
        }

    }

}