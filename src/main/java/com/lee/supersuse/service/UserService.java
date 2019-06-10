package com.lee.supersuse.service;

import com.lee.supersuse.pojo.User;
import com.lee.supersuse.pojo.UserEditView;

public interface UserService {

    void addUser(User user);

    User selectUserByName(String username);

    UserEditView getUserEditViewByUserId(Integer userId);

    boolean checkPassword(Integer userId,String oldPassword,String newPassword);

}
