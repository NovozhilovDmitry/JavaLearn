src\main\java\userinterface\impl
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