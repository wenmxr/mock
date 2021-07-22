package powermock.matcher.service;

import powermock.matcher.dao.UserDao;

/**
 * 局部变量
 *
 * @Author qinwen
 * @Date 2021/7/21 10:17 上午
 */
public class UserService {

    public String find(String name) {
        UserDao userDao = new UserDao();
        return userDao.queryByName(name);
    }
}
