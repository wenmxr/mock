package powermock.mockstatic.service;

import powermock.common.User;
import powermock.mockstatic.dao.UserDao;

/**
 * 局部变量
 *
 * @Author qinwen
 * @Date 2021/7/21 10:17 上午
 */
public class UserService {

    public int queryUserCount() {
        return UserDao.getCount();
    }

    public void saveUser(User user) {
        UserDao.insertUser(user);
    }
}
