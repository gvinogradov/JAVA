public class Сargo {
    private final String name;
    private final String shippingAddress;
    private final String regNumber;
    private final double weight;
    private final boolean thisSideUp;
    private final boolean fragile;
    private final Dimensions dimensions;

    public Сargo(String name, String shippingAddress, String regNumber, double weight, boolean thisSideUp, boolean fragile, double length, double width, double height) {
        this.name = name;
        this.shippingAddress = shippingAddress;
        this.regNumber = regNumber;
        this.weight = weight;
        this.thisSideUp = thisSideUp;
        this.fragile = fragile;
        this.dimensions = new Dimensions(length, width, height);
    }


    public Сargo setShippingAddress(String shippingAddress) {
        return new Сargo(name, shippingAddress, regNumber, weight, thisSideUp, fragile, dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight());
    }

    public Сargo setName(String name) {
        return new Сargo(name, shippingAddress, regNumber, weight, thisSideUp, fragile, dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight());
    }

    public Сargo setRegNumber(String regNumber) {
        return new Сargo(name, shippingAddress, regNumber, weight, thisSideUp, fragile, dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight());
    }

    public Сargo setIsThisSideUp(boolean thisSideUp) {
        return new Сargo(name, shippingAddress, regNumber, weight, thisSideUp, fragile, dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight());
    }

    public Сargo isFragile(boolean fragile) {
        return new Сargo(name, shippingAddress, regNumber, weight, thisSideUp, fragile, dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight());
    }

    public Сargo setDimensions(double length, double width, double height) {
        return new Сargo(name, shippingAddress, regNumber, weight, thisSideUp, fragile, length, width, height);
    }

    public Сargo setWeight(double weight) {
        return new Сargo(name, shippingAddress, regNumber, weight, thisSideUp, fragile, dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight());
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public String getName() {
        return name;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isThisSideUp() {
        return thisSideUp;
    }

    public boolean isFragile() {
        return fragile;
    }

    public String toString() {
        return "Информация о грузе. \n\n" +
                "Название: " + name + "\n" +
                "адрес доставки: " + shippingAddress + "\n" +
                "регистрационный номер: " + regNumber + "\n" +
                "масса: " + weight + "\n" +
                "объем груза: " + dimensions.getVolume() + "\n" +
                "запрещено переворачивать: " + (isThisSideUp() ? "Да" : "Нет") + "\n" +
                "хрупкий груз: " + (isFragile() ? "Да" : "Нет") + "\n" +
                dimensions + "\n";
    }

}
