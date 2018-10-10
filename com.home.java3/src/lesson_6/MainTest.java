package lesson_6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static lesson_6.Main.operation;

public class MainTest {


    @Test
    public void operationTest() {
        int[] arr = {2, 3, 4, 6, 7, 8, 9};
        List<Integer> arr3 = Arrays.asList(6, 7, 8, 9);
        Assert.assertArrayEquals(new List[]{arr3}, new List[]{operation(arr)});
    }

    @Test
    public void operationTest2() {
        int[] arr = {4, 4, 4, 6, 7, 8, 9};
        List<Integer> arr3 = Arrays.asList(6, 7, 8, 9);
        Assert.assertArrayEquals(new List[]{arr3}, new List[]{operation(arr)});
    }

    @Test
    public void operationTest3() {
        int[] arr = {2, 3, 0, 6, 7, 3, 9};
        List<Integer> arr3 = Arrays.asList(9);
        Assert.assertArrayEquals(new List[]{arr3}, new List[]{operation(arr)});
    }

}
