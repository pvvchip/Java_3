import java.util.ArrayList;
import java.util.Arrays;

public class MyGeneric<T> {
    private T[] object;

    public MyGeneric(T[] object) {
        this.object = object;
    }

    public void chElements(int i, int i1) {
        T n = this.object[i1];
        this.object[i1] = this.object[i];
        this.object[i] = n;
    }

    public void show() {
        System.out.println(Arrays.toString(this.object));
    }

    public ArrayList<T> arList() {
        ArrayList<T> al = new ArrayList<>();
        for (T i : this.object) al.add(i);
        return al;
    }
}