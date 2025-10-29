src\main\java\userinterface\impl
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