package lesson_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
//    protected static int[] arr = {1, 2, 4, 4, 2, 3, 4, 1, 7};
    protected static int[] arr = {2, 3, 4, 6, 7, 8, 9};
    protected static List<Integer> arr2 = new ArrayList<>();


    protected static List<Integer> operation(int[] i) {
        System.out.println(Arrays.toString(i));
        int n = -1;
        for (int j = 0; j < i.length; j++) {
            if (i[j] == 4) n = j;
        }
        if (n != -1) {
            for (int j = n + 1; j < i.length; j++) {
                arr2.add(arr[j]);
            }
        }
        if (n == -1) {
            System.out.println("Маркера нет");
            throw new RuntimeException();
        }
        return arr2;

    }

    public static void main(String[] args) {
        System.out.println(operation(arr));
    }
}
