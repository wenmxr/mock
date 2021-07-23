package mockito.simple;

import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;

/**
 * Mockito 讲解
 *
 * @Author qinwen
 * @Date 2021/7/23 10:14 上午
 */
public class ExampleTest {


    public void test() {
        // 1、模拟HttpServletRequest对象，不需要依赖web容器，模拟获得请求参数
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("foo")).thenReturn("boo");
        // 注意:mock()是Mockito的静态方法，可以用@mock注解替换
        // private @mock HttpServletRequest request

        Person person = mock(Person.class);
        // 2、第一次调用返回"xiaoming"，第二次调用返回"xiaohong"
        when(person.getName()).thenReturn("xiaoming").thenReturn("xiaohong");
        when(person.getName()).thenReturn("xiaoming", "xiaohong");
        when(person.getName()).thenReturn("xiaoming");
        when(person.getName()).thenReturn("xiaohong");

        // 3、mockito模拟测试无返回值的方法
        // Person person =mock(Person.class);
        doNothing().when(person).remove();

        // 4、mockito还能对被测试的方法强行抛出异常
        // Person person =mock(Person.class);
        doThrow(new RuntimeException()).when(person).remove();
        when(person.next()).thenThrow(new RuntimeException());

        // 5、//UserAppService用于参数匹配器的demo
        // 参数匹配器
        UserApp app = new UserApp();
        app.setAppKey("q1w2e3r4t5y6u7i8o9p0");
        app.setAppSecret("q1w2e3r4t5y6u7i8o9p0");
        when(app.getAppSecretByAppKey(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object argument) {
                String arg = (String) argument;
                if (arg.equals("1234567890") || arg.equals("q1w2e3r4t5y6u7i8o9p0")) {
                    return true;
                } else {
                    throw new RuntimeException();
                }
            }
        }))).thenReturn(app);

        // 6、Answer接口模拟根据参数返回不同结果
        when(app.getAppSecretByAppKey(anyString())).thenAnswer(
                (InvocationOnMock invocationOnMock) -> {
                    String arg = (String) invocationOnMock.getArguments()[0];
                    if (null == arg) {
                        return null;
                    } else if (arg.equals("q1w2e3r4t5y6u7i8o9p0")) {
                        UserApp a = new UserApp();
                        a.setAppKey("q1w2e3r4t5y6u7i8o9p0");
                        a.setAppSecret("q1w2e3r4t5y6u7i8o9p0");
                        return a;
                    } else {
                        return null;
                    }

                });

        // 7、Mock对象是能调用模拟方法，调用不了它真实的方法，但是spy() 或者@spy
        // 可以监视一个真实的对象，对它进行方法调用时它将调用真实的方法，同时也可以设定这个对象的方法让它返回我们的期望值。
        // 同时，我们也可以用verify进行验证。
        //  当需要整体Mock，只有少部分方法执行真正部分时，选用这种方式
        Abc mockA = Mockito.mock(Abc.class);
        Mockito.doCallRealMethod().when(mockA).goHome();
        // 当需要整体执行真正部分，只有少部分方法执行mock，选用这种方式
        Abc spyA = Mockito.spy(new Abc());
        Mockito.when(spyA.goHome()).thenReturn(false);

    }
}

class Abc {
    public boolean goHome() {
        System.out.println("I say go go go!!");
        return true;
    }
}

class UserApp {
    private String appKey;
    private String appSecret;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public UserApp getAppSecretByAppKey(String key) {
        return new UserApp();
    }
}


class Person {
    public String getName() {
        return name;
    }

    private String name;

    public void remove() {
    }

    public boolean next() {
        return true;
    }
}