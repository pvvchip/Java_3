package lesson_4.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class ClientController implements Controller {
    private final static String SERVER_ADDR = "localhost";
    private final static int SERVER_PORT = 8189;
    private ClientUI ui;

    private Socket sock;
    private Scanner in;
    private PrintWriter out;
    private int index = new Random().nextInt(3) + 1;

    public ClientController() {
        initConnection();
    }

    public void showUI(ClientUI ui, int index) {
        this.ui = ui;
        ui.showUI();
        sendMessage("/auth login" + this.index + " pass" + this.index);
    }

    private void initConnection() {
        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                while (true) {
                    if (in.hasNext()) {
                        String w = in.nextLine();
                        if (w.startsWith("end session")) {
                            ui.addMessage("Ошибка авторизации.");
                            Thread.sleep(5000);
                            System.exit(0);
                        }
                        ui.addMessage(w);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void sendMessage(String msg) {
        out.println(msg);
    }

    @Override
    public void closeConnection() {
        try {
            sendMessage("/exit");
            sock.close();
            out.close();
            in.close();
        } catch (IOException exc) {
        }
    }

    public int getIndex() {
        return index;
    }
}
