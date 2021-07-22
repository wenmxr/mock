package powermock.simple;

/**
 * @Author qinwen
 * @Date 2021/7/20 5:21 下午
 */
public class SimpleDao {
    public void insertUser(User user) {
        throw new RuntimeException("database is not connection...");
    }
}

class MockSimpleDao extends SimpleDao {

    @Override
    public void insertUser(User user) {
        System.out.println("mock successful");
    }
}
