package com.seal_de.data;

import com.seal_de.domain.UserInfo;

public interface UserInfoRepository {
    UserInfo getByUsername(String username);
    boolean save(UserInfo userInfo);
}
