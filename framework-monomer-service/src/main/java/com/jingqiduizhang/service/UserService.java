package com.jingqiduizhang.service;

import com.jingqiduizhang.pojo.Users;
import com.jingqiduizhang.pojo.bo.UserBO;
import com.jingqiduizhang.utils.PagedGridResult;

public interface UserService {

    /**
     * 判断用户名是否存在
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 判断用户名是否存在
     */
    public Users createUser(UserBO userBO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     */
    public Users queryUserForLogin(String username, String password);

    /**
     * 测试 查询用户列表
     * @return
     */
    public PagedGridResult queryPagedUsersTest(String userName,Integer page, Integer pageSize);
}
