import java.util.Scanner;

public class PasswordChecker {
    private int minLength;
    private int maxRepeats;
    private boolean isMinLengthSet = false;
    private boolean isMaxRepeatsSet = false;

    public void setMinLength(int minLength) {
        if (minLength < 0) {
            throw new IllegalArgumentException("Минимальная длина пароля не может быть отрицательной.");
        }
        this.minLength = minLength;
        this.isMinLengthSet = true;
    }

    public void setMaxRepeats(int maxRepeats) {
        if (maxRepeats <= 0) {
            throw new IllegalArgumentException("Максимальное количество повторений должно быть положительным.");
        }
        this.maxRepeats = maxRepeats;
        this.isMaxRepeatsSet = true;
    }

    public boolean verify(String password) {
        if (!isMinLengthSet || !isMaxRepeatsSet) {
            throw new IllegalStateException("Настройки не установлены. Установите минимальную длину и максимальное количество повторений.");
        }

        // Проверка минимальной длины
        if (password.length() < minLength) {
            return false;
        }

        // Проверка максимального количества повторений
        int currentRepeatCount = 1;
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) == password.charAt(i - 1)) {
                currentRepeatCount++;
                if (currentRepeatCount > maxRepeats) {
                    return false;
                }
            } else {
                currentRepeatCount = 1; // Сброс счетчика
            }
        }

        return true;
    }

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