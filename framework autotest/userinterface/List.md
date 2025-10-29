src\main\java\userinterface
## List.java

**Summary**  
Интерфейс List представляет элемент списка в пользовательском интерфейсе с методами для взаимодействия с элементами списка. Поддерживает операции проверки содержания текста, подсчета элементов и клика по конкретным элементам. Возвращает различные представления данных списка.

**Public методы**
- `size()` - возвращает количество элементов
- `values()` - возвращает список значений в виде Java List
- `clickAtText(String text)` - кликает на элемент с указанным текстом
- `shouldContainText(String text)` - проверяет наличие текста в списке
- `shouldNotContainText(String text)` - проверяет отсутствие текста
- `shouldContainItemCount(int count)` - проверяет количество элементов
- `получение_значений()` - возвращает значения в строковом представлении
- `contains(String text)` - проверяет наличие текста (булево)

**Dependencies**
- `com.rational.test.ft.object.interfaces.SelectGuiSubitemTestObject`
- `userInterface.model.abstr.GuiObject`

**Usage example**
```java
List list = // получение экземпляра
list.clickAtText("Опция 1");
list.shouldContainItemCount(5);
```

**Uncertainties**
- Неясен формат возвращаемых данных в `получение_значений()`
- Не указано поведение при клике на несуществующий элемент