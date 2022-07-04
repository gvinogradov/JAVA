public class PhysicalPersonClient extends Client {

    private static final String CLIENT_TYPE = "Физическое лицо";

    public PhysicalPersonClient() {
        putComission = 0.0;
        takeComission = 0.0;
    }

    @Override
    public void take(double amount) {
        super.take(amount * (1 + takeComission));
    }

    @Override
    public void put(double amount) {
        super.put(amount * (1 - putComission));
    }

    @Override
    public String getName() {
        return CLIENT_TYPE;
    }
}
