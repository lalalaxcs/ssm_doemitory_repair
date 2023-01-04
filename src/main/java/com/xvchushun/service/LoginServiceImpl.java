package com.xvchushun.service;

import com.xvchushun.dao.LoginMapper;
import com.xvchushun.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public User userLogin(String account) {
        System.out.println("调用了方法!");
        return loginMapper.userLogin(account);
    }
}
