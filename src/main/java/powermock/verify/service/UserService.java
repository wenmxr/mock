package powermock.verify.service;

import powermock.common.User;
import powermock.verify.dao.UserDao;

/**
 * @Author qinwen
 * @Date 2021/7/21 3:34 下午
 */
public class UserService {

    public void saveOrUpdateUser(User user) {
        UserDao userDao = new UserDao();
        if (userDao.getCount() > 0 ) {
            userDao.updateUser(user);
        } else {
            userDao.insertUser(user);
        }
    }
}
