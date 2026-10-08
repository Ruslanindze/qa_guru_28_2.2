package qa.guru;

import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер банковских счетов клиентов.
 * Использует HashMap для хранения пар "номер счета -> баланс".
 * В банковской системе это критично для быстрого доступа к счету по номеру.
 * O(1) для поиска, добавления и удаления - идеально для транзакций в реальном времени.
 */
public class AccountManager {
    private final Map<String, Double> accounts;

    public AccountManager() {
        this.accounts = new HashMap<>();
    }

    /**
     * Открытие нового счета с начальным балансом.
     * Например: клиент открывает депозитный счет с начальным вкладом.
     */
    public void openAccount(String accountNumber, double initialBalance) {
        accounts.put(accountNumber, initialBalance);
    }

    /**
     * Закрытие счета.
     * Например: клиент закрывает счет после вывода всех средств.
     */
    public Double closeAccount(String accountNumber) {
        return accounts.remove(accountNumber);
    }

    /**
     * Поиск счета и получение баланса.
     * Использует for-цикл по entrySet для перебора всех счетов.
     * В реальной системе использовали бы get(), но здесь демонстрация цикла.
     */
    public Double getBalance(String accountNumber) {
        for (Map.Entry<String, Double> entry : accounts.entrySet()) {
            if (entry.getKey().equals(accountNumber)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Проверка существования счета.
     */
    public boolean accountExists(String accountNumber) {
        for (Map.Entry<String, Double> entry : accounts.entrySet()) {
            if (entry.getKey().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Вывод всех счетов и балансов.
     * Использует for-цикл по entrySet для демонстрации.
     */
    public void printAllAccounts() {
        System.out.println("Все счета:");
        for (Map.Entry<String, Double> entry : accounts.entrySet()) {
            System.out.println("Счет #" + entry.getKey() + ", Баланс: $" + entry.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== AccountManager: Управление счетами ===");
        AccountManager accountManager = new AccountManager();
        
        // Открытие счетов
        accountManager.openAccount("ACC-001", 1000.0);
        accountManager.openAccount("ACC-002", 5000.0);
        accountManager.openAccount("ACC-003", 250.0);
        System.out.println("Открыты 3 счета");
        
        // Проверка существования счета
        boolean exists = accountManager.accountExists("ACC-002");
        System.out.println("Счет ACC-002 существует: " + exists);
        
        // Получение баланса
        Double balance = accountManager.getBalance("ACC-002");
        System.out.println("Баланс счета ACC-002: $" + balance);
        
        // Вывод всех счетов
        accountManager.printAllAccounts();
        
        // Закрытие счета
        Double closedBalance = accountManager.closeAccount("ACC-003");
        System.out.println("Счет ACC-003 закрыт, возвращенный баланс: $" + closedBalance);
        
        accountManager.printAllAccounts();
    }
}
