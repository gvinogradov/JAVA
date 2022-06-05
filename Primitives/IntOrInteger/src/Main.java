public class Main {
    public static void main(String[] args) {
//        Container container = new Container();
//        container.addCount(5672);
//        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.


        for (int i = 0; i <= (int) 'ё'; i++) {
            if (isCyrillicChar(i)) {
                System.out.println(i + " - " + (char) i);
            }
        }
    }

    public static boolean isCyrillicChar(int charNumber) {
        if (charNumber == 'Ё' || charNumber == 'ё') {
            return true;
        }
        if (charNumber >= 'А' && charNumber <= 'Я' ||
                charNumber >= 'а' && charNumber <= 'я') {
            return true;
        }
        return false;
    }

}
