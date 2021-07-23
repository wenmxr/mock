package powermock.matcher.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.matcher.dao.UserDao;

/**
 * Matcher & Answer
 *
 * @Author qinwen
 * @Date 2021/7/21 4:14 下午
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class})
public class UserServiceTest {

    @Test
    public void testFind() throws Exception {

        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

        UserService service = new UserService();

        PowerMockito.when(userDao.queryByName("miluo")).thenReturn("hexiao");
        String result = service.find("miluo");
        Assert.assertEquals("hexiao", result);

        // 没有匹配到
        // PowerMockito.when(userDao.queryByName("miluo")).thenReturn("hexiao");
        // String result2 = service.find("qinwen");
        // Assert.assertEquals("hexiao", result2);

        PowerMockito.when(userDao.queryByName("qinwen")).thenReturn("hexiao");
        String result3 = service.find("qinwen");
        Assert.assertEquals("hexiao", result3);

        PowerMockito.when(userDao.queryByName("cangfeng")).thenReturn("hexiao");
        String result4 = service.find("cangfeng");
        Assert.assertEquals("hexiao", result4);


    }

    @Test
    public void testFindWithMatcher() throws Exception {

        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

        UserService service = new UserService();

        PowerMockito.when(userDao.queryByName(Matchers.argThat(new MyArgumentMatcher()))).thenReturn("hexiao");

        Assert.assertEquals("hexiao", service.find("miluo"));
        Assert.assertEquals("hexiao", service.find("qinwen"));
        Assert.assertEquals("hexiao", service.find("ahuang"));
        Assert.assertEquals("hexiao", service.find("cangfeng"));
        Assert.assertEquals("hexiao", service.find("chuanhua"));
        // Assert.assertEquals("hexiao", service.find("hexiao"));

    }

    static class MyArgumentMatcher extends ArgumentMatcher<String> {

        @Override
        public boolean matches(Object o) {
            String arg = (String) o;
            switch (arg) {
                case "miluo":
                case "qinwen":
                case "ahuang":
                case "cangfeng":
                case "chuanhua":
                    return true;
                default:
                    return false;
            }
        }
    }

    @Test
    public void testFindWithAnswer() {

        try {
            UserDao userDao = PowerMockito.mock(UserDao.class);
            PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

            PowerMockito.when(userDao.queryByName(Mockito.anyString())).then(invocationOnMock -> {
                String arg = (String) invocationOnMock.getArguments()[0];
                switch (arg) {
                    case "hexiao":
                        return "I am hexiao";
                    case "miluo":
                        return "I am miluo";
                    default:
                        throw new RuntimeException("Not support " + arg);
                }
            });

            UserService service = new UserService();

            Assert.assertEquals("I am hexiao", service.find("hexiao"));
            Assert.assertEquals("I am miluo", service.find("miluo"));
            service.find("qinwen");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof RuntimeException);
        }


    }


}