package powermock.construction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.construction.dao.UserDao;

/**
 * @Author qinwen
 * @Date 2021/7/21 4:02 下午
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class})
public class UserServiceTest {

    @Test
    public void save() throws Exception {
        UserDao userDao = PowerMockito.mock(UserDao.class);
        String username = "qinwen";
        String password = "ben";
        PowerMockito.whenNew(UserDao.class).withArguments(username, password).thenReturn(userDao);

        PowerMockito.doNothing().when(userDao).insertUser();

        UserService userService = new UserService();
        userService.save(username, password);
        // userService.save("xx", password);

        Mockito.verify(userDao).insertUser();
    }
}