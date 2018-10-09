package lesson_1;

import java.util.ArrayList;

public class Box<T> {
    private ArrayList<T> object;

    public Box(ArrayList<T> object) {
        this.object = object;
    }

    public ArrayList<T> getObject() {
        return object;
    }

    public float getWeight() {
        float w = 0;

        if (object.size() != 0) {
            switch (String.valueOf(object.get(0).getClass())) {
                case "class Apple":
                    w = Apple.getWeight();
                    break;
                case "class Orange":
                    w = Orange.getWeight();
                    break;
            }
            return object.size() * w;
        } else return 0;
    }

    public boolean compare(Box<T> ob) {
        return this.getWeight() == ob.getWeight();
    }

    public void add(Box<T> ob) {
        this.object.addAll(ob.object);
        ob.object.clear();
    }
}
