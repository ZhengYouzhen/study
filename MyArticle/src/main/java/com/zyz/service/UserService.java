package com.zyz.service;

import com.zyz.bean.User;

/**
 * Created by 娃娃鱼 on 2017/12/12.
 */
public interface UserService extends BaseService {

    User getByNamePwd(String name, String password);

}
