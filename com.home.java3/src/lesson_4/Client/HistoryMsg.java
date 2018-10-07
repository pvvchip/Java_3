package lesson_4.Client;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.*;

public class HistoryMsg {

    public void saveMsg(int nick, JTextArea ta) throws BadLocationException, IOException {
        int str, str2;
        str = ta.getLineStartOffset(0);
        if (ta.getLineCount() > 100) str2 = ta.getLineEndOffset(99);
        else str2 = ta.getLineCount() - 1;
        str2 = ta.getLineEndOffset(str2);
        String st = ta.getText(str, str2 - str);
        System.out.println(st);

        BufferedWriter bw = new BufferedWriter(new FileWriter("msg" + nick + ".txt"));
        bw.write(st);
        bw.close();
    }

    public void openMsg(int nick, JTextArea ta) {
        String st;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("msg" + nick + ".txt"));
            while ((st = br.readLine()) != null) {
                ta.append(st);
                ta.append("\n");
            }
            br.close();
        } catch (IOException e) {
            return;
        }
    }
}
