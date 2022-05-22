public class Arithmetic {
    private int value1 = 0;
    private int value2 = 0;

    public Arithmetic (int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public int sum () {
        return value1 + value2;
    }

    public int multiply () {
        return value1 * value2;
    }

    public int max () {
        return value1 >= value2 ? value1 : value2;
    }

    public int min () {
        return value1 <= value2 ? value1 : value2;
    }

}
