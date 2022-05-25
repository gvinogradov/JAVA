public class Main {
    public static void main(String[] args) {
        Сargo computer = new Сargo("Компьютер", "Москва, ул. Ленина, д. 2", "1234RET", 4.2,
                false, false, 31.2, 18, 35.5);
        System.out.println(computer);
        computer = computer.setWeight(10);
        computer = computer.setShippingAddress("Ярославль, ул. Советская, 2а");
        System.out.println(computer);

        Сargo monitor = new Сargo("Монитор","Москва, ул. Ленина, д. 2", "1235RET", 6.4,
                true, true, 33, 33, 32);
        System.out.println(monitor);
        monitor = monitor.setDimensions(18.8, 19.3, 12.2);
        monitor = monitor.setIsThisSideUp(false);
        System.out.println(monitor);
    }
}
