src\main\java\utils\XML
# Tag.java

## Описание
Устаревший класс для работы с XML-тегами в автотестах. Представляет собой модель тега с атрибутами, текстовым содержимым и вложенными тегами. Обеспечивает сравнение тегов, поиск по имени и извлечение атрибутов. Класс помечен как deprecated и должен быть заменен более современными решениями.

## Public методы
- `Tag(String name, String[] attributes, String text, Tag... innerTags)` - конструктор с массивом атрибутов
- `getAsElement(Document doc)` - преобразует тег в DOM Element
- `compareTo(Tag tag)` - сравнивает два тега (выбрасывает исключение при различиях)
- `static compare(Tag tag1, Tag tag2)` - статический метод сравнения двух тегов с прикреплением в Allure
- `getInnerTagsByName(String name)` - возвращает вложенные теги по имени
- `get_значение_атрибута(String attr_name)` - ищет значение атрибута в текущем и вложенных тегах
- `equals(Object other)` - сравнение тегов на равенство

## Зависимости
- `io.qameta.allure.Step` - для аннотаций Allure
- `org.w3c.dom.*` - для работы с DOM
- `base.XlsChecker` - утилиты для проверки XLS
- `bugbusters.modules.extensions.allure.utils.Attachments` - вложения в Allure

## Пример использования
```java
Tag address = new Tag("Address", new String[]{"city=Moscow", "street=Lenina"}, "");
Tag person = new Tag("Person", new String[]{"id=123"}, "John Doe", address);
person.compareTo(expectedPerson);
```

## Неясности
1. Назначение поля `attribute_exceptions` - почему именно эти атрибуты исключаются из сравнения?
2. Логика работы `without_exceptions()` - как именно фильтруются атрибуты
3. Поведение при сравнении тегов с разной структурой вложенности
4. Назначение флага `isRequired` - не используется в предоставленном коде