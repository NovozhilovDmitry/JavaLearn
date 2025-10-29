src\main\java\userinterface\model\extend\prx
## ProxyContainer.java

### Описание
Специализированный прокси-класс для обертывания RFT-реализаций контейнеров. Наследует базовую функциональность от Proxy и добавляет реализацию интерфейса Container.

**Summary:**
- Специализированная версия Proxy для контейнеров элементов
- Наследует механизм отложенной инициализации и вызова методов
- Реализует интерфейс Container для работы с иерархией элементов

### Public методы
1. **`ProxyContainer(Class<? extends C> wrappedClass, Object... args)`**  
   Конструктор, аналогичный родительскому, но для контейнеров

### Dependencies
- `userInterface.model.extend.interf.Container`
- `userInterface.model.extend.rft.ElementContainer`
- `userInterface.impl.RFT_Tab`

### Пример использования
```java
public class PRX_Tab extends ProxyContainer<RFT_Tab> {
    public PRX_Tab(Container parent, PropertyBuilder props) {
        super(RFT_Tab.class, parent, props);
    }
}
```

### Неясности
1. **Минимальная функциональность**: Класс практически не добавляет новой функциональности кроме типизации
2. **Иерархия контейнеров**: Комментарий показывает сложную вложенность контейнеров, но неясно как это влияет на жизненный цикл объектов