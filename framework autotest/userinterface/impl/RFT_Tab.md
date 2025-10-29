src\main\java\userinterface\impl
## RFT_Tab.java

### summary:
Класс реализует функционал для работы с вкладками в UI-автотестах. Предоставляет методы выбора вкладок, проверки состояния и навигации по дочерним элементам через внутреннюю систему панелей.

### public методы:
- `выбратьВкладку(String выбраннаяВкладка)` - выбирает вкладку по имени (бросает SubitemNotFoundException если вкладка не найдена)
- `isSelected()` - проверяет, выбрана ли текущая вкладка
- `getName()` - возвращает имя вкладки
- `getIndex()` - возвращает индекс вкладки
- `childrenSource()` - возвращает контейнер для поиска дочерних элементов

### dependencies:
- Rational FT: `GuiSubitemTestObject`, `NameSet`, `atText`
- Внутренние: `TabPanel`, `FindBy`, `PropertyBuilder`
- Вспомогательные: `TestObjectFinder`

### usage_example:
```java
RFT_Tab tab = new RFT_Tab(parent, properties);
tab.выбратьВкладку("Основные");
tab.isSelected();
```

### uncertainties:
- Сложная архитектура с внутренним классом TabPanel
- Механизм редиректа через `redirect()` может быть неочевидным
- Использование кириллических имен методов
- Назначение интерфейса Panel не полностью ясно