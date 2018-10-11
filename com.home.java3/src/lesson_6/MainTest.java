package lesson_6;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static lesson_6.Main.operation;

public class MainTest {


    @Test
    // При одиночном запуске ОК
    // При запуске всех тестов Failed ?
    public void operationTest() {
        int[] arr = {2, 3, 4, 6, 7, 8, 9};
        List<Integer> arr3 = Arrays.asList(6, 7, 8, 9);
        Assert.assertArrayEquals(new List[]{arr3}, new List[]{Main.operation(arr)});
    }

    @Test
    public void operationTest2() {
        int[] arr = {4, 4, 4, 6, 7, 8, 9};
        List<Integer> arr3 = Arrays.asList(6, 7, 8, 9);
        Assert.assertArrayEquals(new List[]{arr3}, new List[]{Main.operation(arr)});
    }

    @Test
    public void operationTest3() {
        int[] arr = {2, 3, 0, 6, 7, 3, 9};
        List<Integer> arr3 = Arrays.asList(9);
        Assert.assertArrayEquals(new List[]{arr3}, new List[]{Main.operation(arr)});
    }

    @Test
    // При одиночном запуске ОК
    // При запуске всех тестов Failed ?
    public void operationTest4() {
        int[] arr = {2, 3, 0, 4, 7, 3, 9};
        List<Integer> arr3 = Arrays.asList(7, 8, 9);
        Assert.assertArrayEquals(new List[]{arr3}, new List[]{Main.operation(arr)});
    }

    @Test
    public void operation2() {
        int[] arrTest = {2, 3, 0, 6, 7, 3, 9};
        Assert.assertTrue("False", Main.operation2(arrTest));
    }

    @Test
    public void operation2Test2() {
        int[] arrTest = {1, 4, 4, 1, 1, 1, 1};
        Assert.assertTrue("False", Main.operation2(arrTest));
    }

    @Test
    public void operation2Test3() {
        int[] arrTest = {1, 1, 1, 1, 1};
        Assert.assertTrue("False", Main.operation2(arrTest));
    }

    @Test
    public void operation2Test4() {
        int[] arrTest = {4, 4, 4, 4};
        Assert.assertTrue("False", Main.operation2(arrTest));
    }
}
