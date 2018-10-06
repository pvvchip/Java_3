package Client;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Client extends JFrame implements ClientUI {
    private JTextField jtf;
    private JTextArea jta;
    private Controller controller;
    private int index;

    public Client(Controller controller, int index) {

        this.controller = controller;
        this.index = index;

        setBounds(600, 300, 500, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jta);
        add(jsp, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbSend = new JButton("SEND");
        bottomPanel.add(jbSend, BorderLayout.EAST);
        jtf = new JTextField();
        bottomPanel.add(jtf, BorderLayout.CENTER);

        jbSend.addActionListener(e -> sendMsg());
        jtf.addActionListener(e -> sendMsg());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    new HistoryMsg().saveMsg(index, jta);
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                super.windowClosing(e);
                controller.closeConnection();
            }
        });
    }

    private void sendMsg() {
        if (!jtf.getText().trim().isEmpty()) {
            controller.sendMessage(jtf.getText());
            jtf.setText("");
            jtf.grabFocus();
        }
    }

    @Override
    public void addMessage(String w) {
        jta.append(w);
        jta.append("\n");
    }

    @Override
    public void showUI() {
        setVisible(true);
        new HistoryMsg().openMsg(index, jta);
    }
}
