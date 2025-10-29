src\main\java\userinterface\impl
## PRX_InputTable.java

**Summary**:  
Класс представляет прокси-объект для таблицы ввода данных. Наследует функциональность базовой таблицы PRX_Table и реализует интерфейс InputTable. Предоставляет метод для ввода данных в указанную ячейку строки таблицы с поддержкой логирования шагов в Allure-отчетах.

**Public методы**:
- `ввести_данные_в_ячейки_строки(String column, String value, String... args)`  
  Вводит значение в колонку указанной строки. Принимает название колонки, значение и аргументы для идентификации строки. Возвращает объект RFT_InputTable.

**Dependencies**:
- Родительский класс: `PRX_Table`
- Интерфейсы: `InputTable`, `Container`
- Вспомогательные классы: `PropertyBuilder`, `RFT_InputTable`
- Аннотации: `@Step` из библиотеки Allure

**Usage Example**:
```java
PRX_InputTable table = new PRX_InputTable(parentContainer, properties);
table.ввести_данные_в_ячейки_строки("Стоимость", "1000", "Заказ 1");
```

**Uncertainties**:
- Назначение параметров `args` для идентификации строки
- Реализация метода `methodFromWrappedRFTImpl` скрыта в родительском классе