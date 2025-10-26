src\main\java\utils
# ConcurrentUtils.java

## Summary
Утилитный класс для управления параллельным выполнением задач. Обеспечивает запуск и ожидание завершения набора Runnable задач в рамках заданного таймаута. Использует CompletableFuture для асинхронного выполнения и предоставляет перегруженные методы для разных сценариев использования.

## Public методы
1. **waitForAllTasksToComplete(Runnable... tasks)**  
   Ожидает выполнения всех переданных задач в течение 60 секунд. Параметры: tasks - массив задач для выполнения.

2. **waitForAllTasksToComplete(List<CompletableFuture<?>> tasks)**  
   Ожидает выполнения всех переданных CompletableFuture задач в течение 60 секунд.

3. **waitForAllTasksToComplete(int timeLimitMillis, CompletableFuture<?>... tasks)**  
   Ожидает выполнения всех переданных задач в течение заданного времени. Параметры: timeLimitMillis - лимит времени в миллисекундах, tasks - массив CompletableFuture задач.

## Dependencies
- Java Concurrency (CompletableFuture, ExecutionException, TimeoutException)
- SLF4J (логирование)

## Usage Example
```java
// Параллельное выполнение нескольких задач
ConcurrentUtils.waitForAllTasksToComplete(
    () -> выполнитьОперацию1(),
    () -> выполнитьОперацию2(),
    () -> выполнитьОперацию3()
);

// С пользовательским таймаутом
CompletableFuture<?> future1 = CompletableFuture.runAsync(...);
CompletableFuture<?> future2 = CompletableFuture.runAsync(...);
ConcurrentUtils.waitForAllTasksToComplete(30_000, future1, future2);
```

## Uncertainties
1. Ошибки выполнения задач логируются, но не пробрасываются дальше
2. Фиксированный таймаут 60 секунд может быть недостаточным для некоторых операций
3. Нет возможности отменить выполнение задач при превышении таймаута