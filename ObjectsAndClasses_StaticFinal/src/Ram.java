public class Ram {
    private final RamType ramType;
    private final int volume;
    private final double wheight;

    public Ram(RamType ramType, int volume, double wheight) {
        this.ramType = ramType;
        this.volume = volume;
        this.wheight = wheight;
    }

    public Ram setRamType(RamType ramType) {
        return new Ram(ramType, volume, wheight);
    }

    public Ram setVolume(int volume) {
        return new Ram(ramType, volume, wheight);
    }

    public Ram setWheight(double wheight) {
        return new Ram(ramType, volume, wheight);
    }

    public RamType getRamType() {
        return ramType;
    }

    public int getVolume() {
        return volume;
    }

    public double getWheight() {
        return wheight;
    }

    @Override
    public String toString() {
        return "Оперативная память(" +
                ramType + ", " +
                volume + "Гб)";
    }
}
