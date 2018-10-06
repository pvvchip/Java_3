package Server;

import java.sql.SQLException;

public interface AuthService {
    String authByLoginAndPassword(String login, String password) throws SQLException, ClassNotFoundException;

    User createOrActivateUser(String login, String password, String nick);

    boolean deactivateUser(String nick);

}
