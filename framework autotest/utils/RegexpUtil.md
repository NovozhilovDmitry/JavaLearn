src\main\java\utils
# RegexpUtil.java

## Summary
Утилитный класс для работы с регулярными выражениями. Предоставляет функциональность для экранирования специальных символов в строках, которые будут использоваться в регулярных выражениях. Автоматически обрабатывает все стандартные спецсимволы regex, делая строки безопасными для использования в паттернах.

## Public методы
1. **regexpFrom(String plainString)**  
   Экранирует специальные символы в строке для безопасного использования в регулярных выражениях. Параметр: plainString - исходная строка.

## Dependencies
Нет внешних зависимостей.

## Usage Example
```java
// Экранирование спецсимволов
String safeRegex = RegexpUtil.regexpFrom("file.name+[test]");
// Результат: "file\.name\+\[test\]"

// Использование в поиске
Pattern pattern = Pattern.compile(RegexpUtil.regexpFrom("file.name"));
```

## Uncertainties
1. Не обрабатываются Unicode символы и другие не-ASCII символы
2. Нет методов для обратного преобразования (unescape)
3. Фиксированный набор SPECIAL_CHARACTERS может быть неполным