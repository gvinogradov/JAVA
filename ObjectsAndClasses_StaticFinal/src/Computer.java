public class Computer {
    private final String name;
    private final ComputerVendor vendor;
    private final Cpu cpu;
    private final Ram ram;
    private final HardDisk hardDisk;
    private final Monitor monitor;
    private final KeyBoard keyBoard;

    public Computer(String name, ComputerVendor vendor, Cpu cpu, Ram ram, HardDisk hardDisk, Monitor monitor, KeyBoard keyBoard) {
        this.name = name;
        this.vendor = vendor;
        this.cpu = cpu;
        this.ram = ram;
        this.hardDisk = hardDisk;
        this.monitor = monitor;
        this.keyBoard = keyBoard;
    }

    public Computer setName(String name) {
        return new Computer(name, vendor, cpu, ram, hardDisk, monitor, keyBoard);
    }

    public Computer setVendor(ComputerVendor vendor) {
        return new Computer(name, vendor, cpu, ram, hardDisk, monitor, keyBoard);
    }

    public Computer setCpu(Cpu cpu) {
        return new Computer(name, vendor, cpu, ram, hardDisk, monitor, keyBoard);
    }

    public Computer setRam(Ram ram) {
        return new Computer(name, vendor, cpu, ram, hardDisk, monitor, keyBoard);
    }

    public Computer setHardDisk(HardDisk hardDisk) {
        return new Computer(name, vendor, cpu, ram, hardDisk, monitor, keyBoard);
    }

    public Computer setMonitor(Monitor monitor) {
        return new Computer(name, vendor, cpu, ram, hardDisk, monitor, keyBoard);
    }

    public Computer setKeyBoard(KeyBoard keyBoard) {
        return new Computer(name, vendor, cpu, ram, hardDisk, monitor, keyBoard);
    }

    public String getName() {
        return name;
    }

    public ComputerVendor getVendor() {
        return vendor;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public Ram getRam() {
        return ram;
    }

    public HardDisk getHardDisk() {
        return hardDisk;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public KeyBoard getKeyBoard() {
        return keyBoard;
    }

    public double getTotalWheight() {
        return cpu.getWheight() +
                ram.getWheight() +
                hardDisk.getWheight() +
                monitor.getWheight() +
                keyBoard.getWheight();
    }

    @Override
    public String toString() {
        return "Название: " + name + "\n" +
                "Производитель: " + vendor + "\n" +
                cpu + "\n" +
                ram + "\n" +
                hardDisk + "\n" +
                monitor + "\n" +
                keyBoard + "\n";
    }
}
