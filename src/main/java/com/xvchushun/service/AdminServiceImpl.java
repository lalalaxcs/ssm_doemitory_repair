package com.xvchushun.service;

import com.xvchushun.dao.AdminMapper;
import com.xvchushun.domain.Student;
import com.xvchushun.domain.User;
import com.xvchushun.domain.Worker;
import com.xvchushun.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public List<User> selectAllUser() {
        return adminMapper.selectAllUser();
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public String UploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)  request;
        MultipartFile file = multipartHttpServletRequest.getFile("excelfiles");
        if(file.isEmpty()){
            try {
                throw new Exception("文件不存在！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        InputStream in =null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List<Object>> listob = null;
        try {
            listob = new ExcelUtil().getBankListByExcel(in,file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<listob.size();i++){
            List<Object> lo = listob.get(i);
            User vo = new User();
            Student st = new Student();
            Worker wk = new Worker();
            vo.setId(Integer.valueOf(String.valueOf(lo.get(0))));
            vo.setAccount(String.valueOf(lo.get(1)));
            vo.setPassword(String.valueOf(lo.get(2)));
            vo.setType(Integer.valueOf(String.valueOf(lo.get(3))));
            System.out.println("从excel中读取的实体类对象："+ vo);
            adminMapper.insertUser(vo);
            if(Integer.valueOf(String.valueOf(lo.get(3))) == 3){
                st.setId(Integer.valueOf(String.valueOf(lo.get(0))));
                st.setName(String.valueOf(lo.get(4)));
                System.out.println("从excel中读取的实体类对象："+ st);
                adminMapper.insertStudent(st);
            }else if(Integer.valueOf(String.valueOf(lo.get(3))) == 1){
                wk.setId(Integer.valueOf(String.valueOf(lo.get(0))));
                wk.setName(String.valueOf(lo.get(4)));
                System.out.println("从excel中读取的实体类对象："+ wk);
                adminMapper.insertWorker(wk);
            }
        }
        return "导入成功";
    }

    @Override
    public int deleteUser(int id) {
        int type = adminMapper.selectUserTypeByUserId(id);
        adminMapper.deleteUser(id);
        if(type == 3){
            return adminMapper.deleteStudent(id);
        }else{
            return adminMapper.deleteWorker(id);
        }
    }

    @Override
    public int selectUserTypeByUserId(int id) {
        return adminMapper.selectUserTypeByUserId(id);

    }
}
