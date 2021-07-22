package powermock.helloworld.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import powermock.common.User;
import powermock.helloworld.dao.UserDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @Author qinwen
 * @Date 2021/7/21 10:25 上午
 */
public class UserServiceTest {

    private UserService userService;

    @Before
    public void before() {
        userService = new UserService(new UserDao());
    }

    @Mock
    private UserDao userDao;

    /**
     * do...when...then
     * when...then
     */

    @Test
    public void queryUserCountWithPowerMock() {
        // 不支持注解
        UserDao dao = PowerMockito.mock(UserDao.class);
        PowerMockito.when(dao.getCount()).thenReturn(10);
        // PowerMockito.doReturn(10).when(dao).getCount();

        UserService userService = new UserService(dao);
        int count = userService.queryUserCount();
        assertEquals(10, count);
    }

    /**
     * void test
     */
    @Test
    public void querySaveUserWithPowerMock() {
        UserDao dao = PowerMockito.mock(UserDao.class);
        User user = new User();
        PowerMockito.doNothing().when(dao).insertUser(user);

        UserService userService = new UserService(dao);
        userService.saveUser(user);

        Mockito.verify(dao).insertUser(user);
    }

    @Test
    public void queryUserCountWithMockito() {
        // 支持注解 @Mock
        MockitoAnnotations.initMocks(this);

        UserService service = new UserService(userDao);
        Mockito.when(userDao.getCount()).thenReturn(10);

        int count = service.queryUserCount();
        assertEquals(10, count);
    }

    @Test
    public void queryUserCountWithJunit() {
        try {
            int count = userService.queryUserCount();
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);

        }
    }

    @Test
    public void saveUserWithJunit() {
        try {
            userService.saveUser(new User());
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }
}