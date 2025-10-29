src\main\java\userinterface\impl
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