src\main\java\enums
# TableColumn.java

## Summary
Интерфейс-маркер для перечислений, представляющих колонки таблиц. Определяет минимальный контракт для enum'ов, которые должны предоставлять строковые значения для идентификации колонок в БД или UI.

## Public методы
1. **getValue()**  
   Возвращает строковое значение колонки. Не имеет параметров.

## Dependencies
Нет внешних зависимостей.

## Usage Example
```java
// Пример реализации
public enum UserColumns implements TableColumn {
    ID("user_id"),
    NAME("user_name");
    
    private String value;
    
    UserColumns(String value) { this.value = value; }
    
    @Override
    public String getValue() { return value; }
}
```

## Uncertainties
1. Неясно, в каких именно контекстах используется этот интерфейс
2. Отсутствуют комментарии о том, что именно представляет возвращаемое значение