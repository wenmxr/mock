package powermock.spy.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * @Author qinwen
 * @Date 2021/7/21 5:14 下午
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class})
public class UserServiceTest {

    @Test
    public void testFoo() {

        UserService userService = PowerMockito.mock(UserService.class);
        userService.foo("hello");


        /**
         * spy 会真实的执行
         */
        UserService spy = PowerMockito.spy(new UserService());
        spy.foo("hello");
    }

    @Test
    public void testFoo2() {

        /**
         * spy 符合断言 不会真实执行
         */
        UserService service = PowerMockito.spy(new UserService());
        System.out.println(service);

        String arg = "hello";
        PowerMockito.doNothing().when(service).foo(arg);

        service.foo(arg);
    }

    @Test
    public void testFoo3() {

        /**
         * spy 不符合断言 会真实执行
         */
        UserService service = PowerMockito.spy(new UserService());
        System.out.println(service);

        String arg = "hello";
        PowerMockito.doNothing().when(service).foo(arg);

        service.foo("world");
    }

    @Test
    public void testCheck() throws Exception {
        UserService userService = PowerMockito.spy(new UserService());

        PowerMockito.doReturn(true).when(userService, "checkExist", "miluo");

        // spy 符合断言 不会真实执行 走mock
        assertTrue(userService.exist("miluo"));
        // spy 不符合断言 会真实执行
        assertTrue(userService.exist("milo"));
    }
}