package com.board.practice.Service;

import com.board.practice.Mapper.UserMapper;
import com.board.practice.Vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public int idOverlapCheck(String email) {
        return userMapper.idOverlapCheck(email);
    }

    @Override
    public User loginCheck(User user) {
        return userMapper.loginCheck(user);
    }
}
