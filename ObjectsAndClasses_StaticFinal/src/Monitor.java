public class Monitor {
    private final double diagonal;
    private final MonitorType monitorType;
    private final double wheight;

    public Monitor(double diagonal, MonitorType monitorType, double wheight) {
        this.diagonal = diagonal;
        this.monitorType = monitorType;
        this.wheight = wheight;
    }

    public Monitor setDiagonal(double diagonal) {
        return new Monitor(diagonal, monitorType, wheight);
    }

    public Monitor setMonitorType(MonitorType monitorType) {
        return new Monitor(diagonal, monitorType, wheight);
    }

    public Monitor setWheight(double wheight) {
        return new Monitor(diagonal, monitorType, wheight);
    }

    public double getDiagonal() {
        return diagonal;
    }

    public MonitorType getMonitorType() {
        return monitorType;
    }

    public double getWheight() {
        return wheight;
    }

    @Override
    public String toString() {
        return "Монитор(" +
                diagonal + '"' + ", " +
                monitorType + ")";
    }
}
