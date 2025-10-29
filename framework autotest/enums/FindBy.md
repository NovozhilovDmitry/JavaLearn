src\main\java\enums
# FindBy.java

## Summary
Комплексный enum для стратегий поиска UI-элементов в Swing-приложениях. Содержит основные стратегии поиска и вложенные enum'ы для конкретных типов компонентов. Используется для локации элементов по классу, тексту, имени и другим атрибутам.

## Public методы
1. **getValue()**  
   Возвращает строковое значение стратегии поиска. Не имеет параметров.

## Вложенные enum'ы и интерфейсы
- **ConstantClass**: интерфейс для enum'ов классов компонентов
- **ButtonClass, LabelClass, ComboBoxClass, ListClass, TableClass, TextFieldClass, CheckBox_RadBtn_Class, TabClass, TabPanelClass, ProgressBarClass**: enum'ы конкретных UI-компонентов
- **TextValue**: enum с русскоязычными текстами для поиска

## Dependencies
Нет внешних зависимостей.

## Usage Example
```java
// Поиск по классу
FindBy.ButtonClass buttonClass = FindBy.ButtonClass.AsdcoJButton;

// Поиск по тексту
FindBy.TextValue textValue = FindBy.TextValue.Да;

// Использование в локаторе
String locator = buttonClass.getcls() + "=" + buttonClass.getValue();
```

## Uncertainties
1. Некоторые значения в enum'ах могут быть устаревшими или неиспользуемыми
2. Наличие закомментированного поля ComboBoxClass может указывать на недоделанную функциональность
3. Неясно, как именно используются эти константы в фреймворке автоматизации
4. Некоторые имена классов выглядят сгенерированными (FrmGate$7, FrmNsi$26)