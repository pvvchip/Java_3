package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler {
    //    private Socket socket;
    private Server server;
    private PrintWriter pw;
    private Scanner sc;
    private String nick;
    public ExecutorService service;

    public ClientHandler(Socket socket, Server server) {
//        this.socket = socket;
        this.server = server;


        try {
            try {
                sc = new Scanner(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                pw = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ExecutorService executorService = Executors.newFixedThreadPool(100);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        auth();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    System.out.println(nick + " handler waiting for new massages");
                    while (socket.isConnected()) {
                        String s = sc.nextLine();
                        String toNick;
                        String[] commands = s.split(" ");
                        if (s != null && s.equals("/exit"))
                            server.unsubscribe(this);
                        if (s != null && commands[0].equals("/w")) {
                            System.out.println("Privat from " + nick + s);
                            if (commands.length >= 3) {
                                toNick = commands[1];
                                commands[0] = "";
                                commands[1] = "";
                                s = String.join(" ", commands);
                                server.sendUnicastMessage(nick, toNick, s);
                            }
                            continue;
                        }
                        if (s != null && !s.isEmpty())
                            server.sendBroadcastMessage(nick + " : " + s);
                    }
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Wait for command: "/auth login1 pass1"
     */
    private void auth() throws SQLException, ClassNotFoundException {
        while (true) {
            if (!sc.hasNextLine()) continue;
            String s = sc.nextLine();
            if (s.startsWith("/auth")) {
                String[] commands = s.split(" ");// /auth login1 pass1
                if (commands.length >= 3) {
                    String login = commands[1];
                    String password = commands[2];
                    System.out.println("Try to login with " + login + " and " + password);
                    String nick = server.getAuthService()
                            .authByLoginAndPassword(login, password);
                    if (nick == null) {
                        String msg = "Invalid login or password";
                        System.out.println(msg);
                        pw.println(msg);
                        pw.println("end session");
                    } else if (server.isNickTaken(nick)) {
                        String msg = "Nick " + nick + " already taken!";
                        System.out.println(msg);
                        pw.println(msg);
                        pw.println("end session");
                    } else {
                        this.nick = nick;
                        String msg = "Auth ok!";
                        System.out.println(msg);
                        pw.println(msg);
                        server.subscribe(this);
                        break;
                    }
                }
            } else {
                pw.println("Invalid command!");
            }
        }
    }

    public void sendMessage(String msg) {
        pw.println(msg);
    }

    public String getNick() {
        return nick;
    }
}
