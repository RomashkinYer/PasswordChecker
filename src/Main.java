import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PasswordChecker checker = new PasswordChecker();

        try {
            System.out.print("Введите минимально допустимую длину пароля: ");
            int minLength = Integer.parseInt(scanner.nextLine());
            checker.setMinLength(minLength);

            System.out.print("Введите максимально допустимое количество повторений символа подряд: ");
            int maxRepeats = Integer.parseInt(scanner.nextLine());
            checker.setMaxRepeats(maxRepeats);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            return; // Завершаем программу при ошибке настройки
        }

        System.out.println("Введите пароли для проверки (введите 'end' для завершения):");
        while (true) {
            String password = scanner.nextLine();
            if ("end".equalsIgnoreCase(password)) {
                break;
            }

            try {
                boolean isValid = checker.verify(password);
                System.out.println("Пароль " + password + " " + (isValid ? "допустим" : "недопустим"));
            } catch (IllegalStateException e) {
                System.out.println("Ошибка: " + e.getMessage());
                break; // Завершаем цикл при ошибке проверки
            }
        }

        scanner.close();
    }
}
