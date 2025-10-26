src\main\java\utils\gates
# GatesCollector.java

## Описание
Утилита для сбора шлюзов и их абсолютных путей в виде ordered map. Преобразует относительные пути в абсолютные используя server_path, и сопоставляет их с enum шлюзов по наличию ключевых слов в путях.

## Public методы
- `collectAll(List<String> relativePaths)` - заполняет коллекцию на основе списка относительных путей

## Зависимости
- `enums.Шлюзы` - перечисление шлюзов
- `env.Environment` - для получения server_path

## Пример использования
```java
GatesCollector collector = new GatesCollector();
collector.collectAll(Arrays.asList("gate1/forms", "gate2/downloads"));
// Результат: {ШЛЮЗ1: "C:\server\gate1\forms\", ШЛЮЗ2: "C:\server\gate2\downloads\"}
```

## Неясности
1. Алгоритм сопоставления путей с enum - что если путь содержит несколько ключевых слов?
2. Обработка дубликатов - поведение при повторном вызове collectAll
3. Кодировка путей - как обрабатываются пути с не-ASCII символами