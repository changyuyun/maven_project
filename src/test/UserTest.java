import com.ityun.travel.dao.impl.UserDaoImpl;
import com.ityun.travel.domain.User;
import org.junit.Test;

public class UserTest {

    @Test
    public void ttt() {
        User user = new User();
        user.setUsername("ccc2");
        user.setPassword("123223");
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.save(user);
    }
}
