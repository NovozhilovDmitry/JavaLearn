src\main\java\userinterface\impl
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