src\main\java\userinterface\impl
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