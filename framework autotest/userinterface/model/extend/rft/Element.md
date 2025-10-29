src\main\java\userinterface\model\extend\rft
## Element.java

**Summary**  
Абстрактный базовый класс для GUI-элементов без потомков в фреймворке RFT. Реализует ленивую инициализацию тестового объекта (GuiTestObject) при первом обращении. Обеспечивает механизмы поиска элементов через свойства (PropertyBuilder) и ожидания их появления в интерфейсе. Содержит систему слушателей (LifecycleListener) для отслеживания жизненного цикла элементов.

**Public Methods**  
- `Element(Container parent, PropertyBuilder properties)` – конструктор. Инициализирует родителя, свойства и запускает ожидание элемента.  
- `controlType(): Class<? extends GuiObject>` – возвращает интерфейс элемента (первый из реализованных).  
- `getTestObject(): GuiTestObject` – возвращает кэшированный тестовый объект.  
- `getFreshTestObject(): GuiTestObject` – возвращает новый тестовый объект (принудительный поиск в GUI).  
- `parent(): Container` – возвращает родительский контейнер.  
- `parentElement(): ElementContainer` – возвращает родителя как ElementContainer.  
- `getProperties(): PropertyBuilder` – возвращает свойства для поиска элемента.  
- `<Parent> parent(Class<Parent> type): Parent` – ищет ближайшего родителя указанного типа. Кидает `IllegalArgumentException` если родитель не найден.

**Dependencies**  
- `base.PropertyBuilder`  
- `com.rational.test.ft.object.interfaces.GuiTestObject`  
- `userInterface.model.extend.interf.Container`  
- `userInterface.utils.TestObjectFinder`, `TestObjectWaiter`  
- `userInterface.impl.RFT_Window`, `RFT_Button`  
- `org.junit.jupiter.api.Assertions` (для fail)  

**Usage Example**  
PropertyBuilder props = new PropertyBuilder()
    .add("text", "Submit");
RFT_Button button = new RFT_Button(parentWindow, props);
button.click();

**Uncertainties**  
- Назначение поля `index` и логика его использования в `TestObjectFinder`.  
- Механизм работы `TestObjectWaiter.waitTestObject` и критерии успешного поиска.  
- Роль `ProxyContainer` и процесс преобразования в RFT-реализацию.  
- Практическое применение `LifecycleListener` и кто его регистрирует.