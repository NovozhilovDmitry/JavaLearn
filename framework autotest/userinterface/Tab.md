src\main\java\userinterface
## Tab.java

**Расположение:** `userInterface.Tab`

**Описание:**
Интерфейс Tab представляет вкладку в пользовательском интерфейсе, сочетая функциональность контейнера и GUI объекта. Предоставляет методы для выбора вкладки, проверки состояния, получения свойств и работы с тестовыми объектами. Поддерживает устаревший метод выбора вкладки по имени.

**Public методы:**
- `void выбратьВкладку(String выбраннаяВкладка)` - ⚠️ УСТАРЕЛО: выбирает вкладку по имени
- `boolean isSelected()` - проверяет, выбрана ли вкладка в данный момент
- `String getName()` - возвращает имя вкладки (accessibleName)
- `int getIndex()` - возвращает индекс вкладки (отсчет с 0)
- `GuiSubitemTestObject getTestObject()` - возвращает тестовый объект вкладки
- `GuiSubitemTestObject getFreshTestObject()` - возвращает обновленный тестовый объект

**Зависимости:**
- `com.rational.test.ft.object.interfaces.GuiSubitemTestObject` - тестовый объект Rational Functional Tester
- `userInterface.model.abstr.GuiObject` - базовый GUI объект
- `userInterface.model.extend.interf.Container` - интерфейс контейнера

**Пример использования:**
```java
Tab tab = findTab();
if (!tab.isSelected()) {
    // Использование альтернативного метода вместо устаревшего
    tab.getTestObject().click();
}
String tabName = tab.getName();
```

**Неясности:**
- Причина устаревания метода `выбратьВкладку()` и рекомендуемая замена
- Разница между `getTestObject()` и `getFreshTestObject()`
- Особенности работы с индексами вкладок в разных контекстах