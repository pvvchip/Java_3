package lesson_7;


public class CalcTests extends AnnotationWork {
    private Calculator calculator;

    public CalcTests() {
        calculator = new Calculator();
    }

    @BeforeSuite
    public void testBefore() {
        System.out.println("Start testing...");
    }

    @Test(pointHI = 2)
    public void testAdd() {
        System.out.println("Тестируем сложение...");
        int count = 0;
        if (calculator.add(2, 2) != 3) count++;
        if (calculator.add(8, -3) != 6) count++;
        if (calculator.add(0, 22) != 22) count++;
        if (count != 0) System.out.println("Провалено " + count + " тестов");
        else System.out.println("Тестирование успешно завершено");
    }

    @Test(pointHI = 1)
    public void testSub() {
        System.out.println("Тестируем вычитание...");
        int count = 0;
        if (calculator.sub(2, 2) != 0) count++;
        if (calculator.sub(8, -3) != 11) count++;
        if (calculator.sub(0, 22) != -22) count++;
        if (count != 0) System.out.println("Провалено " + count + " тестов");
        else System.out.println("Тестирование успешно завершено");
    }

    @AfterSuite
    public void testAfter() {
        System.out.println("Testing finished");
    }
}
