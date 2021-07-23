package powermock.spy.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * spy
 *
 * Mock对象是能调用模拟方法，调用不了它真实的方法，但是spy() 或者@spy 可以监视一个真实的对象，
 * 对它进行方法调用时它将调用真实的方法，同时也可以设定这个对象的方法让它返回我们的期望值。同时，我们也可以用verify进行验证。
 *
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

    @Test
    public void testSpy() {
        //  当需要整体Mock，只有少部分方法执行真正部分时，选用这种方式
        A mockA = Mockito.mock(A.class);
        Mockito.doCallRealMethod().when(mockA).goHome();
        // 当需要整体执行真正部分，只有少部分方法执行mock，选用这种方式
        A spyA = Mockito.spy(new A());
        Mockito.when(spyA.goHome()).thenReturn(false);
    }
}
class A {
    public boolean goHome() {
        System.out.println("I say go go go!!");
        return true;
    }
}