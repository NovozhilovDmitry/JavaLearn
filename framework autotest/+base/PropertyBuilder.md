src\main\java\base
# PropertyBuilder.java - Документация

## Summary
Класс `PropertyBuilder` представляет собой специализированную карту свойств для поиска элементов в тестовом фреймворке. Он предоставляет удобные методы для создания и управления свойствами элементов UI в соответствии со стратегиями поиска RFT. Класс служит адаптером между enum-подходом фреймворка и нативным API Rational Functional Tester.

## Public методы
- **`of(FindBy findBy, String value)`** - статический конструктор с указанием стратегии поиска и значения
- **`of(String className)`** - статический конструктор для поиска по классу
- **`of(ConstantClass constantClass)`** - статический конструктор для поиска по константному классу
- **`and(FindBy findBy, String value)`** - добавляет свойство к существующему builder (алиас для put)
- **`put(FindBy findBy, String value)`** - добавляет свойство с enum-ключом
- **`getProperties()`** - преобразует карту в массив Property для RFT API
- **`get(FindBy findBy)`** / **`containsKey(FindBy findBy)`** / **`remove(FindBy findBy)`** - методы работы с enum-ключами

## Dependencies
- **`com.rational.test.ft.script.Property`** - класс свойств RFT
- **`enums.FindBy`** - перечисление стратегий поиска элементов
- **Внешние**: `java.util.HashMap`

## Usage Example
```java
PropertyBuilder props = PropertyBuilder.of(FindBy.Class, "asdco.ARM_KO.FrmGate")
    .and(FindBy.CaptionText, "Шлюзы")
    .and(FindBy.AccessibleName, "MainForm");
Property[] rftProps = props.getProperties();
```

## Uncertainties
1. **Валидация значений** - проверяется только null, но не валидность значений для RFT
2. **Производительность** - преобразование в массив Property при каждом вызове getProperties()
3. **Расширяемость** - жесткая привязка к enum FindBy ограничивает использование произвольных свойств