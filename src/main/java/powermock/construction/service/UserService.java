package powermock.construction.service;

import powermock.construction.dao.UserDao;

/**
 * @Author qinwen
 * @Date 2021/7/21 10:17 上午
 */
public class UserService {
    public void save(String username, String password) {
        UserDao userDao = new UserDao(username, password);
        userDao.insertUser();
    }
}
