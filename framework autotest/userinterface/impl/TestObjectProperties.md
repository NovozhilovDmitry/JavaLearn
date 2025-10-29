src\main\java\userinterface\impl
## Анализ класса TestObjectProperties.java

### Summary
Утилитарный класс для отладочной информации о свойствах RFT-объектов. Предоставляет методы форматированного вывода свойств, метаданных и табличных данных тестовых объектов. Используется преимущественно для диагностики проблем в тестах.

### Public методы
- `printProperties(Hashtable properties)` - форматирует свойства в строку
- `printTestObjectProperties(TestObject obj)` - выводит полные свойства объекта
- `printTableDataProperties(GuiSubitemTestObject)` - выводит свойства табличных данных
- `printTableData(TestObject)` - форматирует содержимое таблицы
- `printTableData1(TestObject)` - альтернативная версия для таблиц

### Dependencies
- `RationalTestScript` (базовый класс RFT)
- `TestObject`, `GuiSubitemTestObject` (RFT-объекты)
- `ITestDataTable`, `ITestDataText` (RFT-интерфейсы данных)

### Usage example
```java
String props = TestObjectProperties.printProperties(textField.getProperties());
System.out.println("Свойства поля: " + props);
```

### Uncertainties
- Разница между printTableData и printTableData1 не очевидна
- Логика обработки разных типов ITestData требует упрощения
- Назначение messageHeader неясно (поле не используется)