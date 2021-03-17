package com.board.practice.Service;

import com.board.practice.Vo.User;
import org.springframework.stereotype.Service;

public interface UserService {

    public void saveUser(User user);

    public int idOverlapCheck(String email);

}
