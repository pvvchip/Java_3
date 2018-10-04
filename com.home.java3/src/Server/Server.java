package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private AuthService authService;
    private List<ClientHandler> clients = new ArrayList<>();
    private myBase base;

    public Server(AuthService authService) {
        this.authService = authService;
        try {
            serverSocket = new ServerSocket(8189);
            base = new myBase();
            System.out.println("Сервер запущен, ожидаем подключения...");
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            serverSocket.close();
            base.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        AuthService baseAuthService = new BaseAuthService();
        Server server = new Server(baseAuthService);
        server.start();
    }

    private void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendUnicastMessage(String from, String to, String msg) {
        for (ClientHandler client : clients) {
            if (client.getNick().equals(to)) {
                msg = "Private " + from + ": " + msg;
                client.sendMessage(msg);
                break;
            }
        }
    }

    public void sendBroadcastMessage(String msg) {
        for (ClientHandler client : clients) {
            client.sendMessage(msg);
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNickTaken(String nick) {
        for (ClientHandler client : clients) {
            if (nick.equals(client.getNick()))
                return true;
        }
        return false;
    }

    public void subscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " подключился";
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " отключился";
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.remove(clientHandler);
    }

    public myBase getBase() {
        return base;
    }

    public void unsubscribe(Runnable runnable) {
    }
}
