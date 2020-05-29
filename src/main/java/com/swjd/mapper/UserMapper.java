package com.swjd.mapper;

import com.swjd.bean.User;

public interface UserMapper {

    public abstract User findUserByUNamePwd(User user);
    //根据登录名,查询id
    public abstract int findUserId(String userName);
}
