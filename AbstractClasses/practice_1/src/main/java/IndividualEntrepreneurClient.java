public class IndividualEntrepreneurClient extends Client {
    private static final String CLIENT_TYPE = "Индивидуальный предприниматель";

    public IndividualEntrepreneurClient() {
        putComission = 0.01;
        takeComission = 0.0;
    }

    @Override
    public void take(double amount) {
        super.take(amount * (1 + takeComission));
    }

    @Override
    public void put(double amount) {
        double smartComission = amount < 1000 ? putComission : putComission * 0.5;
        super.put(amount * (1 - smartComission));
    }

    @Override
    public String getName() {
        return CLIENT_TYPE;
    }

    @Override
    public void getConditions() {
        System.out.printf("Информация о счёте: \n " +
                "- комиссия на пополнение %.1f%%, если сумма меньше 1000 рублей, иначе комиссия %.1f%%\n" +
                "- комиссия на снятие %.1f \n " +
                "- баланс %.2f \n", putComission * 100, putComission * 50, takeComission * 100, amount);
    }
}
