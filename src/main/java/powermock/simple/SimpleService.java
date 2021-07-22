package powermock.simple;

/**
 * @Author qinwen
 * @Date 2021/7/20 5:18 下午
 */
public class SimpleService {

    private SimpleDao simpleDao;

    public SimpleService(SimpleDao simpleDao) {
        this.simpleDao = simpleDao;
    }

    public void insertUser(User user) {
        // some demo
        simpleDao.insertUser(user);
        // some demo
    }
}

class User { }

