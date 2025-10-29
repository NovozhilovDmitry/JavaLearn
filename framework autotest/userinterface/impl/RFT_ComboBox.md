src\main\java\userinterface\impl
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