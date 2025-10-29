src\main\java\base
# XlsFileChecker.java - Документация

## Summary
Класс `XlsFileChecker` расширяет функциональность для работы с XLS-файлами, предоставляя методы поиска, сравнения и редактирования Excel-документов. Он поддерживает валидацию по эталонным файлам, модификацию ячеек и объединенных областей, а также интеграцию с системой отчетов Allure. Класс автоматически управляет процессами Excel для предотвращения блокировок файлов.

## Public методы
- **Конструкторы** - поиск XLS-файлов в шлюзах по имени и листу
- **`reference()`** - статические методы для загрузки эталонных файлов
- **`searchFor()`** - несколько перегруженных методов для сравнения листов и диапазонов
- **`getCellValue(String address)`** - получение значения ячейки по адресу
- **`sessionModifying()`** - начинает сессию редактирования ячеек
- **`saveAs(String new_file_name)`** - сохранение под новым именем
- **`файл()`**, **`bytes()`**, **`getData()`** - базовые методы доступа

## Dependencies
- **`bugbusters.modules.fileWorker.xlsx.XlsxChecker`** - базовый класс для проверки XLSX
- **`org.apache.poi.hssf`** - библиотека для работы со старым форматом XLS
- **`base.FileChecker`**, **`base.FileDKOeditor`** - базовые интерфейсы
- **`enums.Шлюзы`** - перечисления шлюзов
- **Внешние**: `java.io.*`, `java.nio.file.*`, `org.slf4j.Logger`

## Usage Example
```java
XlsFileChecker checker = new XlsFileChecker("report", "Лист1", Шлюзы.ШЛЮЗ_ПИС);
XlsFileChecker reference = XlsFileChecker.reference("etalon.xls", "Лист1");
checker.searchFor(reference);

// Редактирование ячеек
checker.sessionModifying()
    .rewriteCell("A1", "новое значение")
    .rewriteCell("B2", "другое значение")
    .save();
```

## Uncertainties
1. **Наследование от XlsxChecker** - потенциальные проблемы совместимости XLS/XLSX
2. **Обработка объединенных ячеек** - сложная логика работы с merged regions
3. **Производительность** - переинициализация данных при выходе за границы
4. **Безопасность потоков** - работа с Excel требует закрытия процессов