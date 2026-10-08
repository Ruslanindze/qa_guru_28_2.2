package qa.guru;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Очередь заявок на кредит.
 * Использует LinkedList как Queue (FIFO - First In, First Out).
 * В банке это стандартная практика: заявки обрабатываются в порядке поступления.
 * Это обеспечивает справедливость и прозрачность для клиентов.
 */
public class CreditRequests {
    private final Queue<String> requests;

    public CreditRequests() {
        this.requests = new LinkedList<>();
    }

    /**
     * Добавление заявки в очередь.
     * Например: клиент подает онлайн-заявку на кредит.
     */
    public void submitRequest(String requestId) {
        requests.add(requestId);
    }

    /**
     * Удаление и обработка первой заявки из очереди.
     * Например: кредитный менеджер берет следующую заявку в работу.
     */
    public String processRequest() {
        return requests.poll();
    }

    /**
     * Поиск заявки в очереди.
     * Использует while-цикл с peek() для перебора без удаления.
     * В реальной системе это может быть проверка статуса заявки клиентом.
     */
    public boolean findRequest(String requestId) {
        // Создаем временную копию для поиска без изменения оригинала
        Queue<String> tempQueue = new LinkedList<>(requests);
        
        while (!tempQueue.isEmpty()) {
            String current = tempQueue.poll();
            if (current.equals(requestId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Удаление конкретной заявки (например, клиент отозвал заявку).
     * Использует while-цикл для поиска и удаления.
     */
    public boolean cancelRequest(String requestId) {
        Queue<String> tempQueue = new LinkedList<>();
        boolean found = false;
        
        while (!requests.isEmpty()) {
            String current = requests.poll();
            if (current.equals(requestId)) {
                found = true;
            } else {
                tempQueue.add(current);
            }
        }
        
        // Восстанавливаем очередь
        requests.addAll(tempQueue);
        return found;
    }

    /**
     * Вывод всех заявок в очереди.
     * Использует while-цикл для демонстрации.
     */
    public void printAllRequests() {
        System.out.println("Заявки в очереди:");
        Queue<String> tempQueue = new LinkedList<>(requests);
        
        while (!tempQueue.isEmpty()) {
            System.out.println("Заявка: " + tempQueue.poll());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== CreditRequests: Очередь заявок на кредит ===");
        CreditRequests creditRequests = new CreditRequests();
        
        // Подача заявок
        creditRequests.submitRequest("REQ-001");
        creditRequests.submitRequest("REQ-002");
        creditRequests.submitRequest("REQ-003");
        System.out.println("Поданы 3 заявки");
        
        // Вывод всех заявок (должно показать 3)
        System.out.println("После добавления:");
        creditRequests.printAllRequests();
        
        // Поиск заявки
        boolean reqFound = creditRequests.findRequest("REQ-002");
        System.out.println("Заявка REQ-002 найдена: " + reqFound);
        
        // Отмена заявки (удаление из середины очереди)
        boolean cancelled = creditRequests.cancelRequest("REQ-002");
        System.out.println("Заявка REQ-002 отменена: " + cancelled);
        
        // Вывод всех заявок (должно показать 2)
        System.out.println("После отмены REQ-002:");
        creditRequests.printAllRequests();
        
        // Обработка заявок (FIFO)
        System.out.println("Обработка заявок:");
        String processed1 = creditRequests.processRequest();
        System.out.println("Обработана заявка: " + processed1);
        
        String processed2 = creditRequests.processRequest();
        System.out.println("Обработана заявка: " + processed2);
        
        String processed3 = creditRequests.processRequest();
        System.out.println("Обработана заявка: " + processed3 + " (должна быть null)");
        
        // Вывод всех заявок (очередь пуста)
        System.out.println("После обработки всех заявок:");
        creditRequests.printAllRequests();
    }
}
