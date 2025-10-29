src\main\java\userinterface\model\extend\prx
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