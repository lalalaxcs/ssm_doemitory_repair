package com.xvchushun.dao;

import com.xvchushun.domain.Repair;
import com.xvchushun.domain.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 学生映射器
 *
 * @author xvchushun
 * @date 2022/12/06
 */
@Mapper
public interface StudentMapper {
    /**
     * 按id查找学生
     *
     * @param id 身份证件
     * @return {@link Student}
     */
    @Select("select * from student where studentId = #{id}")
    @Results( id = "studentmap" ,value = {
            @Result(property = "id",column = "studentId",id = true),
            @Result(property = "name",column = "studentname")
    })
    public Student findStudentById(int id);

    /**
     * 查找学生id
     *
     * @param name 名称
     * @return int
     */
    @Select("select studentId from student where studentname = #{name}")
    @ResultType(java.lang.Integer.class)
    public int findStudentId(String name);

    /**
     * 按学生id查找维修表
     *
     * @param id 身份证件
     * @return {@link List}<{@link Repair}>
     */
    @Select("select repairId,building,room,state,worker_id ,student_id,goods from repair where student_id = #{id}")
    @Results({
            @Result(property = "id",column = "repairId",id = true),
            @Result(property = "building",column = "building"),
            @Result(property = "room",column = "room"),
            @Result(property = "state",column = "state"),
            @Result(property = "goods",column = "goods"),
            @Result(property = "worker",column = "worker_id",one = @One(select = "com.xvchushun.dao.WorkerMapper.findWorkerById")),
            @Result(property = "student",column = "student_id",one = @One(select = "findStudentById"))
    })
    public List<Repair> findRepairByStudentId(int id);

    /**
     * 通过account查找学生id
     *
     * @param account 账户
     * @return int
     */
    @Select("select userId from `user` where userAc = #{account}")
    @ResultType(java.lang.Integer.class)
    public int findStudentIdByAc(String account);

    /**
     * 添加维修表
     *
     * @param repair 修理
     * @return int
     */
    @Insert("insert into repair(building,room,student_id,goods)values(#{repair.building},#{repair.room},#{repair.student.id},#{repair.goods})")
    public int addRepair(@Param("repair") Repair repair );

}
