src\main\java\userinterface\impl
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