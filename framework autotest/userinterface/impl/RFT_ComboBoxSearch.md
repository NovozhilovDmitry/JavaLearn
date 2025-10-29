src\main\java\userinterface\impl
# RFT_ComboBoxSearch.java

## Summary
Класс реализует выпадающий список с функцией поиска. Позволяет выбирать значения путем ввода текста с последующим автоматическим завершением. Обрабатывает исключения при вводе символов вне кодовой страницы и предоставляет методы проверки выбранного значения.

## Public методы
- `chooseByType(String значение)` - Выбирает значение путем ввода текста в поле. Параметр: текст для поиска. Может выбросить исключение при невозможности ввода.
- `проверить_значение(String value)` - Проверяет, что текущее значение содержит указанную строку. Падает с ошибкой при несовпадении.

## Dependencies
- `com.rational.test.ft.*` (Rational Functional Tester)
- `userInterface.ComboBoxSearch`
- `java.awt.Point`
- `org.junit.jupiter.api.Assertions`

## Usage example
```java
RFT_ComboBoxSearch combo = new RFT_ComboBoxSearch("Города", container, properties);
combo.chooseByType("Москва");
combo.проверить_значение("Москва");
```

## Uncertainties
- Неясна логика обработки StringNotInCodePageException
- Назначение переменных count и retries не реализовано
- Не определен родительский класс RFT_ComboBox