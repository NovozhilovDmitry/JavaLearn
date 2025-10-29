src\main\java\userinterface\impl
## RFT_CheckBox_RadioButton.java

### Summary
Класс реализует работу с элементами CheckBox и RadioButton. Поддерживает операции переключения состояния, проверки выбранного состояния и проверки доступности/недоступности элемента. Использует механизм ожидания закрытия pop-up окон после действий.

### Public методы
- `click(boolean Отметка)` - Устанавливает состояние элемента (выбран/не выбран)
- `isSelected()` - Возвращает текущее состояние элемента
- `isEnabled()` - Проверяет, что элемент доступен для взаимодействия
- `isDisabled()` - Проверяет, что элемент недоступен для взаимодействия
- `getTestObject()`, `getFreshTestObject()` - Возвращают тестовый объект ToggleGUITestObject

### Dependencies
- `com.rational.test.ft.object.interfaces.ToggleGUITestObject`
- `userInterface.CheckBox_RadioButton`
- `userInterface.model.extend.rft.Element`
- `base.Base`, `base.PropertyBuilder`

### Usage Example
```java
RFT_CheckBox_RadioButton checkbox = new RFT_CheckBox_RadioButton(parent, properties);
checkbox.click(true);
checkbox.isSelected();
checkbox.isEnabled();
```

### Uncertainties
1. Назначение и реализация Base.main.waitPopUpClose()
2. Логика определения elementName() через свойство "label" может требовать доработки
3. Отсутствует явная обработка случаев, когда элемент не поддерживает свойство "selected"