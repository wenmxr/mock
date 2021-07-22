package powermock.mockfinal.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.mockfinal.dao.UserDao;

import static org.junit.Assert.*;

/**
 * @Author qinwen
 * @Date 2021/7/21 3:21 下午
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class, UserDao.class})
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Test
    public void queryUserCountWithMockito() {
        MockitoAnnotations.initMocks(this);
        UserService userService = new UserService(userDao);
        Mockito.when(userDao.getCount()).thenReturn(10);
        int count = userService.queryUserCount();
        assertEquals(10, count);
    }

    @Test
    public void queryUserCountWithPowerMock() {
        UserDao dao = PowerMockito.mock(UserDao.class);
        System.out.println(dao.getClass());
        PowerMockito.when(dao.getCount()).thenReturn(10);
        UserService userService = new UserService(dao);
        int count = userService.queryUserCount();
        assertEquals(10, count);
    }
}