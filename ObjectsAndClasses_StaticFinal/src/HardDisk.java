public class HardDisk {
    private final DiskType diskType;
    private final double volume;
    private final double wheight;

    public HardDisk(DiskType diskType, double volume, double wheight) {
        this.diskType = diskType;
        this.volume = volume;
        this.wheight = wheight;
    }

    public HardDisk setDiskType(DiskType diskType) {
        return new HardDisk(diskType, volume, wheight);
    }

    public HardDisk setVolume(double volume) {
        return new HardDisk(diskType, volume, wheight);
    }

    public HardDisk setWheight(double wheight) {
        return new HardDisk(diskType, volume, wheight);
    }

    public DiskType getDiskType() {
        return diskType;
    }

    public double getVolume() {
        return volume;
    }

    public double getWheight() {
        return wheight;
    }

    @Override
    public String toString() {
        return "Жесткий диск(" +
                diskType + ", " +
                volume + "Гб)";
    }
}
