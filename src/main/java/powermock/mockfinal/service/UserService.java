package powermock.mockfinal.service;

import powermock.common.User;
import powermock.mockfinal.dao.UserDao;

/**
 * @Author qinwen
 * @Date 2021/7/21 10:17 上午
 */
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int queryUserCount() {
        return userDao.getCount();
    }

    public void saveUser(User user) {
        userDao.insertUser(user);
    }
}
