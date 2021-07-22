package mockito.helloworld.service;

import mockito.helloworld.dao.UserDao;
import powermock.common.User;

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
