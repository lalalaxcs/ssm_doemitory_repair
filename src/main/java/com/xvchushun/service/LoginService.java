package com.xvchushun.service;

import com.github.pagehelper.PageInfo;
import com.xvchushun.domain.User;

public interface LoginService {
    public User userLogin(String account);
}
