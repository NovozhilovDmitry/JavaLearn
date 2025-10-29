src\main\java\userinterface
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