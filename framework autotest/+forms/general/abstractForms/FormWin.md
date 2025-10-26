src\main\java\forms\general\abstractForms
# FormWin.java

## Описание
Основной интерфейс для оконных форм в фреймворке автотестов. Определяет контракт для работы с GUI-окнами, предоставляет методы поиска окон и доступа к тестовым объектам. Интегрируется с Rational Functional Tester через интерфейс Container и служит базой для всех конкретных форм.

## Public методы
- `win()` - возвращает объект окна (абстрактный метод)
- `searchWindow(PropertyBuilder properties)` - ищет окно по свойствам
- `searchWindow(FindBy findBy, String значениеКласса, FindBy findBy1, String значениеСвойства)` - ищет окно по критериям
- `кнопка(FindBy.TextValue text)` - возвращает кнопку по тексту
- `childrenSource()`, `getTestObject()`, `getFreshTestObject()` - методы из интерфейса Container

## Зависимости
- `base.Base`, `base.PropertyBuilder` - базовые классы и построитель свойств
- `com.rational.test.ft.object.interfaces.GuiTestObject` - RFT тестовые объекты
- `enums.FindBy` - перечисление стратегий поиска
- `userInterface.*` - UI-компоненты

## Пример использования
```java
public class КонкретнаяФорма implements FormWin {
    private Window window;
    
    @Override
    public Window win() {
        return window;
    }
    
    public void какойтоМетод() {
        кнопка(FindBy.TextValue.Закрыть).click();
    }
}
```

## Неясности
1. Разница между `getTestObject()` и `getFreshTestObject()` - когда использовать каждый
2. Логика работы `searchWindow` с разными параметрами - как выбирается стратегия поиска