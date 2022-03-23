import java.text.DecimalFormat;
import java.util.Scanner;

public class Calculator {

    static Scanner scanner = new Scanner(System.in);
    static DecimalFormat decimalFormat = new DecimalFormat("#.####");
    static boolean isClean = true;
    static boolean isExit = false;

    public static void main(String[] args) {

        double operand1;
        double operand2;
        char operation;
        double result = 0;

        printDescription();
        do {
            if (isClean) {
                isClean = false;
                operand1 = getOperand(1);
            } else {
                operand1 = result;
                System.out.println("Далее.   Число 1: " + decimalFormat.format(operand1));
            }
            if ((!isExit) && (!isClean)) {
                operation = getOperation();
                if ((!isExit) && (!isClean)) {
                    operand2 = getOperand(2);
                    if ((!isExit) && (!isClean)) {
                        result = calculation(operand1, operation, operand2);
                        printResult(operand1, operation, operand2, result);
                    }
                }
            }
            if (isClean){
                System.out.println("Начинаем сначала!");
            }
        } while (!isExit);
        System.out.println("Окончание работы. До свидания!");
        scanner.close();
    }

    private static double getOperand(int number) {
        System.out.printf("Введите  число %d: ", number);
        if (scanner.hasNextDouble()) {
            return scanner.nextDouble();
        } else {
            String value = scanner.next();
            return switch (value) {
                case "S", "s" -> {
                    isExit = true;
                    yield 0;
                }
                case "C", "c", "С", "с" -> {
                    isClean = true;
                    yield 0;
                }
                default -> {
                    System.out.println("Вы допустили ошибку при вводе числа. Попробуйте еще раз.");
                    System.out.println("Для сброса нажмите символ С, для выхода S.");
                    yield getOperand(number);
                }
            };
        }
    }

    private static char getOperation() {
        System.out.print("Введите операцию: ");
        String value = scanner.next();
        switch (value) {
            case "+", "-", "*", "/" -> {
            }
            case "S", "s" -> isExit = true;
            case "C", "c", "С", "с" -> isClean = true;
            default -> {
                System.out.println("Вы допустили ошибку при вводе операции. Калькулятор распознает операции: +, -, *, /. Попробуйте еще раз.");
                System.out.println("Для сброса нажмите символ С, для выхода S.");
                return getOperation();
            }
        }
        return value.charAt(0);
    }

    private static double calculation(double operand1, char operation, double operand2) {
        return switch (operation) {
            case '+' -> operand1 + operand2;
            case '-' -> operand1 - operand2;
            case '*' -> operand1 * operand2;
            case '/' -> {
                if (operand2 == 0) {
                    isClean = true;
                }
                yield operand1 / operand2;
            }
            default -> {
                System.out.println("Ошибка");
                yield 0;
            }
        };
    }

    private static void printResult(double operand1, char operation, double operand2, double result) {
        String strResult = "Результат       : " + decimalFormat.format(operand1) + " " + operation + " "
                + decimalFormat.format(operand2) + " = "
                + decimalFormat.format(result);
        System.out.println(strResult);
    }

    private static void printDescription() {
        System.out.println("Добро пожаловать в калькулятор!");
        System.out.println("Калькулятор умеет выполнять операции '+, -, *, /' для целых чисел и чисел с плавающей запятой.");
        System.out.println("Для сброса нажмите символ С, для выхода S.");
        System.out.println();
    }
}
