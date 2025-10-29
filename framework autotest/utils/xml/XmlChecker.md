src\main\java\utils\XML
# XmlChecker.java

## Описание
Устаревший класс для поиска, парсинга и валидации XML-файлов в шлюзах автотестов. Обеспечивает загрузку XML из файлов и строк, валидацию по XSD-схемам, сравнение XML и извлечение данных через XPath. Интегрирован с системой шлюзов и шаблонов XML.

## Public методы
- `XmlChecker(String fileNamePart, XmlTemplate album, Расширения_файлов extension, Шлюзы... шлюзы)` - конструктор с поиском файла
- `сравнитьXML(String ожидаемый_xml, String проверяемый_xml)` - сравнение двух XML строк
- `static fromHTML(Path fullFileName)` - создание из HTML файла
- `static fromHTML(String html_content)` - создание из HTML строки
- `получитьXMLизСтроки(String xml_content)` - парсинг XML из строки
- `получитьЗначениеЧерезXpath(String xpath_выражение)` - извлечение значения через XPath
- `проверить_по_шаблону(NewXML album)` - проверка XML против шаблона
- `изменить_значение_атрибутов(String имяАтрибута, String новоеЗначение)` - массовое изменение атрибутов

## Зависимости
- `org.custommonkey.xmlunit.*` - утилиты сравнения XML
- `enums.XmlTemplate` - перечисление шаблонов XML
- `utils.FileSearcher` - поиск файлов
- `javax.xml.*` - стандартные XML утилиты Java

## Пример использования
```java
XmlChecker checker = new XmlChecker("ED410_", XmlTemplate.ED410, XML, Шлюзы.ШЛЮЗ1);
checker.проверить_по_шаблону(expectedAlbum);
String value = checker.получитьЗначениеЧерезXpath("//igr:File/@igr:fileIdentity");
```

## Неясности
1. Логика работы `getXpathNamespaceContext()` - жестко закодированные namespace
2. Алгоритм поиска файлов в шлюзах - как определяется приоритет шлюзов
3. Обработка ошибок валидации - какие исключения ожидаются
4. Работа с датами в `изменить_значение_атрибута()` - преобразование форматов