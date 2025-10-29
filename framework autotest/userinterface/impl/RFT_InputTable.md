src\main\java\userinterface\impl
# RFT_InputTable.java

## Summary
Класс представляет таблицу с возможностью ввода данных в ячейки. Реализует функционал редактирования ячеек через имитацию кликов и ввода с клавиатуры. Использует Robot для эмуляции нажатий клавиш.

## Public методы
- `ввести_данные_в_ячейки_строки(String column, String value, String... args)` - Вводит значение в указанную колонку и строку. Параметры: название колонки, значение, дополнительные аргументы для идентификации строки.

## Dependencies
- `java.awt.Robot`
- `java.awt.event.KeyEvent`
- `com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject`

## Usage example
```java
RFT_InputTable table = new RFT_InputTable(parent, properties);
table.ввести_данные_в_ячейки_строки("Цена", "1000", "1");
```

## Uncertainties
- Не определены методы родительского класса RFT_Table
- Логика определения строки по args не ясна
- Возможные проблемы с многопоточностью при использовании Robot