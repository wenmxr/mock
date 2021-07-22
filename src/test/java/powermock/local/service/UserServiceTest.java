package powermock.local.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.common.User;
import powermock.local.dao.UserDao;

import static org.junit.Assert.*;

/**
 * @Author qinwen
 * @Date 2021/7/21 2:19 下午
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class})
public class UserServiceTest {

    @Test
    public void queryUserCount() {
        try {
            UserService userService = new UserService();
            UserDao userDao = PowerMockito.mock(UserDao.class);
            // UserDao 通过cglib动态代理继承子类
            System.out.println(userDao.getClass());
            PowerMockito.whenNew(UserDao.class).withNoArguments().thenReturn(userDao);
            PowerMockito.doReturn(10).when(userDao).getCount();
            int count = userService.queryUserCount();
            assertEquals(10, count);
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    public void saveUser() {
        try {
            UserService userService = new UserService();
            UserDao userDao = PowerMockito.mock(UserDao.class);
            PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
            User user = new User();
            PowerMockito.doNothing().when(userDao).insertUser(user);
            userService.saveUser(user);

            Mockito.verify(userDao, Mockito.times(1)).insertUser(user);
        } catch (Throwable e) {
            fail();
        }
    }
}