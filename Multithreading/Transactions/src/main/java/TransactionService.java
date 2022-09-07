public class TransactionService implements Runnable{
    private Bank bank;
    private Transaction[] transactions;
    private int threadNum;
    private int threadCount;

    public TransactionService(Bank bank, Transaction[] transactions, int threadNum, int threadCount) {
        this.bank = bank;
        this.transactions = transactions;
        this.threadNum = threadNum;
        this.threadCount = threadCount;
    }

    @Override
    public void run() {
        for (Transaction tr : transactions) {
            tr.setSuccess(bank.transfer(
                            tr.getSrcAcc(),
                            tr.getDstAcc(),
                            tr.getAmount()
                    )
            );
            System.out.printf("[%d / %d] %s -> %s : %d - %s\n", threadNum, threadCount,
                    tr.getSrcAcc(),
                    tr.getDstAcc(),
                    tr.getAmount(),
                    tr.getSuccess() ? "success" : "fail");
        }
    }
}
