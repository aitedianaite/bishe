package com.tianruan.bishe.service.serviceImpl;

import com.tianruan.bishe.mapper.LoginMapper;
import com.tianruan.bishe.pojo.Users;
import com.tianruan.bishe.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;

    @Override
    public Integer register(Users users) {
        return 1;
    }
}
