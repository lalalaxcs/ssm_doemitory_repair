package com.xvchushun.dao;

import com.xvchushun.domain.Repair;
import com.xvchushun.domain.Worker;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 工人映射器
 *
 * @author 1
 * @date 2022/12/06
 */
@Mapper
public interface WorkerMapper {
    /*
        通过工人id查询工人信息
     */
    @Select("select * from worker where workerId = #{id}")
    @Results({
            @Result(property = "id",column = "workerId",id = true),
            @Result(property = "name",column = "workerName")
    })
    public Worker findWorkerById(int id);

    /**
     * 按帐户获取id
     *
     * @param account 账户
     * @return int
     */
    @Select(("select userId from `user` where userAc = #{account}"))
    public int getIdByAccount(String account);

    /**
     * 按工人id获取维修
     *
     * @param id 身份证件
     * @return {@link List}<{@link Repair}>
     */
    @Select("select repairId,building,room,state,student_id,goods from repair where worker_id = #{id}")
    @Results(id = "workerReaire",value = {
            @Result(property = "id",column = "repairId",id = true),
            @Result(property = "building",column = "building"),
            @Result(property = "room",column = "room"),
            @Result(property = "state",column = "state"),
            @Result(property = "goods",column = "goods"),
            @Result(property = "worker",column = "worker_id",one = @One(select = "findWorkerById")),
            @Result(property = "student",column = "student_id",one = @One(select = "com.xvchushun.dao.StudentMapper.findStudentById"))
    })
    public List<Repair> getRepairsByWorkerId(int id);

    /**
     * 完成维修订单
     *
     * @param id 身份证件
     * @return int
     */
    @Update("update repair set state = 2 where repairId = #{id}")
    public int finishRepair(int id);

    /**
     * 放弃维修单
     *
     * @param id 身份证件
     * @return int
     */
    @Update("update repair set state = 0,worker_id = null where repairId = #{id}")
    public int abandonRepairOrder(int id);

    /**
     * 查找所有未接受维修订单
     *
     * @return {@link List}<{@link Repair}>
     */
    @Select("select repairId,building,room,state,student_id,goods from repair where state = 0")
    @ResultMap(value ="workerReaire" )
    public List<Repair> findAllRepairOrdersWithoutAccept();

    /**
     * 接受维修订单
     *
     * @param wId w标识
     * @param rId r编号
     * @return int
     */
    @Update("update repair set state =1,worker_id = #{w_id} where repairId = #{r_id}")
    public int acceptRepairOrder(@Param("w_id") int wId,@Param("r_id") int rId);
}
