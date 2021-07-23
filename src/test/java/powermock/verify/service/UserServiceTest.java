package powermock.verify.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.common.User;
import powermock.verify.dao.UserDao;

import static org.junit.Assert.*;

/**
 * PowerMock - verify
 *
 * @Author qinwen
 * @Date 2021/7/21 3:40 下午
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class})
public class UserServiceTest {

    @Test
    public void saveOrUpdateUser() throws Exception {
        User user = PowerMockito.mock(User.class);
        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

        PowerMockito.when(userDao.getCount()).thenReturn(0);

        UserService userService = new UserService();
        userService.saveOrUpdateUser(user);

        Mockito.verify(userDao).insertUser(user);
        Mockito.verify(userDao, Mockito.never()).updateUser(user);
    }

    @Test
    public void saveOrUpdateUser2() throws Exception {
        User user = PowerMockito.mock(User.class);
        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

        PowerMockito.when(userDao.getCount()).thenReturn(1);

        UserService userService = new UserService();
        userService.saveOrUpdateUser(user);

        Mockito.verify(userDao).updateUser(user);
        Mockito.verify(userDao, Mockito.never()).insertUser(user);
    }
}