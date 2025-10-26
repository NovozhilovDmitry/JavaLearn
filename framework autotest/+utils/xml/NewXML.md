src\main\java\utils\XML
# NewXML.java

## Описание
Устаревший класс-наследник Tag для работы с XML-альбомами УФЭБС. Предоставляет функциональность для создания, сериализации и выгрузки XML-документов по шаблонам. Включает вложенный класс Table для парсинга HTML-таблиц из XML. Класс помечен как deprecated.

## Public методы
- `NewXML(XmlTemplate template, String tagName, String[] attributes, String text, Tag... innerTag)` - конструктор с шаблоном
- `NewXML(String xmlContent)` - конструктор из XML строки
- `выгрузить_файл(Шлюзы шлюз, String имяФайла, Расширения_файлов... exts)` - выгрузка в шлюз
- `выгрузить_файл(String имяФайла, Расширения_файлов... exts)` - выгрузка во временный каталог
- `static t1_contains_t2(List<LinkedHashMap<String, String>> t1, List<LinkedHashMap<String, String>> t2)` - сравнение таблиц
- `static map(String key1, String value1, String... keyValue_pair)` - создание мапы для таблиц

## Зависимости
- `org.apache.xml.serialize.*` - сериализация XML
- `base.NewTXT` - работа с текстовыми файлами
- `enums.Шлюзы` - перечисление шлюзов
- `org.apache.commons.lang.StringEscapeUtils` - обработка HTML

## Пример использования
```java
NewXML ed410 = new NewXML(XmlTemplate.ED410, "ED410", new String[]{}, "");
ed410.выгрузить_файл(Шлюзы.ШЛЮЗ1, "test_ed410", XML);

NewXML.Table table = new NewXML.Table(htmlContent);
List<LinkedHashMap<String, String>> data = table.table();
```

## Неясности
1. Назначение поля `standalone` - как влияет на генерацию XML
2. Логика работы `prepareString()` - сложная обработка HTML-строк
3. Алгоритм выгрузки файлов - копирование между временными каталогами и шлюзами
4. Работа с кодировками - почему используется windows-1251