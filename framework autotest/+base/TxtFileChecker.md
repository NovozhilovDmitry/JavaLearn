src\main\java\base
# TxtFileChecker.java - Документация

## Summary
Класс `TxtFileChecker` предоставляет функциональность для проверки текстовых файлов в тестовом фреймворке. Он поддерживает поиск файлов в шлюзах, сравнение содержимого с эталонными файлами, модификацию строк и выгрузку результатов. Класс интегрирован с системой отчетов Allure и включает механизмы обработки различных кодировок и преобразования HTML-символов.

## Public методы
- **Конструкторы** - поиск файлов в шлюзах по части имени с поддержкой кодировок
- **`reference(String имя_эталонного_файла)`** - статические методы для загрузки эталонных файлов
- **`searchFor(TxtFileChecker reference, boolean strict, boolean orderedRows)`** - сравнение файлов с эталоном
- **`getData()`** - возвращает содержимое в виде двумерного массива
- **`файл()`**, **`bytes()`** - реализация интерфейса FileChecker
- **`sessionModifying()`** - начинает сессию редактирования файла

## Dependencies
- **`bugbusters.modules.fileWorker.commons.arraysChecker.ArraysChecker`** - сравнение массивов данных
- **`utils.HTMLtoCyrillic`** - преобразование HTML-символов в кириллицу
- **`base.FileChecker`**, **`base.FileDKOeditor`** - базовые интерфейсы
- **`enums.Расширения_файлов`**, **`enums.Шлюзы`** - перечисления
- **Внешние**: `java.io.*`, `java.nio.file.*`, `org.slf4j.Logger`

## Usage Example
```java
TxtFileChecker checker = new TxtFileChecker("report", Расширения_файлов.TXT, Шлюзы.ШЛЮЗ_ПИС);
TxtFileChecker reference = TxtFileChecker.reference("etalon.txt");
checker.searchFor(reference, true, true);

// Редактирование файла
checker.sessionModifying()
    .replaceExistingRow(2, "новое значение")
    .save();
```

## Uncertainties
1. **Кодировки по умолчанию** - UTF-8 жестко закодирован в некоторых конструкторах
2. **Обработка больших файлов** - загрузка всего файла в память может быть проблемой
3. **Безопасность сравнения** - условие `FilesChecker.safetyCheck` не используется в этом классе
4. **Формат дат** - замена дат на форматы зависит от операционного дня MainClass