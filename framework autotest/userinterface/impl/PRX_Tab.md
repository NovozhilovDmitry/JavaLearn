src\main\java\userinterface\impl
## PRX_Tab.java

**Summary**  
Класс PRX_Tab реализует работу с вкладками интерфейса. Позволяет выбирать вкладки по имени, получать информацию о текущей вкладке и работать с тестовыми объектами. Наследует функциональность контейнера прокси-объектов.

**Public методы**
- `PRX_Tab(Container parent, PropertyBuilder properties)` - конструктор
- `выбратьВкладку(String выбраннаяВкладка)` - выбирает указанную вкладку
- `isSelected()` - проверяет, выбрана ли вкладка
- `getName()` - возвращает имя вкладки
- `getIndex()` - возвращает индекс вкладки
- `getTestObject()` - возвращает тестовый объект вкладки
- `getFreshTestObject()` - возвращает обновленный тестовый объект

**Dependencies**
- `userInterface.Tab` (интерфейс)
- `userInterface.model.extend.prx.ProxyContainer` (родительский класс)
- `com.rational.test.ft.object.interfaces.GuiSubitemTestObject`
- `base.PropertyBuilder`

**Usage Example**
```java
PRX_Tab tab = new PRX_Tab(parentContainer, properties);
tab.выбратьВкладку("Основная");
String tabName = tab.getName();
```

**Uncertainties**
- Не указано, как обрабатываются случаи отсутствия вкладки
- Неясно, влияет ли выбор вкладки на другие элементы интерфейса