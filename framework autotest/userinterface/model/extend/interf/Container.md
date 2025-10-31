src\main\java\userinterface\model\extend\interf
## Container.java

### Summary
Интерфейс `Container` предоставляет методы для создания прокси-объектов элементов пользовательского интерфейса. Содержит default-реализации методов для удобного создания элементов с разными стратегиями поиска. Используется как базовый контейнер для организации Page Object паттерна в тестовом фреймворке.

### Public Methods
- `childrenSource()`: Возвращает контейнер для поиска дочерних элементов (по умолчанию - текущий контейнер)
- `checkBox_radioBtn()`: 3 перегрузки для создания чекбокса/радиокнопки
- `button()`: 3 перегрузки для создания кнопки
- `jump_button()`: 3 перегрузки для создания "прыгающей" кнопки (специальный тип кнопки)
- `textField()`: 4 перегрузки для создания текстового поля
- `comboBox()`: 3 перегрузки для создания выпадающего списка
- `list()`: 2 перегрузки для создания списка
- `comboBoxSearch()`: 3 перегрузки для создания комбинированного поля поиска
- `table()`: 2 перегрузки для создания таблицы
- `input_table()`: 3 перегрузки для создания таблицы ввода
- `label()`: 5 перегрузок для создания метки
- `tab()`: 2 перегрузки для создания вкладки
- `progressBar()`: 3 перегрузки для создания индикатора прогресса

### Dependencies
- `base.PropertyBuilder` - построитель свойств для поиска элементов
- `enums.FindBy` - перечисления классов элементов и стратегий поиска
- `userInterface.impl.*` - реализации прокси-классов элементов UI
- `userInterface.model.abstr.GuiObject` - абстрактный базовый класс

### Usage Example
```java
public class LoginPage implements Container {
    public PRX_TextField username = textField("Логин", 
        FindBy.TextFieldClass.TEXT_FIELD, FindBy.PriorLabel, "Имя пользователя");
    public PRX_Button submit = button(FindBy.ButtonClass.BUTTON, FindBy.TextValue.LOGIN);
}
```

### Uncertainties
1. Назначение `PRX_Jump_Button` - неясна специфика этого типа кнопки
2. Разница между `PRX_ComboBox` и `PRX_ComboBoxSearch`
3. Отличие `PRX_Table` от `PRX_InputTable`
4. Смысл параметра `FindBy.TextValue` в некоторых методах
5. Назначение "прыгающей кнопки" (jump button) в контексте UI