src\main\java\userinterface\impl
## RFT_Button.java

### Summary
Класс реализует функциональность кнопки в UI-тестах с использованием Rational Functional Tester. Обеспечивает основные операции: клик, проверки видимости и доступности элемента. Включает механизм повторных попыток нажатия при временной недоступности элемента с детализированным логированием.

### Public методы
- `click()` - Выполняет нажатие на кнопку с проверкой видимости и доступности
- `getText()` - Возвращает текст кнопки (из свойства "text" или "toolTipText")
- `isEnabled()` - Проверяет, доступна ли кнопка для взаимодействия
- `isVisible()` - Проверяет, видима ли кнопка в интерфейсе
- `isEnabled(boolean state)` - Проверяет соответствие состояния доступности ожидаемому
- `isVisible(boolean state)` - Проверяет соответствие состояния видимости ожидаемому

### Dependencies
- `com.rational.test.ft` (WindowActivateFailedException, CoordinateOffScreenException, NotYetAbleToPerformActionException)
- `userInterface.Button`
- `userInterface.model.extend.rft.Element`
- `base.App`, `base.PropertyBuilder`
- `bugbusters.modules.helper.Helper`

### Usage Example
```java
RFT_Button button = new RFT_Button(parent, properties);
button.click();
button.isEnabled(true);
button.isVisible();
```

### Uncertainties
1. Неясна логика условия в цикле попыток клика (комментарий "todo: внимательно посмотреть реализацию")
2. Назначение App.terminate_excel(false) при NotYetAbleToPerformActionException
3. Логика обработки различных исключений при клике требует дополнительного анализа