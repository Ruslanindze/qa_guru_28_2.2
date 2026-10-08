package qa.guru;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для управления списком клиентов банка.
 * Использует ArrayList для хранения имен клиентов.
 * В реальном банке это могло бы быть CRM-система для работы с клиентской базой.
 */
public class BankClients {
    private final List<String> clients;

    public BankClients() {
        this.clients = new ArrayList<>();
    }

    /**
     * Добавление нового клиента в базу.
     * Например: регистрация нового клиента при открытии счета.
     */
    public void addClient(String clientName) {
        clients.add(clientName);
    }

    /**
     * Удаление клиента из базы.
     * Например: закрытие всех счетов клиента и удаление из CRM.
     */
    public boolean removeClient(String clientName) {
        return clients.remove(clientName);
    }

    /**
     * Поиск клиента в базе.
     * Использует for-each цикл для перебора всех клиентов.
     * Возвращает true, если клиент найден.
     */
    public boolean findClient(String clientName) {
        for (String client : clients) {
            if (client.equals(clientName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Вывод всех клиентов.
     * Использует for-each цикл для демонстрации.
     */
    public void printAllClients() {
        for (String client : clients) {
            System.out.println("Клиент: " + client);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== BankClients: Список клиентов банка ===");
        BankClients bankClients = new BankClients();
        
        // Добавление клиентов
        bankClients.addClient("Иванов Иван");
        bankClients.addClient("Петров Петр");
        bankClients.addClient("Сидоров Сидор");
        System.out.println("Добавлены 3 клиента");
        
        // Поиск клиента
        boolean found = bankClients.findClient("Петров Петр");
        System.out.println("Клиент 'Петров Петр' найден: " + found);
        
        boolean notFound = bankClients.findClient("Смирнов Смирнов");
        System.out.println("Клиент 'Смирнов Смирнов' найден: " + notFound);
        
        // Вывод всех клиентов
        bankClients.printAllClients();
        
        // Удаление клиента
        boolean removed = bankClients.removeClient("Сидоров Сидор");
        System.out.println("Клиент удален: " + removed);
        
        bankClients.printAllClients();
    }
}
