import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private static final String URL = "jdbc:mysql://192.168.5.89:3306/skillbox";
    private static final String USER = "skillbox";
    private static final String PASS = "Ab123456";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT " +
                            "CONCAT('\"'," +
                            "p.course_name," +
                            "'\" = '," +
                            "ROUND(p.count / (p.maxMonth - p.minMonth + 1), 2)) `avgPurchase`" +
                            " FROM(SELECT pl.course_name," +
                                        "pl.subscription_date," +
                                        "MIN(MONTH(pl.subscription_date)) `minMonth`," +
                                        "MAX(MONTH(pl.subscription_date)) `maxMonth`," +
                                        "COUNT(*) `count`" +
                                " FROM PurchaseList pl" +
                                " GROUP BY pl.course_name) p;"
            );
            while (resultSet.next()) {
                String courceName = resultSet.getString("avgPurchase");
                System.out.println(courceName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
