# full_userinterface.md - Объединенная документация

## Объединенные файлы:
- Button.md
- CheckBox_RadioButton.md  
- ComboBox.md
- ComboBoxSearch.md
- InputTable.md
- Jump_Button.md
- Label.md
- List.md
- PaneTable.md
- ProgressBar.md
- Tab.md
- Table.md
- TextField.md
- Window.md
- impl/PRX_TextField.md
- impl/PRX_Button.md
- impl/PRX_CheckBox_RadioButton.md
- impl/PRX_ComboBox.md
- impl/PRX_ComboBoxSearch.md
- impl/PRX_InputTable.md
- impl/PRX_Jump_Button.md
- impl/PRX_Label.md
- impl/PRX_List.md
- impl/PRX_PaneTable.md
- impl/PRX_ProgressBar.md
- impl/PRX_Tab.md
- impl/PRX_Table.md
- impl/TestObjectProperties.md
- impl/RFT_Button.md
- impl/RFT_CheckBox_RadioButton.md
- impl/RFT_ComboBox.md
- impl/RFT_ComboBoxSearch.md
- impl/RFT_InputTable.md
- impl/RFT_Jump_Button.md
- impl/RFT_Label.md
- impl/RFT_List.md
- impl/RFT_PaneTable.md
- impl/RFT_ProgressBar.md
- impl/RFT_Tab.md
- impl/RFT_Table.md
- impl/RFT_TextField.md
- impl/RFT_Window.md
- model/abstr/GuiObject.md
- model/extend/interf/Container.md
- model/extend/prx/Proxy.md
- model/extend/prx/ProxyContainer.md
- model/extend/rft/ElementContainer.md
- model/extend/rft/Element.md
- utils/TestObjectWaiter.md
- utils/TestObjectFinder.md

---

## Button.java

**Summary:**  
Интерфейс `Button` определяет контракт для работы с кнопками в пользовательском интерфейсе. Он предоставляет методы для взаимодействия с кнопкой (клик), управления её состоянием (видимость, доступность) и получения текстовой метки. Интерфейс наследует базовые свойства от `GuiObject`.

**Public методы:**
- `click()` — выполняет действие клика по кнопке.
- `isEnabled(boolean state)` — устанавливает состояние доступности кнопки. Возвращает сам объект для цепочки вызовов.
- `isVisible(boolean state)` — устанавливает видимость кнопки. Возвращает сам объект для цепочки вызовов.
- `getText()` — возвращает текст, отображаемый на кнопке.
- `isVisible()` — проверяет, видима ли кнопка.
- `isEnabled()` — проверяет, доступна ли кнопка для взаимодействия.

**Dependencies:**
- `userInterface.model.abstr.GuiObject`

**Usage example:**
```java
Button button = ... // получение объекта кнопки
button.isVisible(true).isEnabled(true);
button.click();
String text = button.getText();
```

**Uncertainties:**
- Не указано, какие исключения могут возникать при вызове `click()`, если кнопка недоступна.
- Неясна логика работы методов `isEnabled(state)` и `isVisible(state)` — меняют ли они реальное состояние или только в рамках тестовой модели.

---

## CheckBox_RadioButton.java

**Summary:**  
Интерфейс `CheckBox_RadioButton` описывает поведение элементов управления: чекбоксов и радиокнопок. Он позволяет управлять состоянием отметки, проверять доступность элемента и взаимодействовать с низкоуровневым тестовым объектом `ToggleGUITestObject`.

**Public методы:**
- `click(boolean отметка)` — устанавливает состояние отметки (true — отмечено, false — снято).
- `isSelected()` — возвращает текущее состояние отметки.
- `isEnabled()` — проверяет, доступен ли элемент. Возвращает сам объект.
- `isDisabled()` — проверяет, недоступен ли элемент. Возвращает сам объект.
- `getTestObject()` — возвращает связанный тестовый объект.
- `getFreshTestObject()` — возвращает актуальный тестовый объект.

**Dependencies:**
- `userInterface.model.abstr.GuiObject`
- `com.rational.test.ft.object.interfaces.ToggleGUITestObject`
- `userInterface.impl.RFT_CheckBox_RadioButton`

**Usage example:**
```java
CheckBox_RadioButton checkbox = ... // получение элемента
checkbox.click(true);
boolean selected = checkbox.isSelected();
ToggleGUITestObject testObj = checkbox.getTestObject();
```

**Uncertainties:**
- Непонятно, чем отличается `getTestObject()` от `getFreshTestObject()`.
- Почему `isEnabled()` и `isDisabled()` возвращают сам объект, а не boolean?

---

## ComboBox.java

**Summary:**  
Интерфейс `ComboBox` представляет выпадающий список и предоставляет методы для выбора значений, проверки содержимого и управления состоянием элемента. Также позволяет получать список всех доступных пунктов и работать с тестовыми объектами типа `TextSelectGuiSubitemTestObject`.

**Public методы:**
- `выбрать_значение(String Значение)` — выбирает значение из списка по точному совпадению.
- `проверить_значение_не_пустое()` — проверяет, что выбранное значение не пустое.
- `проверить_значение(String Значение)` — проверяет, что выбранное значение соответствует ожидаемому.
- `выбрать_значение_содержащее(String Значение)` — выбирает значение, содержащее указанную подстроку.
- `проверитьДоступныеЗначенияСписка(String......)` — проверяет наличие указанных значений в списке.
- `isEnabled()` / `isDisabled()` — проверяют доступность элемента.
- `getValue()` — возвращает текущее выбранное значение.
- `items()` — возвращает список всех доступных значений.

**Dependencies:**
- `userInterface.model.abstr.GuiObject`
- `com.rational.test.ft.object.interfaces.TextSelectGuiSubitemTestObject`
- `userInterface.impl.RFT_ComboBox`

**Usage example:**
```java
ComboBox combo = ... // получение комбобокса
combo.выбрать_значение("Значение");
combo.проверить_значение("Значение");
List<String> items = combo.items();
```

**Uncertainties:**
- Как обрабатывается ситуация, когда значение не найдено в `выбрать_значение()`?
- Что происходит при вызове `проверить_значение_не_пустое()`, если значение пустое?

---

## ComboBoxSearch.java

**Summary:**  
Интерфейс-наследник `ComboBox`, добавляющий функциональность выбора значения через поиск или ввод текста. Используется для комбобоксов с возможностью поиска.

**Public методы:**
- `chooseByType(String значение)` — выбирает значение путем ввода текста (например, через поиск).

**Dependencies:**
- `userInterface.ComboBox`

**Usage example:**
```java
ComboBoxSearch searchCombo = ... // получение комбобокса с поиском
searchCombo.chooseByType("ЧастьЗначения");
```

**Uncertainties:**
- Чем отличается поведение `chooseByType()` от `выбрать_значение()`?
- Как обрабатывается ситуация, когда введённый текст не соответствует ни одному значению?

---

## InputTable.java

**Summary**  
Интерфейс InputTable расширяет базовый интерфейс Table и предоставляет методы для ввода данных в табличные элементы интерфейса. Использует кириллические названия методов для улучшения читаемости кода. Предназначен для работы с таблицами в автоматизированных тестах.

**Public методы**
- `ввести_данные_в_ячейки_строки(String column, String value, String... args)` - вводит данные в ячейки указанной строки таблицы
  - Параметры: 
    - `column` - колонка для ввода данных
    - `value` - значение для ввода
    - `args` - дополнительные аргументы (предположительно значения для других колонок)

**Dependencies**
- `userInterface.Table` (родительский интерфейс)
- `userInterface.impl.RFT_InputTable` (имплементация)

**Usage example**
```java
InputTable table = // получение экземпляра
table.ввести_данные_в_ячейки_строки("ФИО", "Иванов Иван", "Возраст", "30");
```

**Uncertainties**
- Неясна логика работы с переменным количеством аргументов `args`
- Не указаны возможные исключения при работе метода

---

## Jump_Button.java

**Summary**  
Интерфейс Jump_Button представляет специализированную кнопку навигации между страницами приложения. Поддерживает различные сценарии клика с возвратом экземпляров классов страниц и возможностью закрытия текущего окна. Использует дженерики для типобезопасной навигации.

**Public методы**
- `click(T existingClassInstance)` - клик с возвратом существующего экземпляра класса страницы
- `click(Class<T> new_Class_Instance)` - клик с созданием нового экземпляра класса страницы
- `click(Class<T> new_Class_Instance, Object... args)` - клик с созданием экземпляра с параметрами
- `clickAndClose(T existingClassInstance)` - клик с закрытием окна и возвратом существующего экземпляра
- `clickAndClose(Class<T> new_Class_Instance)` - клик с закрытием и созданием нового экземпляра
- `clickAndClose(Class<T> new_Class_Instance, Object... args)` - клик с закрытием и созданием экземпляра с параметрами
- `clickAndClose()` - клик с закрытием без возврата значения

**Dependencies**
- `userInterface.Button` (родительский интерфейс)

**Usage example**
```java
Jump_Button button = // получение экземпляра
MainPage mainPage = button.click(MainPage.class);
```

**Uncertainties**
- Неясна разница между передачей класса и экземпляра класса
- Не указано поведение при невозможности создания экземпляра класса

---

## Label.java

**Summary**  
Интерфейс Label представляет текстовые метки интерфейса с функциями проверки содержащегося текста и всплывающих подсказок. Предоставляет методы для валидации сообщений по частичному совпадению и получения текста с опциональным удалением HTML-тегов.

**Public методы**
- `проверка_сообщения_по_части_строки(String)` - проверяет наличие части текста в метке
- `проверка_отсутствия_сообщения_по_части_строки(String)` - проверяет отсутствие части текста
- `проверка_всплывающей_подсказки_по_части_строки(String)` - проверяет текст всплывающей подсказки
- `getText()` - возвращает текст метки (возможно с тегами)
- `getText(boolean removeTags)` - возвращает текст с опциональным удалением тегов
- `проверка_отсутствия_сообщений()` - проверяет полное отсутствие сообщений
- `getTestObject()`, `getFreshTestObject()` - возвращают тестовые объекты GUI

**Dependencies**
- `com.rational.test.ft.object.interfaces.GuiSubitemTestObject`
- `com.rational.test.ft.object.interfaces.GuiTestObject`
- `userInterface.model.abstr.GuiObject`

**Usage example**
```java
Label label = // получение экземпляра
label.проверка_сообщения_по_части_строки("успешно");
String cleanText = label.getText(true);
```

**Uncertainties**
- Неясно, что именно считается "сообщением"
- Не указаны критерии проверки всплывающих подсказок

---

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

---

## PaneTable.java

**Расположение:** `userInterface.PaneTable`

**Описание:**
Интерфейс PaneTable представляет собой базовый интерфейс для табличных компонентов пользовательского интерфейса. В текущей реализации не содержит методов, что может указывать на его использование в качестве маркерного интерфейса или базового типа для таблиц. Предположительно служит для типобезопасности и организации иерархии компонентов.

**Public методы:**
- Отсутствуют

**Зависимости:**
- Отсутствуют

**Пример использования:**
```java
// Использование в качестве типа
PaneTable table = getTableComponent();
```

**Неясности:**
- Назначение интерфейса неясно из-за отсутствия методов
- Непонятно, является ли это временной реализацией или финальным решением

---

## ProgressBar.java

**Расположение:** `userInterface.ProgressBar`

**Описание:**
Интерфейс ProgressBar определяет контракт для работы с индикаторами выполнения в пользовательском интерфейсе. Предоставляет методы для проверки наличия и отсутствия прогресс-бара, а также для ожидания его исчезновения. Наследует функциональность от абстрактного GUI объекта.

**Public методы:**
- `ProgressBar проверка_наличия()` - проверяет наличие прогресс-бара на экране, возвращает текущий объект
- `void проверка_отсутствия()` - проверяет отсутствие прогресс-бара на экране
- `void ожидание_исчезновения()` - ожидает исчезновения прогресс-бара с экрана

**Зависимости:**
- `userInterface.impl.RFT_ProgressBar` - импорт реализации
- `userInterface.model.abstr.GuiObject` - базовый класс

**Пример использования:**
```java
ProgressBar progressBar = findProgressBar();
progressBar.проверка_наличия()
           .ожидание_исчезновения();
```

**Неясности:**
- Не указаны таймауты для операции ожидания
- Непонятна разница между проверкой отсутствия и ожиданием исчезновения
- Отсутствует информация о возможных исключениях

---

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

---

# Table.java

## Summary
Интерфейс для работы с табличными элементами пользовательского интерфейса. Предоставляет методы для взаимодействия с таблицами: получение данных, проверка значений, выполнение кликов. Поддерживает работу со строками и колонками таблицы в тестовых сценариях.

## Public Methods
- `getRowCount()` - возвращает количество строк в таблице
- `getColumnCount()` - возвращает количество колонок в таблице
- `clickAtRandomRow()` - кликает на случайную строку, возвращает значения строки
- `получение_строки(String... args)` - получает строку таблицы по значениям колонок
- `получение_строки(TableColumn ИмяКолонки, String... args)` - получает строку по указанной колонке и значениям
- `проверка_значений_ячеек_строки_таблицы(String... args)` - проверяет значения ячеек строки
- `проверка_отсутствия_строки_таблицы(String... args)` - проверяет отсутствие строки в таблице
- `получить_значения_Колонки(String ИмяКолонки)` - возвращает все значения указанной колонки
- `получить_значения_Колонок(String... ИмяКолонки)` - возвращает значения нескольких колонок
- `колонка_отсортирована(String ИмяКолонки, boolean по_Возрастанию)` - проверяет сортировку колонки
- `проверка_отсутствия_значения_ячейки_строки_таблицы(String ИмяКолонки, String ЗначениеКолонки)` - проверяет отсутствие значения в ячейке
- `проверка_отсутствия_записи_таблицы(String... КолонкаЗначение)` - проверяет отсутствие записи в таблице
- `проверка_одинаковых_значений_Колонки(String Колонка, String Значение)` - проверяет одинаковость значений в колонке
- `checkTheSame(String column, String value)` - проверяет идентичность значений (английская версия)
- `сумма_Значений_Колонки(String ИмяКолонки)` - вычисляет сумму значений числовой колонки
- `получение_таблицы(String... колонки)` - получает всю таблицу или указанные колонки
- `сравнить_с_таблицей(List<LinkedHashMap<String, String>> таблица)` - сравнивает текущую таблицу с переданной
- `получить_мах_значение_колонки(String имяКолонки)` - находит максимальное значение в колонке
- `уникальная_колонка(List<LinkedHashMap<String, String>>... таблица)` - находит уникальную колонку в таблице(ах)
- `проверить_что_значения_всех_строк_равны_заданной(String[] СтолбецЗначение)` - проверяет равенство значений всех строк заданным
- `проверить_что_значения_колонки_больше_нуля(String имяКолонки)` - проверяет что значения колонки больше нуля
- `getTestObject()`, `getFreshTestObject()` - наследуются от GuiObject

## Dependencies
- `com.rational.test.ft.object.interfaces.GuiTestObject`
- `enums.TableColumn`
- `userInterface.impl.RFT_Table`
- `userInterface.model.abstr.GuiObject`
- `java.math.BigDecimal`
- `java.util.HashMap`, `LinkedHashMap`, `List`

## Usage Example
```java
Table table = // получение таблицы
table.проверка_значений_ячеек_строки_таблицы("Колонка1", "значение1", "Колонка2", "значение2");
String[] значения = table.получить_значения_Колонки("ИмяКолонки");
table.колонка_отсортирована("Дата", true);
```

## Uncertainties
- Неясна разница между `проверка_одинаковых_значений_Колонки` и `checkTheSame`
- Назначение параметра `toLog` и `toAttach` в перегруженном `получение_таблицы`
- Не указаны исключения для методов поиска при отсутствии элементов

---

# TextField.java

## Summary
Интерфейс для работы с текстовыми полями ввода. Предоставляет базовые операции: ввод текста, проверка значений, проверка доступности элемента. Используется для тестирования форм ввода данных.

## Public Methods
- `ввести_текст(String keys)` - вводит текст в поле
- `проверить_не_пустое_значение()` - проверяет что поле не пустое
- `проверить_значение(String value)` - проверяет что значение поля соответствует ожидаемому
- `getValue()` - возвращает текущее значение поля (на английском)
- `isEnabled()` - проверяет что поле доступно для редактирования
- `isDisabled()` - проверяет что поле заблокировано
- `getTestObject()`, `getFreshTestObject()` - наследуются от GuiObject

## Dependencies
- `com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject`
- `userInterface.impl.RFT_TextField`
- `userInterface.model.abstr.GuiObject`

## Usage Example
```java
TextField поле = // получение текстового поля
поле.ввести_текст("тестовый текст");
поле.проверить_значение("тестовый текст");
поле.isEnabled();
```

## Uncertainties
- Не указано поведение при вводе null в `ввести_текст`
- Не ясно, кидаются ли исключения при проверках `проверить_не_пустое_значение` и `проверить_значение`

---

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

---

## PRX_TextField.java

**Summary**  
Класс PRX_TextField реализует работу с текстовыми полями ввода. Предоставляет методы для ввода текста, проверки значений и состояния поля. Использует типизированные тестовые объекты для работы с текстовыми элементами GUI.

**Public методы**
- `PRX_TextField(String textFieldName, Container parent, PropertyBuilder properties)` - конструктор
- `ввести_текст(String keys)` - вводит текст в поле
- `проверить_не_пустое_значение()` - проверяет, что поле не пустое
- `проверить_значение(String value)` - проверяет конкретное значение поля
- `getValue()` - возвращает текущее значение поля
- `isEnabled()`, `isDisabled()` - проверяют состояние поля
- `getTestObject()`, `getFreshTestObject()` - возвращают тестовые объекты

**Dependencies**
- `userInterface.TextField` (интерфейс)
- `userInterface.model.extend.prx.Proxy` (родительский класс)
- `com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject`
- `base.PropertyBuilder`

**Usage Example**
```java
PRX_TextField textField = new PRX_TextField("username", parentContainer, properties);
textField.ввести_текст("testuser");
textField.проверить_значение("testuser");
String value = textField.getValue();
```

**Uncertainties**
- Не указано, как обрабатываются специальные клавиши при вводе
- Неясна разница между `getTestObject()` и `getFreshTestObject()`
- Отсутствует валидация вводимых данных

---

## PRX_Button.java

**Summary**:  
Класс-прокси для работы с кнопками в UI-тестах. Реализует интерфейс Button и предоставляет методы для взаимодействия с кнопками: клик, проверка состояния и получение текста. Включает механизм повторных попыток при возникновении исключений с настраиваемыми таймаутами.

**Public методы**:
- `click()` - выполняет клик по кнопке с повторными попытками при ошибках
- `isEnabled(boolean state)` - проверяет/устанавливает состояние активности кнопки
- `isVisible(boolean state)` - проверяет/устанавливает состояние видимости кнопки
- `getText()` - возвращает текст кнопки с повторными попытками при ошибках
- `isVisible()` - проверяет видимость кнопки
- `isEnabled()` - проверяет активность кнопки

**Dependencies**:
- `RFT_Button` - оборачиваемый класс
- `MainClass`, `Helper`, `PropertyBuilder` - вспомогательные классы
- `UnregisteredObjectException`, `WindowActivateFailedException` - исключения RFT

**Usage example**:
```java
PRX_Button button = new PRX_Button(parent, properties);
button.click();
String text = button.getText();
button.isEnabled(true);
```

**Uncertainties**:
- Неясна реализация `methodFromWrappedRFTImpl()`
- Непонятно назначение `MainClass.afterMainClass()` в `getText()`
- Требуется проверка корректности логики в `getText()` (отмечено TODO)

---

## PRX_CheckBox_RadioButton.java

**Summary**:  
Прокси-класс для работы с чекбоксами и радиокнопками. Реализует интерфейс CheckBox_RadioButton и предоставляет методы для управления состоянием элементов (выбор/снятие) и проверки их текущего состояния.

**Public методы**:
- `click(boolean отметка)` - устанавливает состояние элемента (выбран/не выбран)
- `isSelected()` - проверяет, выбран ли элемент
- `isEnabled()` - проверяет активность элемента
- `isDisabled()` - проверяет неактивность элемента
- `getTestObject()` - возвращает тестовый объект RFT
- `getFreshTestObject()` - возвращает обновленный тестовый объект RFT

**Dependencies**:
- `RFT_CheckBox_RadioButton` - оборачиваемый класс
- `ToggleGUITestObject` - интерфейс тестового объекта RFT
- `PropertyBuilder` - построитель свойств

**Usage example**:
```java
PRX_CheckBox_RadioButton checkbox = new PRX_CheckBox_RadioButton(parent, properties);
checkbox.click(true); // выбрать
boolean isSelected = checkbox.isSelected();
```

**Uncertainties**:
- Неясна реализация `methodFromWrappedRFTImpl()`
- Методы `isEnabled()` и `isDisabled()` возвращают `RFT_CheckBox_RadioButton` вместо boolean

---

## PRX_ComboBox.java

**Summary**:  
Прокси-класс для работы с выпадающими списками (combobox). Предоставляет методы для выбора значений, проверки содержимого и получения текущего значения. Включает механизм повторных попыток при ошибках.

**Public методы**:
- `выбрать_значение(String Значение)` - выбирает значение из списка с повторными попытками
- `выбрать_значение_содержащее(String значение)` - выбирает значение содержащее указанную строку
- `проверить_значение_не_пустое()` - проверяет, что значение не пустое
- `проверить_значение(String значение)` - проверяет конкретное значение
- `getValue()` - возвращает текущее значение
- `проверитьДоступныеЗначенияСписка(String...)` - проверяет доступные значения
- `isEnabled()`, `isDisabled()` - проверяют состояние элемента
- `items()` - возвращает список всех элементов

**Dependencies**:
- `RFT_ComboBox` - оборачиваемый класс
- `TextSelectGuiSubitemTestObject` - интерфейс тестового объекта RFT
- `MainClass`, `Helper` - вспомогательные классы

**Usage example**:
```java
PRX_ComboBox comboBox = new PRX_ComboBox("comboName", parent, properties);
comboBox.выбрать_значение("Значение1");
String value = comboBox.getValue();
```

**Uncertainties**:
- Логика в `выбрать_значение()` требует проверки (отмечено TODO)
- Неясна реализация `methodFromWrappedRFTImpl()`
- Непонятно назначение `MainClass.afterMainClass()`

---

## PRX_ComboBoxSearch.java

**Summary**:  
Специализированный класс для комбобоксов с функцией поиска. Наследуется от PRX_ComboBox и добавляет метод для выбора значения путем ввода текста.

**Public методы**:
- `chooseByType(String значение)` - выбирает значение путем ввода текста

**Dependencies**:
- `PRX_ComboBox` - родительский класс
- `RFT_ComboBoxSearch` - оборачиваемый класс

**Usage example**:
```java
PRX_ComboBoxSearch searchCombo = new PRX_ComboBoxSearch("searchCombo", parent, properties);
searchCombo.chooseByType("текст");
```

**Uncertainties**:
- Неясна разница между `chooseByType` и `выбрать_значение`
- Неясна реализация `methodFromWrappedRFTImpl()`

---

## PRX_InputTable.java

**Summary**:  
Класс представляет прокси-объект для таблицы ввода данных. Наследует функциональность базовой таблицы PRX_Table и реализует интерфейс InputTable. Предоставляет метод для ввода данных в указанную ячейку строки таблицы с поддержкой логирования шагов в Allure-отчетах.

**Public методы**:
- `ввести_данные_в_ячейки_строки(String column, String value, String... args)`  
  Вводит значение в колонку указанной строки. Принимает название колонки, значение и аргументы для идентификации строки. Возвращает объект RFT_InputTable.

**Dependencies**:
- Родительский класс: `PRX_Table`
- Интерфейсы: `InputTable`, `Container`
- Вспомогательные классы: `PropertyBuilder`, `RFT_InputTable`
- Аннотации: `@Step` из библиотеки Allure

**Usage Example**:
```java
PRX_InputTable table = new PRX_InputTable(parentContainer, properties);
table.ввести_данные_в_ячейки_строки("Стоимость", "1000", "Заказ 1");
```

**Uncertainties**:
- Назначение параметров `args` для идентификации строки
- Реализация метода `methodFromWrappedRFTImpl` скрыта в родительском классе

---

## PRX_Jump_Button.java

**Summary**:  
Специализированная кнопка для навигации между окнами/страницами. Реализует интерфейс Jump_Button с методами для кликов с возвратом экземпляров классов страниц. Содержит механизм повторных попыток при возникновении ошибок активации окон.

**Public методы**:
- `click(T existingClassInstance)` – Клик с возвратом существующего экземпляра класса
- `click(Class<T> cls)` – Клик с созданием нового экземпляра класса
- `click(Class<T> new_Class_Instance, Object... args)` – Клик с созданием экземпляра класса через конструктор с параметрами
- `clickAndClose()` и его перегрузки – Клик с последующим закрытием окна

**Dependencies**:
- Родительский класс: `PRX_Button`
- Внешние зависимости: `MainClass`, `Window`, `RFT_Jump_Button`
- Исключения: `UnregisteredObjectException`, `WindowActivateFailedException`

**Usage Example**:
```java
PRX_Jump_Button button = new PRX_Jump_Button(parent, properties);
OrderPage orderPage = button.click(OrderPage.class);
```

**Uncertainties**:
- Логика обработки ошибок в цикле повторных попыток
- Назначение Window.LAST_FOUND_WINDOWS
- Разница между click() и clickAndClose()

---

## PRX_Label.java

**Summary**:  
Прокси-объект для работы с текстовыми метками. Реализует интерфейс Label и предоставляет методы для проверки текстового содержимого и всплывающих подсказок. Поддерживает получение текста с опциональным удалением HTML-тегов.

**Public методы**:
- Методы проверки сообщений по части строки (`проверка_сообщения_по_части_строки` и др.)
- `getText()`/`getText(boolean removeTags)` – Получение текста метки
- `getTestObject()`/`getFreshTestObject()` – Получение GUI-объектов RFT

**Dependencies**:
- Родительский класс: `Proxy<RFT_Label>`
- GUI-объект: `GuiTestObject` из RFT
- Интерфейсы: `Label`, `Container`

**Usage Example**:
```java
PRX_Label label = new PRX_Label(parent, properties);
label.проверка_сообщения_по_части_строки("успешно");
String text = label.getText(true);
```

**Uncertainties**:
- Разница между основными и "fresh" test objects
- Критерии сравнения "по части строки"
- Логика удаления HTML-тегов в getText()

---

## PRX_List.java

**Summary**:  
Прокси-объект для работы со списками элементов. Реализует интерфейс List и предоставляет методы для взаимодействия с выпадающими списками или списками значений: выбор элементов, проверки содержимого, получение значений.

**Public методы**:
- `clickAtText(String text)` – Клик по элементу с указанным текстом
- `shouldContainText()`/`shouldNotContainText()` – Проверки наличия/отсутствия текста
- `values()`/`size()`/`contains()` – Методы получения информации о содержимом
- Методы получения RFT-объектов `SelectGuiSubitemTestObject`

**Dependencies**:
- Родительский класс: `Proxy<RFT_List>`
- RFT-объект: `SelectGuiSubitemTestObject`
- Интерфейсы: `List`, `Container`

**Usage Example**:
```java
PRX_List countryList = new PRX_List("Страны", parent, properties);
countryList.clickAtText("Россия");
List<String> values = countryList.values();
```

**Uncertainties**:
- Назначение параметра `listName` в конструкторе
- Разница между `получение_значений()` и `values()`
- Особенности работы `SelectGuiSubitemTestObject`

---

## PRX_PaneTable.java

**Summary**  
Класс PRX_PaneTable представляет прокси-реализацию таблицы в пользовательском интерфейсе. Наследуется от MainClass и реализует интерфейс PaneTable. Использует логгер для отслеживания операций и содержит ссылку на RFT_PaneTable для выполнения фактических операций с GUI.

**Public методы**
- Отсутствуют явно объявленные public методы (класс находится в разработке)

**Dependencies**
- `userInterface.PaneTable` (интерфейс)
- `base.MainClass` (родительский класс)
- `org.slf4j.Logger` (логирование)

**Usage Example**
```java
// Класс не завершен, пример использования недоступен
```

**Uncertainties**
- Класс закомментирован и не готов к использованию
- Неясно, какие методы будут реализованы
- Отсутствует конструктор

---

## PRX_ProgressBar.java

**Summary**  
Класс PRX_ProgressBar реализует функциональность работы с индикатором выполнения (прогресс-баром). Предоставляет методы для проверки наличия/отсутствия прогресс-бара и ожидания его исчезновения. Использует механизм прокси для делегирования вызовов RFT-реализации.

**Public методы**
- `PRX_ProgressBar(Container parent, PropertyBuilder properties)` - конструктор
- `проверка_наличия()` - проверяет наличие прогресс-бара, возвращает RFT_объект
- `проверка_отсутствия()` - проверяет отсутствие прогресс-бара, логирует результат
- `ожидание_исчезновения()` - ожидает исчезновения прогресс-бара из интерфейса

**Dependencies**
- `userInterface.ProgressBar` (интерфейс)
- `userInterface.model.extend.prx.Proxy` (родительский класс)
- `userInterface.model.extend.interf.Container`
- `base.PropertyBuilder`
- `userInterface.utils.TestObjectFinder`

**Usage Example**
```java
PRX_ProgressBar progressBar = new PRX_ProgressBar(parentContainer, properties);
progressBar.проверка_наличия();
progressBar.ожидание_исчезновения();
```

**Uncertainties**
- Неясна разница между `проверка_отсутствия()` и `ожидание_исчезновения()`
- Обработка исключений может пропускать некоторые типы ошибок
- Не указаны таймауты для ожидания

---

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

---

## PRX_Table.java

**Summary**  
Класс PRX_Table предоставляет расширенную функциональность для работы с таблицами в GUI. Включает методы для получения данных из таблицы, проверки сортировки, вычисления сумм и сравнения с эталонными данными. Реализует механизм повторных попыток при возникновении ошибок.

**Public методы**
- `PRX_Table(Container parent, PropertyBuilder properties)` - конструктор
- `getRowCount()`, `getColumnCount()` - возвращают размеры таблицы
- `clickAtRandomRow()` - кликает на случайную строку
- `проверка_одинаковых_значений_Колонки()` - проверяет одинаковые значения в колонке
- `сумма_Значений_Колонки()` - вычисляет сумму числовых значений колонки
- `получение_таблицы()` - получает данные таблицы в различных форматах
- `сравнить_с_таблицей()` - сравнивает таблицу с эталоном
- `получение_строки()` - получает данные конкретной строки
- `проверка_значений_ячеек_строки_таблицы()` - проверяет значения в строке
- `получить_значения_Колонок()` - получает значения указанных колонок
- `колонка_отсортирована()` - проверяет сортировку колонки
- `проверка_отсутствия_строки_таблицы()` - проверяет отсутствие строки
- `уникальная_колонка()` - находит уникальную колонку в таблице

**Dependencies**
- `userInterface.Table` (интерфейс)
- `userInterface.model.extend.prx.Proxy` (родительский класс)
- `enums.TableColumn`
- `base.MainClass`
- `com.rational.test.ft` (исключения)
- `java.math.BigDecimal`
- `java.util` (коллекции)

**Usage Example**
```java
PRX_Table table = new PRX_Table(parentContainer, properties);
int rowCount = table.getRowCount();
BigDecimal sum = table.сумма_Значений_Колонки("Цена");
List<LinkedHashMap<String, String>> data = table.получение_таблицы("Колонка1", "Колонка2");
```

**Uncertainties**
- Сложная логика повторных попыток в некоторых методах
- Неясны критерии выбора "уникальной колонки"
- Возможны проблемы с производительностью при больших таблицах

---

## Анализ класса TestObjectProperties.java

### Summary
Утилитарный класс для отладочной информации о свойствах RFT-объектов. Предоставляет методы форматированного вывода свойств, метаданных и табличных данных тестовых объектов. Используется преимущественно для диагностики проблем в тестах.

### Public методы
- `printProperties(Hashtable properties)` - форматирует свойства в строку
- `printTestObjectProperties(TestObject obj)` - выводит полные свойства объекта
- `printTableDataProperties(GuiSubitemTestObject)` - выводит свойства табличных данных
- `printTableData(TestObject)` - форматирует содержимое таблицы
- `printTableData1(TestObject)` - альтернативная версия для таблиц

### Dependencies
- `RationalTestScript` (базовый класс RFT)
- `TestObject`, `GuiSubitemTestObject` (RFT-объекты)
- `ITestDataTable`, `ITestDataText` (RFT-интерфейсы данных)

### Usage example
```java
String props = TestObjectProperties.printProperties(textField.getProperties());
System.out.println("Свойства поля: " + props);
```

### Uncertainties
- Разница между printTableData и printTableData1 не очевидна
- Логика обработки разных типов ITestData требует упрощения
- Назначение messageHeader неясно (поле не используется)

---

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

---

## RFT_CheckBox_RadioButton.java

### Summary
Класс реализует работу с элементами CheckBox и RadioButton. Поддерживает операции переключения состояния, проверки выбранного состояния и проверки доступности/недоступности элемента. Использует механизм ожидания закрытия pop-up окон после действий.

### Public методы
- `click(boolean Отметка)` - Устанавливает состояние элемента (выбран/не выбран)
- `isSelected()` - Возвращает текущее состояние элемента
- `isEnabled()` - Проверяет, что элемент доступен для взаимодействия
- `isDisabled()` - Проверяет, что элемент недоступен для взаимодействия
- `getTestObject()`, `getFreshTestObject()` - Возвращают тестовый объект ToggleGUITestObject

### Dependencies
- `com.rational.test.ft.object.interfaces.ToggleGUITestObject`
- `userInterface.CheckBox_RadioButton`
- `userInterface.model.extend.rft.Element`
- `base.Base`, `base.PropertyBuilder`

### Usage Example
```java
RFT_CheckBox_RadioButton checkbox = new RFT_CheckBox_RadioButton(parent, properties);
checkbox.click(true);
checkbox.isSelected();
checkbox.isEnabled();
```

### Uncertainties
1. Назначение и реализация Base.main.waitPopUpClose()
2. Логика определения elementName() через свойство "label" может требовать доработки
3. Отсутствует явная обработка случаев, когда элемент не поддерживает свойство "selected"

---

## RFT_ComboBox.java

### Summary
Класс реализует работу с выпадающими списками (ComboBox). Предоставляет методы выбора значений по точному совпадению или содержанию, проверки выбранного значения, проверки доступных значений в списке. Включает кеширование элементов списка для оптимизации.

### Public методы
- `getValue()` - Возвращает выбранное значение
- `выбрать_значение(String значение)` - Выбирает значение по точному совпадению
- `выбрать_значение_содержащее(String значение)` - Выбирает значение по частичному совпадению
- `проверить_значение(String Значение)` - Проверяет выбранное значение
- `проверить_значение_не_пустое()` - Проверяет, что значение не пустое
- `проверитьДоступныеЗначенияСписка(String... значения)` - Проверяет наличие значений в списке
- `isEnabled()`, `isDisabled()` - Проверяют доступность элемента
- `items()` - Возвращает список всех доступных значений

### Dependencies
- `com.rational.test.ft.object.interfaces.TextSelectGuiSubitemTestObject`
- `com.rational.test.ft.SubitemNotFoundException`
- `com.rational.test.ft.vp.ITestDataList`
- `userInterface.ComboBox`
- `userInterface.model.extend.rft.Element`
- `bugbusters.modules.helper.Helper`, `bugbusters.modules.helper.utils.StringUtils`

### Usage Example
```java
RFT_ComboBox combo = new RFT_ComboBox("Комбо-бокс", parent, properties);
combo.выбрать_значение("Значение1");
combo.проверить_значение("Значение1");
combo.проверитьДоступныеЗначенияСписка("Значение1", "Значение2");
```

### Uncertainties
1. Производительность при большом количестве элементов (комментарий про 50+ записей)
2. Надежность механизма выбора по индексу при несовпадении значений
3. Логика кеширования itemsCache может требовать сброса при изменении списка
4. Обработка случаев, когда список изначально пуст

---

# RFT_ComboBoxSearch.java

## Summary
Класс реализует выпадающий список с функцией поиска. Позволяет выбирать значения путем ввода текста с последующим автоматическим завершением. Обрабатывает исключения при вводе символов вне кодовой страницы и предоставляет методы проверки выбранного значения.

## Public методы
- `chooseByType(String значение)` - Выбирает значение путем ввода текста в поле. Параметр: текст для поиска. Может выбросить исключение при невозможности ввода.
- `проверить_значение(String value)` - Проверяет, что текущее значение содержит указанную строку. Падает с ошибкой при несовпадении.

## Dependencies
- `com.rational.test.ft.*` (Rational Functional Tester)
- `userInterface.ComboBoxSearch`
- `java.awt.Point`
- `org.junit.jupiter.api.Assertions`

## Usage example
```java
RFT_ComboBoxSearch combo = new RFT_ComboBoxSearch("Города", container, properties);
combo.chooseByType("Москва");
combo.проверить_значение("Москва");
```

## Uncertainties
- Неясна логика обработки StringNotInCodePageException
- Назначение переменных count и retries не реализовано
- Не определен родительский класс RFT_ComboBox

---

# RFT_InputTable.java

## Summary
Класс представляет таблицу с возможностью ввода данных в ячейки. Реализует функционал редактирования ячеек через имитацию кликов и ввода с клавиатуры. Использует Robot для эмуляции нажатий клавиш.

## Public методы
- `ввести_данные_в_ячейки_строки(String column, String value, String... args)` - Вводит значение в указанную колонку и строку. Параметры: название колонки, значение, дополнительные аргументы для идентификации строки.

## Dependencies
- `java.awt.Robot`
- `java.awt.event.KeyEvent`
- `com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject`

## Usage example
```java
RFT_InputTable table = new RFT_InputTable(parent, properties);
table.ввести_данные_в_ячейки_строки("Цена", "1000", "1");
```

## Uncertainties
- Не определены методы родительского класса RFT_Table
- Логика определения строки по args не ясна
- Возможные проблемы с многопоточностью при использовании Robot

---

# RFT_Jump_Button.java

## Summary
Специализированная кнопка для навигации между формами приложения. Обеспечивает переход к другим окнам с автоматическим созданием экземпляров целевых классов. Реализует сложную логику ожидания закрытия/открытия окон.

## Public методы
- `click(T existingClassInstance)` - Нажимает кнопку и возвращает существующий экземпляр класса
- `click(Class<T> new_Class_Instance)` - Нажимает кнопку и создает новый экземпляр класса
- `clickAndClose()` - Нажимает кнопку с закрытием текущего окна
- Аналогичные методы с параметрами Object... args

## Dependencies
- `java.lang.reflect.*` (рефлексия)
- `base.MainClass`
- `utils.ClassUtils`
- `userInterface.utils.TestObjectFinder`

## Usage example
```java
RFT_Jump_Button button = new RFT_Jump_Button(parent, properties);
NewForm form = button.click(NewForm.class);
```

## Uncertainties
- Сложная логика работы с рефлексией может быть подвержена ошибкам
- Неясна обработка исключений в InvocationTargetException
- Логика определения "одинаковых" форм требует дополнительного изучения

---

# RFT_Label.java

## Summary
Класс для работы с текстовыми метками интерфейса. Предоставляет методы проверки текста меток и всплывающих подсказок. Поддерживает обработку HTML-контента и проверку частичных совпадений текста.

## Public методы
- `проверка_всплывающей_подсказки_по_части_строки(String)` - Проверяет всплывающую подсказку на содержание текста
- `проверка_сообщения_по_части_строки(String)` - Проверяет текст метки на частичное совпадение
- `проверка_отсутствия_сообщения_по_части_строки(String)` - Проверяет отсутствие текста в метке
- `проверка_отсутствия_сообщений()` - Проверяет пустоту текста метки

## Dependencies
- `org.jsoup.Jsoup` (парсинг HTML)
- `utils.HTMLtoCyrillic`
- `io.qameta.allure.Step` (интеграция с Allure)

## Usage example
```java
RFT_Label label = new RFT_Label(parent, properties);
label.проверка_сообщения_по_части_строки("успешно");
```

## Uncertainties
- Логика обработки HTML-символов может быть неполной
- Неясна разница между getText(true) и getText(false)
- Возможны проблемы с производительностью при частых проверках

---

## RFT_List.java

### summary:
Класс реализует функционал для работы со списками в UI-автотестах через Rational Functional Tester. Предоставляет методы для получения значений элементов списка, проверки содержания текста, выбора элементов и проверки количества элементов. Включает парсинг значений списка с учетом специальных случаев форматирования.

### public методы:
- `size()` - возвращает количество элементов в списке
- `values()` - возвращает список строковых значений всех элементов
- `contains(String text)` - проверяет наличие текста в элементах списка
- `clickAtText(String text)` - кликает на элемент с указанным текстом (бросает SubitemNotFoundException если элемент не найден)
- `shouldContainItemCount(int count)` - проверяет соответствие количества элементов ожидаемому значению
- `shouldContainText(String text)` - проверяет наличие указанного текста в списке
- `shouldNotContainText(String text)` - проверяет отсутствие указанного текста в списке
- `получение_значений()` - возвращает текстовое представление всех значений списка

### dependencies:
- Rational FT: `SelectGuiSubitemTestObject`, `SubitemNotFoundException`
- Внутренние: `RFT_Window`, `PropertyBuilder`, `StringUtils`
- Тестирование: `org.junit.jupiter.api.Assertions.fail`

### usage_example:
```java
RFT_List myList = new RFT_List("СписокРегионов", window, properties);
myList.shouldContainItemCount(5);
myList.clickAtText("Москва");
myList.shouldContainText("Санкт-Петербург");
```

### uncertainties:
- Логика парсинга в `parseValues()` может быть хрупкой при сложных форматах данных
- Комментарий указывает на проблему несоответствия `.itemCount` и фактического количества распарсенных значений
- Использование кириллических имен методов может усложнить поддержку

---

## RFT_PaneTable.java

### summary:
Файл содержит закомментированный код класса для работы с таблицами. В текущем состоянии класс не реализован и не может быть использован.

### public методы:
Отсутствуют

### dependencies:
Отсутствуют

### usage_example:
Не применимо

### uncertainties:
- Назначение класса неясно из-за отсутствия реализации
- Непонятно, должен ли класс наследоваться от RFT_Window

---

## RFT_ProgressBar.java

### summary:
Класс реализует методы для работы с прогресс-барами в UI-автотестах. Предоставляет функционал проверки наличия/отсутствия прогресс-бара, а также ожидания его появления и исчезновения с использованием таймаутов.

### public методы:
- `проверка_наличия()` - проверяет наличие прогресс-бара
- `проверка_отсутствия()` - проверяет отсутствие прогресс-бара (бросает исключение при наличии)
- `ожидание_исчезновения()` - ожидает исчезновения прогресс-бара в течение заданного времени

### dependencies:
- Rational FT: `GuiTestObject`
- Внутренние: `TestObjectFinder`, `TestObjectWaiter`, `PropertyBuilder`
- Конфигурация: `env.Environment.getTimeOutProperties`

### usage_example:
```java
RFT_ProgressBar progress = new RFT_ProgressBar(parent, properties);
progress.проверка_наличия();
progress.ожидание_исчезновения();
```

### uncertainties:
- Логика в `ожидание_исчезновения()` содержит потенциально бесконечный цикл
- Использование кириллических имен методов
- Взаимодействие с `Base.main.isCompleted()` не полностью ясно

---

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

---

# RFT_Table.java

## Summary
Класс `RFT_Table` представляет собой реализацию интерфейса `Table` для работы с табличными элементами GUI в фреймворке автотестов. Он предоставляет методы для взаимодействия с таблицами: выбор строк, проверка значений, получение данных, проверка сортировки и другие операции. Класс содержит расширенную логику обработки различных типов ячеек (даты, деревья, числа) и обходные решения для специфических проблем RFT.

## Public методы

### Основные операции
- `getRowCount()`: Возвращает количество строк в таблице
- `getColumnCount()`: Возвращает количество колонок в таблице
- `clickAtRow(int columnIndex, String... args)`: Кликает на строку с указанными параметрами
- `clickAtRandomRow()`: Выбирает случайную строку таблицы и возвращает её данные
- `получение_строки(String... пара_Колонка_Значение)`: Получает данные строки по критериям
- `получение_строки(TableColumn ИмяКолонки, String... пара_Колонка_Значение)`: Получает данные строки с кликом в указанной колонке

### Проверки
- `проверка_значений_ячеек_строки_таблицы(TableColumn ИмяКолонки, String... пара_Колонка_Значение)`: Проверяет значения ячеек строки
- `проверка_отсутствия_строки_таблицы(String... пара_Колонка_Значение)`: Проверяет отсутствие строки
- `колонка_отсортирована(String НазваниеКолонки, boolean по_Возрастанию)`: Проверяет сортировку колонки
- `проверка_одинаковых_значений_Колонки(String ИмяКолонки, String ЗначениеКолонки)`: Проверяет одинаковые значения в колонке

### Работа с данными
- `получить_значения_Колонки(String ИмяКолонки)`: Возвращает массив значений колонки
- `получить_значения_Колонок(String... ИмяКолонки)`: Возвращает значения нескольких колонок
- `получение_таблицы(boolean toLog, boolean toAttach, String... колонки)`: Получает данные таблицы с опциями логирования
- `сумма_Значений_Колонки(String ИмяКолонки)`: Суммирует значения числовой колонки

### Утилиты
- `уникальная_колонка()`: Находит колонку с уникальными значениями
- `таблица_toString()`: Форматирует таблицу для вывода
- `t1_contains_t2()`: Проверяет вхождение одной таблицы в другую

## Dependencies
**Внутренние:**
- `base.Base`, `base.MainClass`, `base.PropertyBuilder`
- `userInterface.Table`, `userInterface.model.extend.*`
- `enums.TableColumn`
- `bugbusters.modules.helper.*`

**Внешние:**
- `com.rational.test.ft.*` (RFT framework)
- `java.math.BigDecimal`
- `java.time.*`, `java.text.SimpleDateFormat`
- `java.util.*` коллекции

## Usage Example
```java
RFT_Table table = new RFT_Table(parent, properties);

// Получить данные строки
HashMap<String, String> rowData = table.получение_строки("Номер", "12345", "Статус", "Активный");

// Проверить сортировку
table.колонка_отсортирована("Дата", true);

// Получить сумму значений
BigDecimal sum = table.сумма_Значений_Колонки("Сумма");
```

## Uncertainties
1. **Обходные решения для JTree**: Логика обработки ячеек с JTree и ZIP-файлами может быть избыточной
2. **Таймауты и ожидания**: Использование системных свойств для настройки таймаутов может быть неочевидным
3. **Смешанная локализация**: Комбинация русского и английского в именах методов усложняет чтение
4. **Устаревшие методы**: Наличие закомментированного кода и Deprecated методов требует чистки
5. **Обработка исключений**: Сложная логика повторных попыток может маскировать реальные проблемы
6. **Производительность**: Множественные обращения к RFT API могут замедлять выполнение тестов

---

## RFT_TextField.java

### Summary
Класс реализует текстовое поле в UI-фреймворке для автоматизированного тестирования. Предоставляет методы для ввода текста, проверки состояния поля (активно/неактивно) и валидации значений. Включает обработку различных состояний поля и специальную логику для числовых значений с округлением.

### Public методы
- `ввести_текст(String значение)` - вводит текст в поле, пропускает null и уже установленные значения
- `isEnabled()` - проверяет, доступно ли поле для редактирования
- `isDisabled()` - проверяет, заблокировано ли поле
- `getValue()` - возвращает текущее значение поля
- `проверить_не_пустое_значение()` - проверяет, что поле не пустое
- `проверить_значение(String значение)` - сравнивает значение поля с ожидаемым

### Dependencies
- `TextField` (интерфейс)
- `Element`, `Container`, `PropertyBuilder` (базовые классы)
- `RFT_Window` (родительское окно)
- `App`, `Base` (базовые утилиты)
- `TextGuiSubitemTestObject` (RFT-объект)

### Usage example
```java
RFT_TextField textField = new RFT_TextField("Пароль", window, properties);
textField.isEnabled();
textField.ввести_текст("Test123");
textField.проверить_значение("Test123");
```

### Uncertainties
- Логика округления чисел до 2 знаков может быть избыточной для текстовых полей
- Неясно назначение проверки на JTextArea и отправки ENTER
- Обработка NotYetAbleToPerformActionException через terminate_excel требует пояснения

---

## RFT_Window.java

### Summary
Класс представляет окно приложения в тестовом фреймворке. Обеспечивает управление окном (активация, максимизация, закрытие), работу с деревьями интерфейса и навигацию. Поддерживает как модальные, так и обычные окна, включая специфичные исключения для определенных окон.

### Public методы
- `getTitle()` - возвращает заголовок окна
- `isDescribedByForm()`, `form()` - работа с ассоциированной формой
- `isModal()` - проверяет модальность окна
- `close()` - закрывает окно и удаляет из регистрации
- `move()` - перемещает окно
- `выбор_значения_в_дереве()` (2 перегрузки) - выбирает значение в tree-компоненте
- `снять_отметку_в_дереве()` - снимает выделение в дереве
- `getHandle()` - возвращает хендл окна

### Dependencies
- `Window` (интерфейс)
- `ElementContainer`, `PropertyBuilder` (базовые классы)
- `FormWin` (ассоциированная форма)
- `Base`, `MainClass` (базовые утилиты)
- `TopLevelTestObject` (RFT-объект)

### Usage example
```java
RFT_Window window = new RFT_Window(form, properties);
window.getTestObject().activate();
window.выбор_значения_в_дереве("0", "Документы->Входящие");
window.close();
```

### Uncertainties
- Назначение LAST_FOUND_WINDOWS требует пояснения
- Логика максимизации окна с исключениями для конкретных заголовков
- Методы работы с деревом содержат закомментированный код и требуют рефакторинга

---

## GuiObject.java

### Summary
Интерфейс `GuiObject` определяет связь между объектами тестов и элементами пользовательского интерфейса. Он предоставляет методы для работы с GUI-объектами, включая безопасное выполнение операций через обработку устаревших ссылок. Интерфейс может быть реализован различными компонентами UI (окнами, кнопками и др.) для прямого доступа к элементам из тестов.

### Public методы
1. **`GuiTestObject getTestObject()`**  
   Возвращает объект `GuiTestObject`. Простой геттер, может возвращать ранее сохранённое значение.

2. **`GuiTestObject getFreshTestObject()`**  
   Возвращает актуальный объект `GuiTestObject`, принудительно перезагружая его из интерфейса. Рекомендуется рекурсивно обновлять родительские элементы.

3. **`<T> T mapTestObject(Function<GuiTestObject, T> mapper)`**  
   Применяет функцию `mapper` к тестовому объекту. Если ссылка устарела, автоматически использует свежий объект.  
   **Параметры:**  
   - `mapper`: функция для преобразования `GuiTestObject`.  
   **Исключения:**  
   - Может пробросить `UnregisteredObjectException`, если объект не найден.

4. **`void useTestObject(Consumer<GuiTestObject> consumer)`**  
   Выполняет действие `consumer` над тестовым объектом. Обрабатывает устаревшие ссылки аналогично `mapTestObject`.  
   **Параметры:**  
   - `consumer`: действие для выполнения над `GuiTestObject`.

### Dependencies
- **Пакеты:**  
  - `com.rational.test.ft` (RFT-фреймворк, включая `UnregisteredObjectException`).  
  - `java.util.function` (для `Consumer` и `Function`).  
- **Классы:**  
  - `GuiTestObject`, `TestObject` (из RFT).

### Usage Example
```java
// Клик по кнопке с обработкой устаревшей ссылки
button.useTestObject(obj -> {
    obj.click();
});

// Получение текста элемента
String text = label.mapTestObject(obj -> 
    obj.getProperty("text").toString()
);
```

### Uncertainties
1. **Механизм обновления `getFreshTestObject`:** Не ясно, как реализован рекурсивный поиск родительских элементов.
2. **Поведение при повторных ошибках:** Что произойдёт, если `getFreshTestObject` также выбросит `UnregisteredObjectException`?
3. **Кэширование объектов:** Не определён жизненный цикл кэшированных объектов в `getTestObject()`.
4. **Требования к реализации:** Не указано, должны ли реализации гарантировать потокобезопасность.

---

## Container.java

### Summary
Интерфейс `Container` предоставляет методы для создания прокси-объектов элементов пользовательского интерфейса. Содержит default-реализации методов для удобного создания элементов с разными стратегиями поиска. Используется как базовый контейнер для организации Page Object паттерна в тестовом фреймворке.

### Public Methods
- `childrenSource()`: Возвращает контейнер для поиска дочерних элементов (по умолчанию - текущий контейнер)
- `checkBox_radioBtn()`: 3 перегрузки для создания чекбокса/радиокнопки
- `button()`: 3 перегрузки для создания кнопки
- `jump_button()`: 3 перегрузки для создания "прыгающей" кнопки (специальный тип кнопки)
- `textField()`: 4 перегрузки для создания текстового поля
- `comboBox()`: 3 перегрузки для создания выпадающего списка
- `list()`: 2 перегрузки для создания списка
- `comboBoxSearch()`: 3 перегрузки для создания комбинированного поля поиска
- `table()`: 2 перегрузки для создания таблицы
- `input_table()`: 3 перегрузки для создания таблицы ввода
- `label()`: 5 перегрузок для создания метки
- `tab()`: 2 перегрузки для создания вкладки
- `progressBar()`: 3 перегрузки для создания индикатора прогресса

### Dependencies
- `base.PropertyBuilder` - построитель свойств для поиска элементов
- `enums.FindBy` - перечисления классов элементов и стратегий поиска
- `userInterface.impl.*` - реализации прокси-классов элементов UI
- `userInterface.model.abstr.GuiObject` - абстрактный базовый класс

### Usage Example
```java
public class LoginPage implements Container {
    public PRX_TextField username = textField("Логин", 
        FindBy.TextFieldClass.TEXT_FIELD, FindBy.PriorLabel, "Имя пользователя");
    public PRX_Button submit = button(FindBy.ButtonClass.BUTTON, FindBy.TextValue.LOGIN);
}
```

### Uncertainties
1. Назначение `PRX_Jump_Button` - неясна специфика этого типа кнопки
2. Разница между `PRX_ComboBox` и `PRX_ComboBoxSearch`
3. Отличие `PRX_Table` от `PRX_InputTable`
4. Смысл параметра `FindBy.TextValue` в некоторых методах
5. Назначение "прыгающей кнопки" (jump button) в контексте UI

---

## ProxyContainer.java

### Описание
Специализированный прокси-класс для обертывания RFT-реализаций контейнеров. Наследует базовую функциональность от Proxy и добавляет реализацию интерфейса Container.

**Summary:**
- Специализированная версия Proxy для контейнеров элементов
- Наследует механизм отложенной инициализации и вызова методов
- Реализует интерфейс Container для работы с иерархией элементов

### Public методы
1. **`ProxyContainer(Class<? extends C> wrappedClass, Object... args)`**  
   Конструктор, аналогичный родительскому, но для контейнеров

### Dependencies
- `userInterface.model.extend.interf.Container`
- `userInterface.model.extend.rft.ElementContainer`
- `userInterface.impl.RFT_Tab`

### Пример использования
```java
public class PRX_Tab extends ProxyContainer<RFT_Tab> {
    public PRX_Tab(Container parent, PropertyBuilder props) {
        super(RFT_Tab.class, parent, props);
    }
}
```

### Неясности
1. **Минимальная функциональность**: Класс практически не добавляет новой функциональности кроме типизации
2. **Иерархия контейнеров**: Комментарий показывает сложную вложенность контейнеров, но неясно как это влияет на жизненный цикл объектов

---

## Proxy.java

### Описание
Абстрактный класс-обертка для RFT-реализаций элементов интерфейса. Предоставляет механизм отложенной инициализации оборачиваемого элемента и унифицированный способ вызова его методов через рефлексию. Используется как базовый класс для PRX-реализаций элементов GUI.

**Summary:**
- Прокси-класс для обертывания RFT-реализаций элементов интерфейса
- Обеспечивает отложенную инициализацию оборачиваемого объекта
- Предоставляет универсальный метод для вызова методов обернутого объекта с обработкой исключений

### Public методы
1. **`Proxy(Class<? extends E> wrappedClass, Object... args)`**  
   Конструктор, принимающий класс оборачиваемого элемента и аргументы для его создания

2. **`getElement(): E`**  
   Возвращает экземпляр обернутого элемента (при первом вызове выполняет инициализацию)

3. **`getTestObject(): GuiTestObject`**  
   Возвращает тестовый объект RFT из обернутого элемента

4. **`getFreshTestObject(): GuiTestObject`**  
   Возвращает "свежий" тестовый объект (пересоздает при необходимости)

### Dependencies
- `com.rational.test.ft` (TargeGoneException, UnregisteredObjectException, GuiTestObject)
- `userInterface.impl` (RFT_Button, RFT_Label)
- `userInterface.model.extend.rft.Element`
- `utils.ClassUtils`
- `org.slf4j.Logger`

### Пример использования
```java
public class PRX_Button extends Proxy<RFT_Button> {
    public PRX_Button(Container parent, PropertyBuilder props) {
        super(RFT_Button.class, parent, props);
    }
    
    public void click() {
        methodFromWrappedRFTImpl();
    }
}
```

### Неясности
1. **Отложенная инициализация**: Неясно, в каких именно случаях используется отложенная инициализация и не может ли это вызвать проблемы с состоянием объекта
2. **Обработка исключений**: Логика повторного вызова после UnregisteredObjectException/TargetGoneException может быть не всегда безопасной
3. **Reflection performance**: Использование рефлексии для каждого вызова метода может негативно сказаться на производительности

---

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

---

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

---

# TestObjectWaiter.java

## Summary
Класс предоставляет механизмы ожидания появления тестовых объектов в интерфейсе. Реализует повторные попытки поиска с настраиваемыми таймаутами и интервалами, включая логирование процесса ожидания. Основное применение - ожидание элементов GUI в автоматизированных тестах.

## Public методы
- `waitTestObject(Element e)`  
  Ожидание появления тестового объекта для переданного Element.  
  Параметры: `e` - элемент для ожидания  
  Возвращает: Optional<GuiTestObject>

- `waitCondition(String description, int waitTimeMillis, int waitTimeCount, BiConsumer<Integer, Integer> consumer)`  
  Универсальный метод ожидания условия.  
  Параметры:  
  - `description` - описание условия  
  - `waitTimeMillis` - интервал между проверками (мс)  
  - `waitTimeCount` - количество попыток  
  - `consumer` - проверяемое условие  
  Возвращает: boolean (true - условие выполнено)

## Dependencies
- `Helper` (bugbusters.modules.helper)
- `GuiTestObject` (com.rational.test.ft)
- `Element` (userInterface.model.extend.rft)
- `MoreThan1WereFoundException` (userInterface.utils.TestObjectFinder)

## Usage example
```java
Element loginButton = new Element(...);
Optional<GuiTestObject> button = TestObjectWaiter.waitTestObject(loginButton);

// Ожидание кастомного условия
boolean found = TestObjectWaiter.waitCondition(
    "Ожидание элемента",
    1000, 
    5,
    (i, limit) -> { if (!element.exists()) throw new RuntimeException(); }
);
```

## Uncertainties
1. Назначение системного свойства `TestObjectWaiter.printAttemptNumberPer.times`
2. Поведение при одновременном возникновении разных исключений
3. Логика сброса счетчика в основном цикле ожидания
4. Назначение метода `main` (отладочный или производственный)

--

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