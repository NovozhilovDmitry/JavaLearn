src\main\java\userinterface\impl
## PRX_CheckBox_RadioButton.java

**Summary**:  
Прокси-класс для работы с чекбоксами и радиокнопками. Реализует интерфейс CheckBox_RadioButton и предоставляет методы для управления состоянием элементов (выбор/снятие) и проверки их текущего состояния.

**Public методы**:
- `click(boolean отметка)` - устанавливает состояние элемента (выбран/не выбран)
- `isSelected()` - проверяет, выбран ли элемент
- `isEnabled()` - проверяет активность элемента
- `isDisabled()` - проверяет неактивность элемента
- `getTestObject()` - возвращает тестовый объект RFT
- `getFreshTestObject()` - возвращает обновленный тестовый объект RFT

**Dependencies**:
- `RFT_CheckBox_RadioButton` - оборачиваемый класс
- `ToggleGUITestObject` - интерфейс тестового объекта RFT
- `PropertyBuilder` - построитель свойств

**Usage example**:
```java
PRX_CheckBox_RadioButton checkbox = new PRX_CheckBox_RadioButton(parent, properties);
checkbox.click(true); // выбрать
boolean isSelected = checkbox.isSelected();
```

**Uncertainties**:
- Неясна реализация `methodFromWrappedRFTImpl()`
- Методы `isEnabled()` и `isDisabled()` возвращают `RFT_CheckBox_RadioButton` вместо boolean