package lesson_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    //    protected static int[] arr = {1, 2, 4, 4, 2, 3, 4, 1, 7};
    protected static int[] arr = {2, 3, 4, 6, 7, 8, 9};
    protected static int[] arr2 = {1, 4, 1, 4, 4, 4, 1, 2, 1};

    protected static List<Integer> arrList = new ArrayList<>();


    protected static List<Integer> operation(int[] i) {
        System.out.println("Test1: " + Arrays.toString(i));
        int n = -1;
        for (int j = 0; j < i.length; j++) {
            if (i[j] == 4) n = j;
        }
        if (n != -1) {
            for (int j = n + 1; j < i.length; j++) {
                arrList.add(arr[j]);
            }
        }
        if (n == -1) {
            System.out.println("Маркера нет");
            throw new RuntimeException();
        }
        return arrList;
    }

    protected static boolean operation2(int[] arr) {
        System.out.println("Test2: " + Arrays.toString(arr));
        for (int i: arr) {
            if (!(i == 1 || i == 4)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Test1: " + operation(arr));
        System.out.println("Test2: " + operation2(arr2));
    }
}
