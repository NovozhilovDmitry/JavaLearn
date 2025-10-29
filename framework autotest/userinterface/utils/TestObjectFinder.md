src\main\java\userinterface\utils
# TestObjectFinder.java

## Summary
Класс предоставляет утилиты для поиска тестовых объектов в GUI. Он позволяет находить элементы по заданным свойствам, как от корневого объекта, так и от указанного родительского элемента. Включает обработку различных сценариев поиска: когда элементов не найдено, найдено несколько, или требуется рекурсивный обход.

## Public методы
- `findTestObjects(TestObject parentObject, PropertyBuilder properties)`  
  Поиск элементов по свойствам от родительского элемента (включая всех потомков).  
  Параметры:  
  - `parentObject` - родительский элемент (если null, используется корневой)  
  - `properties` - свойства для поиска  
  Возвращает: массив найденных TestObject

- `findTestObjectsDirectly(TestObject parentObject, PropertyBuilder properties)`  
  Поиск элементов только среди непосредственных потомков родителя.  
  Параметры: аналогично `findTestObjects`  
  Возвращает: массив TestObject или пустой массив если родитель null

- `findParentAndAllChildren(TestObject parentObject)`  
  Рекурсивный поиск всех потомков включая сам родительский объект.  
  Параметры: `parentObject` - стартовый элемент  
  Возвращает: List<TestObject>

- `getAllChildren(TestObject parentObject)`  
  Получение потока всех потомков (исключая родителя).  
  Параметры: `parentObject` - родительский элемент  
  Возвращает: Stream<TestObject>

- `findTestObject(PropertyBuilder properties)`  
  Поиск одного элемента от корневого объекта.  
  Параметры: `properties` - свойства для поиска  
  Исключения: `NothingFoundException`, `MoreThan1WereFoundException`  
  Возвращает: GuiTestObject

- `findTestObject(PropertyBuilder properties, int index)`  
  Поиск элемента по индексу от корневого объекта.  
  Параметры:  
  - `properties` - свойства для поиска  
  - `index` - индекс элемента  
  Исключения: `NothingFoundException`  
  Возвращает: GuiTestObject

- `findTestObject(TestObject parentObject, PropertyBuilder properties, int index)`  
  Расширенный поиск с указанием родителя и индекса.  
  Параметры:  
  - `parentObject` - родительский элемент  
  - `properties` - свойства для поиска  
  - `index` - индекс элемента (-1 для автоматического выбора)  
  Исключения: `NothingFoundException`, `MoreThan1WereFoundException`  
  Возвращает: GuiTestObject

## Dependencies
- `PropertyBuilder` (base)
- `Helper` (bugbusters.modules.helper)
- `GuiTestObject`, `RootTestObject`, `TestObject` (com.rational.test.ft)
- `SubitemFactory` (static import)

## Usage example
```java
PropertyBuilder props = new PropertyBuilder().add("text", "Submit");
GuiTestObject button = TestObjectFinder.findTestObject(props);

// Поиск от конкретного родителя
TestObject parentWindow = ...;
GuiTestObject childElement = TestObjectFinder.findTestObject(
    parentWindow, 
    new PropertyBuilder().add("type", "Button"), 
    0
);
```

## Uncertainties
1. Неясна разница между `atDescendant` и `atList` в методах поиска
2. Закомментированная альтернативная реализация `findTestObjects` с рекурсивным обходом
3. Поведение при index < 0 в `findTestObject` с массивом результатов