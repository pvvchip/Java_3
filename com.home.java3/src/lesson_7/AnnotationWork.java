package lesson_7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class AnnotationWork {

    public static void main(String[] args) {
        start(CalcTests.class);
    }

    public static void start(String className) throws ClassNotFoundException {
        start(Class.forName(className));
    }

    public static void start(Class classObj) {
        runTests(classObj);
    }

    private static void runTests(Class classObj) {
        Object tester;
        Method beforeTests = null;
        Method afterTests = null;
        Comparator<Method> comp = (n_1, n_2) -> {
            Integer i_1 = n_1.getAnnotation(Test.class).pointHI();
            Integer i_2 = n_2.getAnnotation(Test.class).pointHI();
            return i_1.compareTo(i_2);
        };
        Set<Method> testMethods = new TreeSet<>(comp);
        try {
            tester = classObj.newInstance();
            for (Method method : classObj.getDeclaredMethods()) {
                method.setAccessible(true);
                if (method.isAnnotationPresent(BeforeSuite.class)) {
                    if (beforeTests == null)
                        beforeTests = method;
                    else throw new RuntimeException();
                } else if (method.getAnnotation(AfterSuite.class) != null) {
                    if (afterTests == null)
                        afterTests = method;
                    else throw new RuntimeException();
                } else if (method.getAnnotation(Test.class) != null) {
                    testMethods.add(method);
                }
            }

            if (beforeTests != null) beforeTests.invoke(tester);

            for (Method method : testMethods) {
                method.invoke(tester);
            }

            if (afterTests != null) afterTests.invoke(tester);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
