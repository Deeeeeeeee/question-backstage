package com.seal_de.domain;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by seal_de on 2017/4/4.
 */
@Entity
@Table(name = "user_info", schema = "question", catalog = "")
public class UserInfo {
    private byte[] id;
    private byte[] username;
    private byte[] password;
    private Integer role;

    @Id
    @Column(name = "id", nullable = false)
    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true)
    public byte[] getUsername() {
        return username;
    }

    public void setUsername(byte[] username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true)
    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Basic
    @Column(name = "role", nullable = true)
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (!Arrays.equals(id, userInfo.id)) return false;
        if (!Arrays.equals(username, userInfo.username)) return false;
        if (!Arrays.equals(password, userInfo.password)) return false;
        if (role != null ? !role.equals(userInfo.role) : userInfo.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(id);
        result = 31 * result + Arrays.hashCode(username);
        result = 31 * result + Arrays.hashCode(password);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
