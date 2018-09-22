import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

//      Пункт 1.
        MyGeneric<Integer> inums = new MyGeneric<>(new Integer[]{10, 20, 30, 40});
        MyGeneric<String> dnums = new MyGeneric<>(new String[]{"Test1", "Test2", "Test3", "Test4"});

        System.out.println();

        inums.show();
        inums.chElements(0, 2);
        inums.show();

        System.out.println();

        dnums.show();
        dnums.chElements(1, 3);
        dnums.show();

        System.out.println();

//      Пункт 2.
        ArrayList<Integer> alInt;
        ArrayList<String> alDou;

        alInt = inums.arList();
        System.out.println(Arrays.toString(alInt.toArray()));
        alDou = dnums.arList();
        System.out.println(Arrays.toString(alDou.toArray()));

//      Пункт 3.

    }

}
