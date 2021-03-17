package com.board.practice.Util;

import com.board.practice.Vo.User;

import java.util.HashMap;

import static com.board.practice.Util.StatusCode.*;

public class ValidationCheck {


    public HashMap<String, Enum> userCheck(User user) {

        HashMap<String, Enum> returnMap = new HashMap<String, Enum>();

        if(user.getId() == null || "".equals(user.getId())) {
            returnMap.put("status",NOT_ID);
            return  returnMap;
        }

        // 생년월일 체크
        if(user.getBirthday() == null || "".equals(user.getBirthday())) {
            returnMap.put("status",NOT_BIRTHDAY);
            return  returnMap;
        }
        // Email 체크
        if(user.getEmail() == null || "".equals(user.getEmail())) {
            returnMap.put("status",NOT_EMAIL);
            return  returnMap;
        }
        // password 체크
        if(user.getPassword() == null || "".equals(user.getPassword())) {
            returnMap.put("status",NOT_PASSWORD);
            return  returnMap;
        }
        // 이름 체크
        if(user.getName() == null || "".equals(user.getName())) {
            returnMap.put("status", NOT_USERNAME);
            return  returnMap;
        }

        returnMap.put("status",SUCCESS);
        return  returnMap;

    }







}
