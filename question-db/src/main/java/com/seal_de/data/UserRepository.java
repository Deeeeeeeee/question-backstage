package com.seal_de.data;

import com.seal_de.domain.User;

public interface UserRepository {
    User getByUsername(String username);
}
