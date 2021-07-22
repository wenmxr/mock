package powermock.local.service;

import powermock.common.User;
import powermock.local.dao.UserDao;

/**
 * 局部变量
 *
 * @Author qinwen
 * @Date 2021/7/21 10:17 上午
 */
public class UserService {

    public int queryUserCount() {
        UserDao userDao = new UserDao();
        return userDao.getCount();
    }

    public void saveUser(User user) {
        UserDao userDao = new UserDao();
        userDao.insertUser(user);
    }
}
