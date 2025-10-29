src\main\java\utils\gates
# GatesCleaner.java

## Описание
Вспомогательный класс для очистки файлов в шлюзах. Поддерживает многопоточное удаление файлов по заданным критериям (регулярные выражения имён файлов и расширения). Автоматически закрывает процессы Excel перед очисткой и повторяет попытки удаления в течение заданного таймаута.

## Public методы
- `clearDownloadedForms(Шлюзы... gates)` - статический метод для очистки всех или указанных шлюзов
- `clearDownloadedForms(GatesCleaner cleaner, Шлюзы... gates)` - статический метод с кастомной конфигурацией
- `withTimeoutMillis(int timeoutMillis)` - установка таймаута операций (мс)
- `withFileNamesRegex(String fileNamesRegex)` - установка regex для обязательного удаления файлов
- `withIncExtS(Расширения_файлов... incExtS)` - фильтр по расширениям файлов
- `withErrorsKeeper(ErrorsKeeper errorsKeeper)` - кастомный обработчик ошибок
- `clear(List<Gate> gates)` - основной метод очистки списка шлюзов

## Зависимости
- `base.App` - для завершения процессов Excel
- `bugbusters.modules.helper.utils.StringUtils` - утилиты строк
- `enums.Расширения_файлов` - перечисление расширений
- `enums.Шлюзы` - перечисление шлюзов  
- `model.Gate` - модель шлюза
- `utils.ConcurrentUtils` - многопоточные утилиты

## Пример использования
```java
// Базовая очистка всех шлюзов
GatesCleaner.clearDownloadedForms();

// Кастомная очистка
GatesCleaner cleaner = new GatesCleaner()
    .withTimeoutMillis(5000)
    .withFileNamesRegex("Form0(11|22).*");
GatesCleaner.clearDownloadedForms(cleaner, Шлюзы.ШЛЮЗ1, Шлюзы.ШЛЮЗ2);
```

## Неясности
1. Логика повторных попыток удаления - неясно как именно работает цикл while с таймаутом
2. Поведение `App.terminate_excel(false)` - что делает параметр false?
3. Ожидание задач - как `ConcurrentUtils.waitForAllTasksToComplete` обрабатывает исключения