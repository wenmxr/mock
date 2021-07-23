package powermock.mockstatic.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.common.User;
import powermock.mockstatic.dao.UserDao;

import static org.junit.Assert.assertEquals;

/**
 * PowerMock - static
 *
 * @Author qinwen
 * @Date 2021/7/21 2:42 下午
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserDao.class})
public class UserServiceTest {

    @Test
    public void queryUserCount() {
        PowerMockito.mockStatic(UserDao.class);
        PowerMockito.when(UserDao.getCount()).thenReturn(10);
        UserService userService = new UserService();
        int count = userService.queryUserCount();
        assertEquals(10, count);
    }

    @Test
    public void saveUser() {
        PowerMockito.mockStatic(UserDao.class);
        User user = new User();
        PowerMockito.doNothing().when(UserDao.class);
        UserService userService = new UserService();
        userService.saveUser(user);

        PowerMockito.verifyStatic();

    }
}