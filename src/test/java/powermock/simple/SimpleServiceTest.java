package powermock.simple;

import org.junit.Before;
import org.junit.Test;

/**
 * Mock εη
 *
 * @Author qinwen
 * @Date 2021/7/20 5:45 δΈε
 */
public class SimpleServiceTest {

    private SimpleDao simpleDao;

    @Before
    public void before() {
        simpleDao = new MockSimpleDao();
    }

    @Test
    public void test() {
        SimpleService simpleService = new SimpleService(simpleDao);
        simpleService.insertUser(new User());
    }
}
