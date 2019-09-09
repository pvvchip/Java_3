package lesson_4.Server;

public class Factory {
    public User getUser(String typeClient) {
        User user = null;
        switch (typeClient) {
            case "client_1":
                user = new UserImp("User_1", "123", "User_1");
                break;
            case "client_2":
                user = new UserImp("User_2", "123", "User_2");
                break;
            case "client_3":
                user = new UserImp("User_3", "123", "User_3");
                break;
            case "client_4":
                user = new UserImp("User_4", "123", "User_4");
                break;
            default:
                break;
        }
        return user;
    }
}
