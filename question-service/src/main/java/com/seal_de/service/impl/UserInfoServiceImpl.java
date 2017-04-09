package com.seal_de.service.impl;

import com.seal_de.data.UserInfoRepository;
import com.seal_de.domain.UserInfo;
import com.seal_de.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository){
        this.userInfoRepository = userInfoRepository;
    }

    @Transactional
    public boolean saveUserInfo(UserInfo userInfo){
        if(userInfo.getId() !=null)
            userInfo.setId(null);
        return userInfoRepository.save(userInfo);
    }

    public UserInfo getByUsername(String username) {
        return userInfoRepository.getByUsername(username);
    }

}
