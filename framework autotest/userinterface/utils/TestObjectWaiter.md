src\main\java\userinterface\utils
# TestObjectWaiter.java

## Summary
Класс предоставляет механизмы ожидания появления тестовых объектов в интерфейсе. Реализует повторные попытки поиска с настраиваемыми таймаутами и интервалами, включая логирование процесса ожидания. Основное применение - ожидание элементов GUI в автоматизированных тестах.

## Public методы
- `waitTestObject(Element e)`  
  Ожидание появления тестового объекта для переданного Element.  
  Параметры: `e` - элемент для ожидания  
  Возвращает: Optional<GuiTestObject>

- `waitCondition(String description, int waitTimeMillis, int waitTimeCount, BiConsumer<Integer, Integer> consumer)`  
  Универсальный метод ожидания условия.  
  Параметры:  
  - `description` - описание условия  
  - `waitTimeMillis` - интервал между проверками (мс)  
  - `waitTimeCount` - количество попыток  
  - `consumer` - проверяемое условие  
  Возвращает: boolean (true - условие выполнено)

## Dependencies
- `Helper` (bugbusters.modules.helper)
- `GuiTestObject` (com.rational.test.ft)
- `Element` (userInterface.model.extend.rft)
- `MoreThan1WereFoundException` (userInterface.utils.TestObjectFinder)

## Usage example
```java
Element loginButton = new Element(...);
Optional<GuiTestObject> button = TestObjectWaiter.waitTestObject(loginButton);

// Ожидание кастомного условия
boolean found = TestObjectWaiter.waitCondition(
    "Ожидание элемента",
    1000, 
    5,
    (i, limit) -> { if (!element.exists()) throw new RuntimeException(); }
);
```

## Uncertainties
1. Назначение системного свойства `TestObjectWaiter.printAttemptNumberPer.times`
2. Поведение при одновременном возникновении разных исключений
3. Логика сброса счетчика в основном цикле ожидания
4. Назначение метода `main` (отладочный или производственный)