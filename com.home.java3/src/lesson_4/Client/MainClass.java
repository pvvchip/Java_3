package lesson_4.Client;

public class MainClass {

    public static void main(String[] args) {
        Controller controller = new ClientController();
        ClientUI clientUI = new Client(controller, ((ClientController) controller).getIndex());
        controller.showUI(clientUI, ((ClientController) controller).getIndex());
    }
}
