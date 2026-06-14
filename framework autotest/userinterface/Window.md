src\main\java\userinterface
# Window.java

## Summary
Интерфейс для работы с окнами приложения. Содержит методы управления окнами: закрытие, перемещение, работа с модальными окнами, взаимодействие с деревьями элементов. Поддерживает интеграцию с формами из пакета forms.

## Public Methods
- `close()` - закрывает окно по крестику
- `move()` - сдвигает окно к левому верхнему углу экрана
- `getTitle()` - возвращает заголовок окна
- `isDescribedByForm()` - проверяет описано ли окно формой
- `form()` - возвращает связанную форму (может быть null)
- `isModal()` - проверяет является ли окно модальным
- `выбор_значения_в_дереве(String classIndex, String Путь)` - выбирает значение в дереве по индексу класса и пути
- `выбор_значения_в_дереве(String classIndex, String className, String Путь)` - выбирает значение в дереве с указанием класса
- `снять_отметку_в_дереве(String classIndex, String path)` - снимает отметку в дереве
- `getHandle()` - возвращает handle окна
- `getTestObject()`, `getFreshTestObject()` - наследуются от GuiObject

## Dependencies
- `com.rational.test.ft.object.interfaces.TopLevelTestObject`
- `forms.general.abstractForms.FormWin`
- `userInterface.model.abstr.GuiObject`
- `userInterface.model.extend.interf.Container`
- `java.util.LinkedList`
- `java.util.concurrent.CompletableFuture`

## Usage Example
```java
Window окно = // получение окна
окно.move();
String заголовок = окно.getTitle();
окно.выбор_значения_в_дереве("Tree1", "Узел1/Узел2");
окно.close();
```

## Uncertainties
- Назначение статического поля `LAST_FOUND_WINDOWS`
- Не ясно поведение `form()` когда `isDescribedByForm()` возвращает false
- Не указаны параметры для `classIndex` и `className` в методах работы с деревом
- Назначение deprecated метода `get()`