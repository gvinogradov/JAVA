public class Main {
    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Молоко", 70, 1);
        basket.add("Хлеб", 35, 1);

        Basket basket2 = new Basket();
        basket2.add("Мыло", 120, 1);
        basket2.add("Стиральный порошок", 350, 1);

        System.out.println("Общее количество корзин: " + Basket.getCount());
        System.out.println("Общее количество товаров в корзинах: " + Basket.getItemsCount());
        System.out.println("Общая стоимость товаров в корзинах: " + Basket.getPrice());
        System.out.println("Средняя стоимость товара: " + Basket.averageItemPrice());
        System.out.println("Средняя стоимость корзины: " + Basket.averageBasketPrice());
    }
}
