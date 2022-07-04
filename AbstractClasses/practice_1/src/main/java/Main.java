public class Main {
    public static void main(String[] args) {
        Client client1 = new CompanyClient();
        client1.put(1000);
        System.out.println(client1.getName());
        System.out.println("Баланс до снятия: " + client1.getAmount());
        client1.take(200);
        client1.getConditions();
        System.out.println();

        Client client2 = new IndividualEntrepreneurClient();
        client2.put(1000);
        System.out.println(client2.getName());
        System.out.println("Баланс до снятия: " + client2.getAmount());
        client2.take(200);
        client2.getConditions();
        System.out.println();

        Client client3 = new PhysicalPersonClient();
        client3.put(1000);
        System.out.println(client3.getName());
        System.out.println("Баланс до снятия: " + client3.getAmount());
        client3.take(200);
        client3.getConditions();
    }
}
