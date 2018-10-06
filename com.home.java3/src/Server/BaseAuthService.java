package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseAuthService implements AuthService {
    private Map<String, User> users = new HashMap<>();

    public BaseAuthService() {
//        users.put("nick1", new User("login1", "pass1", "nick1"));
//        users.put("nick2", new User("login2", "pass2", "nick2"));
//        users.put("nick3", new User("login3", "pass3", "nick3"));
    }

    @Override
    public String authByLoginAndPassword(String login, String password) throws SQLException, ClassNotFoundException {

        myBase base = new myBase();
        ResultSet rs;
        String pas, nick;
        int active;
        String sql = "SELECT * FROM Users where Login = '" + login + "'";
        System.out.println(sql);
        rs = base.getStat().executeQuery(sql);
        if (rs.next()) {
            pas = rs.getString(2);
            System.out.println("Модуль проверки "+pas);

            nick = rs.getString(3);
            System.out.println("Модуль проверки "+nick);

            active = rs.getInt(4);
        } else {
            System.out.println("Ошибка запроса к базе.");
            return null;
        }

        if (password.equals(pas)
                && (active == 1) )
            return nick;
        else return null;

    }

    @Override
    public User createOrActivateUser(String login, String password, String nick) {



        User user = new User(login, password, nick);
        if (users.containsKey(nick)) {
            users.get(nick).setActive(true);
            System.out.println("User with nick " + nick + "already exist");
        } else {
            users.put(nick, user);
            persist();
        }
        return user;
    }

    private void persist() {
        //do some logic...
//        new File("users.txt");
    }

    @Override
    public boolean deactivateUser(String nick) {
        User user = users.get(nick);
        if (user != null) {
            user.setActive(false);
            return true;
        }
        return false;
    }
}
