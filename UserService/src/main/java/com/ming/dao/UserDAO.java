package com.ming.dao;

import com.ming.model.UserDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDAO {

    @Options(useGeneratedKeys=true, keyProperty="uId",keyColumn = "u_id")
    @Insert("insert into user(user_name, amount) values(#{userName}, #{amount})")
    int insert(UserDTO dto);

    @Select("select * from user")
    List<UserDTO> findAll();

    @Update("update user set amount = amount - #{amount} where u_id = #{userId}")
    int pay(@Param("userId") Long userId, @Param("amount") Long amount);
}
