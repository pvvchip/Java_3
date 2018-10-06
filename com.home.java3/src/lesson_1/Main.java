package lesson_1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

//      Пункт 1.
        System.out.println();
        System.out.println("Пункт 1.");
        MyGeneric<Integer> inums = new MyGeneric<>(new Integer[]{10, 20, 30, 40});
        MyGeneric<String> dnums = new MyGeneric<>(new String[]{"Test1", "Test2", "Test3", "Test4"});

        inums.show();
        inums.chElements(0, 2);
        inums.show();

        System.out.println();

        dnums.show();
        dnums.chElements(1, 3);
        dnums.show();

        System.out.println();

//      Пункт 2.
        System.out.println("Пункт 2.");
        ArrayList<Integer> alInt;
        ArrayList<String> alDou;

        alInt = inums.arList();
        System.out.println(Arrays.toString(alInt.toArray()));
        alDou = dnums.arList();
        System.out.println(Arrays.toString(alDou.toArray()));
        System.out.println();

//      Пункт 3.
        System.out.println("Пункт 3.");

        Box<Apple> appleBox = new Box<>(new ArrayList<>());
        Box<Apple> appleBox2 = new Box<>(new ArrayList<>());
        Box<Orange> orangeBox = new Box<>(new ArrayList<>());
        Box<Orange> orangeBox2 = new Box<>(new ArrayList<>());

        for (int i = 0; i < 10; i++) appleBox.getObject().add(new Apple());
        for (int i = 0; i < 10; i++) appleBox2.getObject().add(new Apple());
        for (int i = 0; i < 15; i++) orangeBox.getObject().add(new Orange());
        for (int i = 0; i < 18; i++) orangeBox2.getObject().add(new Orange());

        System.out.println("Box - apple");
        System.out.println(appleBox.getWeight());
        System.out.println(appleBox2.getWeight());
        System.out.println(appleBox.compare(appleBox2));
        System.out.println();

        System.out.println("Box - orange");
        System.out.println(orangeBox.getWeight());
        System.out.println(orangeBox2.getWeight());
        System.out.println(orangeBox.compare(orangeBox2));

        System.out.println("Пересыпаем яблоки");
        appleBox.add(appleBox2);
        System.out.println(appleBox.getWeight());
        System.out.println(appleBox2.getWeight());

        System.out.println("Пересыпаем апельсины");
        orangeBox.add(orangeBox2);
        System.out.println(orangeBox.getWeight());
        System.out.println(orangeBox2.getWeight());
    }
}
