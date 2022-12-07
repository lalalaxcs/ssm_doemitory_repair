package com.xvchushun.dao;

import com.xvchushun.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * 登录映射器
 *
 * @author 1
 * @date 2022/12/06
 */
@Mapper
public interface LoginMapper {
    @Select("select* from user where userAc = #{account}")
    @Results({
            @Result(property = "id",column = "userId",id = true),
            @Result(property = "account",column = "userAc"),
            @Result(property = "password",column = "userPw"),
            @Result(property = "type",column = "userType")
    })
    public User userLogin(String account);
}
