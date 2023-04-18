
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Converter converter = new Converter();

    public static void main(String[] args) throws IOException {


        Scanner scanner = new Scanner(System.in);
        boolean go = true;
        while (go) {
            System.out.println("Введите действие");
            String value = scanner.next();
            if (value.equals("exit")) {
                go = false;
                System.out.println("Finished");
                break;
            }

            try {
                int answer = Integer.parseInt(calc(value));
                if (value.contains("X") || value.contains("V") || value.contains("I")) {
                    System.out.println(converter.getRoma(answer));
                } else {

                    System.out.println(answer);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String calc(String str) throws IOException {
        float sum = 0;
        String[] string;

        boolean haveRoma = str.contains("X") || str.contains("V") || str.contains("I");// || str.contains("L") ||
                //str.contains("M") || str.contains("C");
        boolean haveArabian = str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") ||
                str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") ||
                str.contains("9");
        if (haveArabian) {
            string = str.split("[-/*+]");
            for (int a = 0; a < string.length; a++) {
                if (Integer.parseInt(string[a]) > 10)
                    throw new IOException("Программа не принимает чисел больше 10, простите");
            }
        } else if (haveRoma) {
            string = str.split("[-/*+]");
            for (int a = 0; a < string.length; a++) {
                if (converter.getArab(string[a]) > 10)
                    throw new IOException("Программа не принимает чисел больше 10, простите");
            }
        }
        if (haveArabian && haveRoma)
            throw new IOException("Операция невозможна, т.к. используются одновременно разные" +
                    " системы счисления");

        if (str.indexOf("+") != -1) {
            string = str.split("[+]");
            for (int j = 0; j < string.length; j++) {
                if (string[j].contains("/") || string[j].contains("*") || string[j].contains("-") || string.length > 2)
                    throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один" +
                            " оператор (+, -, /, *)");
            }
            if (haveRoma)
                sum = converter.getArab(string[0]) + converter.getArab(string[1]);
            else sum = Integer.parseInt(string[0]) + Integer.parseInt(string[1]);

        } else if (str.indexOf("-") != -1) {
            string = str.split("-");
            for (int j = 0; j < string.length; j++) {
                if (string[j].contains("/") || string[j].contains("*") || string[j].contains("+") || string.length > 2)
                    throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один" +
                            " оператор (+, -, /, *)");
            }
            if (haveRoma)
                sum = converter.getArab(string[0]) - converter.getArab(string[1]);
            if (sum <= 0) throw new IOException("в римской системе нет отрицательных чисел и нуля");
            else sum = Integer.parseInt(string[0]) - Integer.parseInt(string[1]);

        } else if (str.indexOf("/") != -1) {
            string = str.split("/");
            for (int j = 0; j < string.length; j++) {
                if (string[j].contains("+") || string[j].contains("*") || string[j].contains("-") || string.length > 2)
                    throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один" +
                            " оператор (+, -, /, *)");
            }
            if (haveRoma)
                sum = (float) converter.getArab(string[0]) / (float) converter.getArab(string[1]);
            else sum = (float) Integer.parseInt(string[0]) / (float) Integer.parseInt(string[1]);

            if (sum != (int) sum) {
                throw new IOException("Калькулятор работает только с целыми числами!!!");

            }
        } else if (str.indexOf("*") != -1) {
            string = str.split("[*]");
            for (int j = 0; j < string.length; j++) {
                if (string[j].contains("/") || string[j].contains("+") || string[j].contains("-") || string.length > 2)
                    throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один" +
                            " оператор (+, -, /, *)");
            }
            if (haveRoma)
                sum = converter.getArab(string[0]) * converter.getArab(string[1]);
            else sum = Integer.parseInt(string[0]) * Integer.parseInt(string[1]);
        }
        int finalString = (int) sum;
        return new String("" + finalString);
    }

}