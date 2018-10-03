package Client;

public interface Controller {
    void sendMessage(String msg);
    void closeConnection();
    void showUI(ClientUI clientUI, int index);
}
