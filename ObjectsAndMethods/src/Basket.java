public class Basket {


    private static int count = 0;
    private static int itemsCount = 0;
    private static int price = 0;

    private String items = "";
    private int totalPrice = 0;
    private int limit;
    private double totalWeight = 0;

    public Basket() {
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static int getCount() {
        return count;
    }

    public static int getItemsCount() {
        return itemsCount;
    }

    public static int getPrice() {
        return price;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public static void increaseCount(int count) {
        Basket.count = Basket.count + count;
    }

    public static void increasePrice(int price) {
        Basket.price = Basket.price + price;
    }

    public static void increaseItemCount(int count) {
        Basket.itemsCount = Basket.itemsCount + count;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        increasePrice(price);
        increaseItemCount(count);

        items = items + "\n" + name + " - " +
            count + " шт. - " + price;
        totalPrice = totalPrice + count * price;
    }

    public void add(String name, int price, int count, double weight) {
        add(name, price, count);
        totalWeight = totalWeight + weight;
    }

    public void add(String name, int price, double weight) {
        add(name, price, 1, weight);
    }

    public void clear() {
        items = "";
        totalPrice = 0;
    }


    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }

    public static double averageItemPrice() {
        return Basket.itemsCount > 0 ? (double) Basket.price / Basket.itemsCount : 0;
    }

    public static double averageBasketPrice() {
        return Basket.count > 0 ? (double) Basket.price / Basket.count : 0;
    }


}
