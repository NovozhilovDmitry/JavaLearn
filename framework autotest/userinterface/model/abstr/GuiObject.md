src\main\java\userinterface\model\abstr
## GuiObject.java

### Summary
Интерфейс `GuiObject` определяет связь между объектами тестов и элементами пользовательского интерфейса. Он предоставляет методы для работы с GUI-объектами, включая безопасное выполнение операций через обработку устаревших ссылок. Интерфейс может быть реализован различными компонентами UI (окнами, кнопками и др.) для прямого доступа к элементам из тестов.

### Public методы
1. **`GuiTestObject getTestObject()`**  
   Возвращает объект `GuiTestObject`. Простой геттер, может возвращать ранее сохранённое значение.

2. **`GuiTestObject getFreshTestObject()`**  
   Возвращает актуальный объект `GuiTestObject`, принудительно перезагружая его из интерфейса. Рекомендуется рекурсивно обновлять родительские элементы.

3. **`<T> T mapTestObject(Function<GuiTestObject, T> mapper)`**  
   Применяет функцию `mapper` к тестовому объекту. Если ссылка устарела, автоматически использует свежий объект.  
   **Параметры:**  
   - `mapper`: функция для преобразования `GuiTestObject`.  
   **Исключения:**  
   - Может пробросить `UnregisteredObjectException`, если объект не найден.

4. **`void useTestObject(Consumer<GuiTestObject> consumer)`**  
   Выполняет действие `consumer` над тестовым объектом. Обрабатывает устаревшие ссылки аналогично `mapTestObject`.  
   **Параметры:**  
   - `consumer`: действие для выполнения над `GuiTestObject`.

### Dependencies
- **Пакеты:**  
  - `com.rational.test.ft` (RFT-фреймворк, включая `UnregisteredObjectException`).  
  - `java.util.function` (для `Consumer` и `Function`).  
- **Классы:**  
  - `GuiTestObject`, `TestObject` (из RFT).

### Usage Example
```java
// Клик по кнопке с обработкой устаревшей ссылки
button.useTestObject(obj -> {
    obj.click();
});

// Получение текста элемента
String text = label.mapTestObject(obj -> 
    obj.getProperty("text").toString()
);
```

### Uncertainties
1. **Механизм обновления `getFreshTestObject`:** Не ясно, как реализован рекурсивный поиск родительских элементов.
2. **Поведение при повторных ошибках:** Что произойдёт, если `getFreshTestObject` также выбросит `UnregisteredObjectException`?
3. **Кэширование объектов:** Не определён жизненный цикл кэшированных объектов в `getTestObject()`.
4. **Требования к реализации:** Не указано, должны ли реализации гарантировать потокобезопасность.