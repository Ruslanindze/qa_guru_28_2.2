package qa.guru;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Реестр уникальных идентификаторов спутников.
 * Использует HashSet для хранения уникальных ID.
 * В космической отрасли это критично - каждый спутник должен иметь уникальный идентификатор
 * для избежания коллизий в системах связи и навигации.
 */
public class SatelliteRegistry {
    private final Set<String> satelliteIds;

    public SatelliteRegistry() {
        this.satelliteIds = new HashSet<>();
    }

    /**
     * Регистрация нового спутника.
     * Например: запуск нового спутника Starlink или GPS.
     */
    public boolean registerSatellite(String satelliteId) {
        return satelliteIds.add(satelliteId);
    }

    /**
     * Списание спутника (выход из орбиты).
     * Например: controlled deorbit после окончания срока службы.
     */
    public boolean decommissionSatellite(String satelliteId) {
        return satelliteIds.remove(satelliteId);
    }

    /**
     * Поиск спутника по ID.
     * Использует Iterator для перебора элементов.
     * Итератор позволяет безопасно удалять элементы во время перебора.
     */
    public boolean findSatellite(String satelliteId) {
        Iterator<String> iterator = satelliteIds.iterator();
        while (iterator.hasNext()) {
            String id = iterator.next();
            if (id.equals(satelliteId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Вывод всех зарегистрированных спутников.
     * Использует Iterator для демонстрации.
     */
    public void printAllSatellites() {
        Iterator<String> iterator = satelliteIds.iterator();
        System.out.println("Зарегистрированные спутники:");
        while (iterator.hasNext()) {
            System.out.println("ID: " + iterator.next());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SatelliteRegistry: Реестр спутников ===");
        SatelliteRegistry registry = new SatelliteRegistry();
        
        // Регистрация спутников
        boolean reg1 = registry.registerSatellite("STARLINK-1001");
        boolean reg2 = registry.registerSatellite("GPS-IIF-5");
        boolean reg3 = registry.registerSatellite("STARLINK-1002");
        System.out.println("Зарегистрированы спутники: " + reg1 + ", " + reg2 + ", " + reg3);
        
        // Попытка регистрации дубликата (HashSet не позволит)
        boolean duplicate = registry.registerSatellite("STARLINK-1001");
        System.out.println("Повторная регистрация того же ID: " + duplicate + " (должно быть false)");
        
        // Поиск спутника
        boolean satFound = registry.findSatellite("GPS-IIF-5");
        System.out.println("Спутник 'GPS-IIF-5' найден: " + satFound);
        
        // Вывод всех спутников
        registry.printAllSatellites();
        
        // Декомиссия спутника
        boolean decommissioned = registry.decommissionSatellite("GPS-IIF-5");
        System.out.println("Спутник выведен из орбиты: " + decommissioned);
        
        registry.printAllSatellites();
    }
}
