public class KeyBoard {
    private final KeyBoardType keyBoardType;
    private final LedType led;
    private final double wheight;

    public KeyBoard(KeyBoardType keyBoardType, LedType led, double wheight) {
        this.keyBoardType = keyBoardType;
        this.led = led;
        this.wheight = wheight;
    }

    public KeyBoard setKeyBoardType(KeyBoardType keyBoardType) {
        return new KeyBoard(keyBoardType, led, wheight);
    }

    public KeyBoard setLed(LedType led) {
        return new KeyBoard(keyBoardType, led, wheight);
    }

    public KeyBoard setWheight(double wheight) {
        return new KeyBoard(keyBoardType, led, wheight);
    }

    public KeyBoardType getKeyBoardType() {
        return keyBoardType;
    }

    public LedType isLed() {
        return led;
    }

    public double getWheight() {
        return wheight;
    }

    @Override
    public String toString() {
        return "Клавиатура" +
                keyBoardType + ", " +
                led + ")";
    }
}
