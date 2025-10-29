
## CheckBox_RadioButton.java

**Summary:**  
Интерфейс `CheckBox_RadioButton` описывает поведение элементов управления: чекбоксов и радиокнопок. Он позволяет управлять состоянием отметки, проверять доступность элемента и взаимодействовать с низкоуровневым тестовым объектом `ToggleGUITestObject`.

**Public методы:**
- `click(boolean отметка)` — устанавливает состояние отметки (true — отмечено, false — снято).
- `isSelected()` — возвращает текущее состояние отметки.
- `isEnabled()` — проверяет, доступен ли элемент. Возвращает сам объект.
- `isDisabled()` — проверяет, недоступен ли элемент. Возвращает сам объект.
- `getTestObject()` — возвращает связанный тестовый объект.
- `getFreshTestObject()` — возвращает актуальный тестовый объект.

**Dependencies:**
- `userInterface.model.abstr.GuiObject`
- `com.rational.test.ft.object.interfaces.ToggleGUITestObject`
- `userInterface.impl.RFT_CheckBox_RadioButton`

**Usage example:**
```java
CheckBox_RadioButton checkbox = ... // получение элемента
checkbox.click(true);
boolean selected = checkbox.isSelected();
ToggleGUITestObject testObj = checkbox.getTestObject();
```

**Uncertainties:**
- Непонятно, чем отличается `getTestObject()` от `getFreshTestObject()`.
- Почему `isEnabled()` и `isDisabled()` возвращают сам объект, а не boolean?