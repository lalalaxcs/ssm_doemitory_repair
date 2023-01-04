package com.xvchushun.service;

import com.xvchushun.domain.Student;
import com.xvchushun.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AdminService {
    public List<User> selectAllUser();
    public int insertUser(User user);
    public String UploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;
    public int deleteUser(int id);
    public int selectUserTypeByUserId(int id);
}
