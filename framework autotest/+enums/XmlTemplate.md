src\main\java\enums
# XmlTemplate.java

## Summary
Интерфейс для определения контракта XML-шаблонов. Задает основные атрибуты XML-документов: версию, кодировку, standalone-статус и имя схемы. Используется для стандартизации создания и валидации XML-документов в системе.

## Public методы
1. **getVersion()**  
   Возвращает версию XML-документа (например, "1.0").

2. **getEncoding()**  
   Возвращает кодировку XML-документа.

3. **getStandalone()**  
   Возвращает standalone-статус документа (yes/no).

4. **getSchema_name()**  
   Возвращает имя XML-схемы (XSD) для валидации.

## Dependencies
Нет внешних зависимостей.

## Usage Example
```java
// Реализация интерфейса
public class CreditTemplate implements XmlTemplate {
    public String getVersion() { return "1.0"; }
    public String getEncoding() { return "UTF-8"; }
    public Boolean getStandalone() { return true; }
    public String getSchema_name() { return "credit.xsd"; }
}
```

## Uncertainties
1. Неясно, как именно используется этот интерфейс в фреймворке
2. Отсутствуют комментарии о допустимых значениях для каждого метода
3. Не определены ожидаемые форматы для версий и имен схем