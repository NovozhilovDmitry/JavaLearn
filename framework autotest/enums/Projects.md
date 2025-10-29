src\main\java\enums
# Projects.java

## Summary
Enum, представляющий различные проекты в системе. Содержит информацию о названии проекта, соответствующей схеме базы данных и параметрах системных настроек. Используется для управления конфигурациями проектов, включая определение шлюзов и параметров подключения к БД.

## Public методы
1. **hasItsOwnGates()**  
   Проверяет, имеет ли проект собственные шлюзы. Возвращает boolean.

2. **getByValue(String value)**  
   Статический метод для получения экземпляра Projects по строковому значению. Параметр: value - строковое значение проекта. Может выбросить AssertionError если проект не найден.

3. **currentProject()**  
   Статический метод, возвращающий текущий проект на основе MainClass.currentProject.

## Dependencies
- base.MainClass (для currentProject)
- java.util.Arrays, Collections, HashMap, Map

## Usage Example
```java
// Получение текущего проекта
Projects current = Projects.currentProject();

// Проверка наличия шлюзов
boolean hasGates = Projects.DEPOSIT.hasItsOwnGates();

// Получение проекта по значению
Projects credit = Projects.getByValue("credit");
```

## Uncertainties
1. Назначение nv_parameterName не до конца ясно из комментария
2. Логика hasItsOwnGates() (value.equals(schema)) может быть неочевидной
3. Неясно, как именно используется DB_SCHEMAS в других частях системы