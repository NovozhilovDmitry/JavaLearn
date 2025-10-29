src\main\java\userinterface\impl
## RFT_List.java

### summary:
Класс реализует функционал для работы со списками в UI-автотестах через Rational Functional Tester. Предоставляет методы для получения значений элементов списка, проверки содержания текста, выбора элементов и проверки количества элементов. Включает парсинг значений списка с учетом специальных случаев форматирования.

### public методы:
- `size()` - возвращает количество элементов в списке
- `values()` - возвращает список строковых значений всех элементов
- `contains(String text)` - проверяет наличие текста в элементах списка
- `clickAtText(String text)` - кликает на элемент с указанным текстом (бросает SubitemNotFoundException если элемент не найден)
- `shouldContainItemCount(int count)` - проверяет соответствие количества элементов ожидаемому значению
- `shouldContainText(String text)` - проверяет наличие указанного текста в списке
- `shouldNotContainText(String text)` - проверяет отсутствие указанного текста в списке
- `получение_значений()` - возвращает текстовое представление всех значений списка

### dependencies:
- Rational FT: `SelectGuiSubitemTestObject`, `SubitemNotFoundException`
- Внутренние: `RFT_Window`, `PropertyBuilder`, `StringUtils`
- Тестирование: `org.junit.jupiter.api.Assertions.fail`

### usage_example:
```java
RFT_List myList = new RFT_List("СписокРегионов", window, properties);
myList.shouldContainItemCount(5);
myList.clickAtText("Москва");
myList.shouldContainText("Санкт-Петербург");
```

### uncertainties:
- Логика парсинга в `parseValues()` может быть хрупкой при сложных форматах данных
- Комментарий указывает на проблему несоответствия `.itemCount` и фактического количества распарсенных значений
- Использование кириллических имен методов может усложнить поддержку