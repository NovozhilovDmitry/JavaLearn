src\main\java\model
# Operations.java

## Summary
Контейнер для хранения операций, расширяющий HashSet<Operation>. Отличается от стандартного Set тем, что при итерации операции сортируются по приоритету в порядке возрастания. Обеспечивает выполнение операций в порядке их приоритета.

## Public методы
1. **forEach(Consumer<? super Operation> action)**  
   Переопределенный метод для итерации по операциям, отсортированным по приоритету.

## Dependencies
- model.Operation
- java.util.HashSet
- java.util.function.Consumer

## Usage Example
```java
Operations operations = new Operations();
operations.add(new Operation("Операция 1", () -> {}, 2));
operations.add(new Operation("Операция 2", () -> {}, 1));

// Операции выполнятся в порядке: Операция 2, затем Операция 1
operations.forEach(op -> op.action.run());
```

## Uncertainties
1. Наследование от HashSet может быть не оптимальным для частых сортировок
2. Нет методов для массового добавления операций
3. Не предусмотрена обработка дубликатов по описанию (используется HashSet логика)