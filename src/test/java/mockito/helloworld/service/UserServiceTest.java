package mockito.helloworld.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import powermock.common.User;
import powermock.helloworld.dao.UserDao;
import powermock.helloworld.service.UserService;

/**
 * @Author qinwen
 * @Date 2021/7/22 2:25 下午
 */
@RunWith(MockitoJUnitRunner.class)
// 等价于 MockitoAnnotations.initMocks(this);
public class UserServiceTest {

    // @Mock: 创建一个Mock.
    @Mock
    private UserDao userDao;

    @Mock
    private User user;

    // @InjectMocks: 创建一个实例，其余用@Mock（或@Spy）注解创建的mock将被注入到用该实例中
    @InjectMocks
    private UserService userService;

    @Before
    public void before() {
        // 注意：必须使用@RunWith(MockitoJUnitRunner.class) 或 Mockito.initMocks(this)进行mocks的初始化和注入。
        // MockitoAnnotations.initMocks(this);
        // 等价于 @RunWith(MockitoJUnitRunner.class)
    }

    @Test
    public void testQueryUserCount() {
        Mockito.when(userDao.getCount()).thenReturn(10).thenReturn(1);
        Assert.assertEquals(10, userService.queryUserCount());
        Assert.assertEquals(10, userService.queryUserCount());
    }

    @Test
    public void testQueryUserCount2() {
        Mockito.doReturn(12).when(userDao).getCount();
        Assert.assertEquals(12, userService.queryUserCount());

    }

    @Test
    public void testSave() {
        Mockito.doNothing().when(userDao).insertUser(user);

        userService.saveUser(user);

        // Mockito.verify(userDao).insertUser(user);
        // Mockito.verify(userDao, Mockito.times(2)).insertUser(user);
        Mockito.verify(userDao, Mockito.timeout(10).times(2)).insertUser(user);
    }


}