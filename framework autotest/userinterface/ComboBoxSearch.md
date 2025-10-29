src\main\java\userinterface
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