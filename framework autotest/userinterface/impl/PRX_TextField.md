src\main\java\userinterface\impl
## PRX_TextField.java

**Summary**  
Класс PRX_TextField реализует работу с текстовыми полями ввода. Предоставляет методы для ввода текста, проверки значений и состояния поля. Использует типизированные тестовые объекты для работы с текстовыми элементами GUI.

**Public методы**
- `PRX_TextField(String textFieldName, Container parent, PropertyBuilder properties)` - конструктор
- `ввести_текст(String keys)` - вводит текст в поле
- `проверить_не_пустое_значение()` - проверяет, что поле не пустое
- `проверить_значение(String value)` - проверяет конкретное значение поля
- `getValue()` - возвращает текущее значение поля
- `isEnabled()`, `isDisabled()` - проверяют состояние поля
- `getTestObject()`, `getFreshTestObject()` - возвращают тестовые объекты

**Dependencies**
- `userInterface.TextField` (интерфейс)
- `userInterface.model.extend.prx.Proxy` (родительский класс)
- `com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject`
- `base.PropertyBuilder`

**Usage Example**
```java
PRX_TextField textField = new PRX_TextField("username", parentContainer, properties);
textField.ввести_текст("testuser");
textField.проверить_значение("testuser");
String value = textField.getValue();
```

**Uncertainties**
- Не указано, как обрабатываются специальные клавиши при вводе
- Неясна разница между `getTestObject()` и `getFreshTestObject()`
- Отсутствует валидация вводимых данных