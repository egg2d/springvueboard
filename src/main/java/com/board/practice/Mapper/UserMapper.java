package com.board.practice.Mapper;

import com.board.practice.Vo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void saveUser(User user);

    int idOverlapCheck(String email);

}
