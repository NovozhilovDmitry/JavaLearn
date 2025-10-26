src\main\java\base
# XmlFileChecker.java - Документация

## Summary
Класс `XmlFileChecker` предоставляет комплексную функциональность для работы с XML-файлами, включая валидацию по XSD-схемам, сравнение с эталонами и расширенные возможности редактирования. Он поддерживает автоматическое определение кодировок, замену динамических значений (даты, UUID) и интеграцию с системой отчетов Allure. Класс использует JSoup для парсинга и манипуляции XML-структурами.

## Public методы
- **Конструкторы** - поиск XML-файлов с поддержкой кодировок и валидации схем
- **`reference()`** - статические методы для загрузки эталонных файлов
- **`валидация_схемы(XmlTemplate album)`** - валидация XML по XSD-схеме
- **`check(XmlFile reference)`** - сравнение XML-файлов с эталоном
- **`extractEncoding(String filePath)`** - статический метод определения кодировки
- **`sessionModifying()`** - начинает сессию редактирования XML
- **`файл()`**, **`bytes()`** - базовые методы доступа

## Dependencies
- **`bugbusters.modules.fileWorker.xml.XmlFile`** - базовый класс для работы с XML
- **`org.jsoup`** - парсинг и манипуляция XML/HTML
- **`javax.xml.validation`** - валидация XSD-схем
- **`enums.XmlTemplate`** - перечисление XML-шаблонов и схем
- **Внешние**: `java.io.*`, `java.nio.file.*`, `org.slf4j.Logger`

## Usage Example
```java
XmlFileChecker checker = new XmlFileChecker("document", XmlTemplate.ED_ALBUM, Шлюзы.ШЛЮЗ_ПИС);
XmlFileChecker reference = XmlFileChecker.reference("etalon.xml", XmlTemplate.ED_ALBUM);
checker.check(reference);

// Редактирование XML
checker.sessionModifying()
    .tag("DocumentID")
    .replaceText("new_document_id")
    .replaceAttr("version", "2.0")
    .save();
```

## Uncertainties
1. **Определение кодировки** - эвристический метод может быть ненадежным для сложных случаев
2. **Рефлексия в complete()** - изменение final поля через reflection может быть проблематично
3. **Обработка больших XML** - парсинг всего документа в память может быть затратным
4. **Валидация схем** - отсутствие схемы не считается ошибкой, только предупреждение