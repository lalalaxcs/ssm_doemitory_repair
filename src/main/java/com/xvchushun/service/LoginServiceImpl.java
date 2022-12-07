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
        return loginMapper.userLogin(account);
    }
}
