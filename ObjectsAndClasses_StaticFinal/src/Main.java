public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer("Домашний компьютер", ComputerVendor.HP,
                new Cpu(2000, 8,CpuVendor.AMD, 0.03),
                new Ram(RamType.DDR3, 16, 0.08),
                new HardDisk(DiskType.SSD,1000,0.1),
                new Monitor(24, MonitorType.IPS,3.5),
                new KeyBoard(KeyBoardType.MECHANICAL, LedType.LED, 0.5));

        System.out.println(computer);
        System.out.println("Общий вес: " + computer.getTotalWheight() + "\n");

        computer = computer.setCpu(new Cpu(2000, 8,CpuVendor.AMD, 0.33));
        System.out.println(computer);
        System.out.println("Общий вес: " + computer.getTotalWheight() + "\n");

        Computer computer1 = new Computer("Рабочий компьютер", ComputerVendor.ASUS,
                new Cpu(4000, 16,CpuVendor.INTEL, 0.04),
                new Ram(RamType.DDR5, 32, 0.16),
                new HardDisk(DiskType.SSD,4000,0.2),
                new Monitor(27, MonitorType.IPS,4.5),
                new KeyBoard(KeyBoardType.MECHANICAL, LedType.NO_LED, 0.7));

        System.out.println(computer1);
        System.out.println("Общий вес: " + computer1.getTotalWheight() + "\n");

        computer1 = computer1.setMonitor(new Monitor(32, MonitorType.VA, 9));
        System.out.println(computer1);
        System.out.println("Общий вес: " + computer1.getTotalWheight() + "\n");
    }
}
