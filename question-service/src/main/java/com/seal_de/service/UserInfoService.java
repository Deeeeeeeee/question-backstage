package com.seal_de.service;

import com.seal_de.domain.UserInfo;

public interface UserInfoService {
    boolean saveUserInfo(UserInfo userInfo);
    UserInfo getByUsername(String username);
}
