package lesson_5;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static final int CARS_COUNT = 4;
    public static CountDownLatch ctlStart = new CountDownLatch(CARS_COUNT);
    public static Semaphore smpTunnel = new Semaphore(CARS_COUNT / 2);


    public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        Thread[] thr = new Thread[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
//            new Thread(cars[i]).start();
            thr[i] = new Thread(cars[i]);
        }

        for (Thread i: thr
             ) {
            i.start();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        for (Thread i: thr
        ) {
            try {
                i.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
