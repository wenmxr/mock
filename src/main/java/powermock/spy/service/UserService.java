package powermock.spy.service;

/**
 * @Author qinwen
 * @Date 2021/7/21 10:17 上午
 */
public class UserService {

   public void foo(String arg) {
       log();
   }

   private void log() {
       System.out.println("I am console log.");
   }

   public boolean exist(String username) {
       return checkExist(username);
   }

    private boolean checkExist(String username) {
        throw new UnsupportedOperationException();
    }
}
