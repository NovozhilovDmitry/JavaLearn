src\main\java\userinterface\model\extend\rft
## ElementContainer.java

**Summary**  
Абстрактный класс-наследник Element для GUI-элементов, имеющих как родителя, так и потомков. Не добавляет новой логики, служит маркером контейнерных элементов и реализует интерфейс Container.

**Public Methods**  
- `ElementContainer(Container parent, PropertyBuilder properties)` – конструктор, делегирующий вызов родительскому классу Element.

**Dependencies**  
- `base.PropertyBuilder`  
- `userInterface.model.extend.interf.Container`  
- Родительский класс `Element`  

**Usage Example**  
```java
PropertyBuilder tabProps = new PropertyBuilder()
    .add("title", "Main");
RFT_Tab mainTab = new RFT_Tab(parentWindow, tabProps);
```

**Uncertainties**  
- Какие конкретные методы управления потомками добавляются в реализациях (не показано в коде).  
- Отличия в поведении между `Element` и `ElementContainer` на уровне фреймворка.  