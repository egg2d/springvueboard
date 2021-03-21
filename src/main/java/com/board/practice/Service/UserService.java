package com.board.practice.Service;

import com.board.practice.Vo.User;
import org.springframework.stereotype.Service;

public interface UserService {
    // 계정 생성
    public void saveUser(User user);
    // 중복계정 체크
    public int idOverlapCheck(String email);
    // 로그인 체크
    public User loginCheck(User user);

}
