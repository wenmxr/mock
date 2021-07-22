package powermock.construction.dao;

import powermock.common.User;

/**
 * @Author qinwen
 * @Date 2021/7/21 10:18 上午
 */
public class UserDao {
    private String username;
    private String password;

    public UserDao(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void insertUser() {
        throw new UnsupportedOperationException();
    }
}
