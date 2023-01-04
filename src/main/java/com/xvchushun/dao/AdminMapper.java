package com.xvchushun.dao;

import com.xvchushun.domain.Student;
import com.xvchushun.domain.User;
import com.xvchushun.domain.Worker;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("select * from user ")
    @Results(id = "findAllUser", value = {
            @Result(property = "id", column = "userId", id = true),
            @Result(property = "account", column = "userAc"),
            @Result(property = "password", column = "userPw"),
            @Result(property = "type", column = "userType")
    })
    public List<User> selectAllUser();

    @Insert("insert into user(userId,userAc,userPw,userType)values(#{id},#{account},#{password},#{type})")
    public int insertUser(User user);

    @Insert("Insert into student (studentId,studentname)values(#{id},#{name})")
    public int insertStudent(Student student);

    @Insert("insert into worker(workerId,workername)values(#{id},#{name})")
    public int insertWorker(Worker worker);

    @Delete("delete from user where userId = #{id}")
    public int deleteUser(int id);

    @Delete("delete from student where studentId = #{id}")
    public int deleteStudent(int id);

    @Delete("delete from worker where worker_id = #{id}")
    public int deleteWorker(int id);

    @Select("select userType from user where userId= #{id}")
    public int selectUserTypeByUserId(int id);
}
