package task_1;


//Почемуто не работает как надо
//


public class MainTask {
    private boolean flagA = true,
            flagB = false,
            flagC = false;
    private int status = 1;
    Msg msg = new Msg();

    public static void main(String[] args) {
        MainTask taskA = new MainTask();
        MainTask taskB = new MainTask();
        MainTask taskC = new MainTask();


        new Thread(() -> taskA.msgPrint('A')).start();
        new Thread(() -> taskB.msgPrint('B')).start();
        new Thread(() -> taskC.msgPrint('C')).start();
    }

    private void msgPrint(char ch) {
        switch (status) {
            case 1:
                if (ch == 'A') {
                    status = 2;
                    msg.printMsg(ch);
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                if (ch == 'B')   {
                    status = 3;
                    msgPrint(ch);
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 3:
                if (ch == 'C') {
                    status = 1;
                    msgPrint(ch);
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
       notify();
    }
}


