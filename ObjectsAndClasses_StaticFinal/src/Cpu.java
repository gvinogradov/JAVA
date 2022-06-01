public class Cpu {
    private final double frequency;
    private final int cores;
    private final CpuVendor vendor;
    private final double wheight;

    public Cpu(double frequency, int cores, CpuVendor vendor, double wheight) {
        this.frequency = frequency;
        this.cores = cores;
        this.vendor = vendor;
        this.wheight = wheight;
    }

    public Cpu setFrequency(double frequency) {
        return new Cpu(frequency, cores, vendor, wheight);
    }

    public Cpu setCores(int cores) {
        return new Cpu(frequency, cores, vendor, wheight);
    }

    public Cpu setVendor(CpuVendor vendor) {
        return new Cpu(frequency, cores, vendor, wheight);
    }

    public Cpu setWheight(double wheight) {
        return new Cpu(frequency, cores, vendor, wheight);
    }

    public double getFrequency() {
        return frequency;
    }

    public int getCores() {
        return cores;
    }

    public CpuVendor getVendor() {
        return vendor;
    }

    public double getWheight() {
        return wheight;
    }

    @Override
    public String toString() {
        return "Процессор(" +
                frequency + " Ггц, " +
                cores + " ядер, " +
                vendor + ")";
    }
}
