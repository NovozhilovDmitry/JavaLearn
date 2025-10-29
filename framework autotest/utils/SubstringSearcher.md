src\main\java\utils
# SubstringSearcher.java

## Summary
Утилитный класс для поиска подстрок в тексте с расширенными возможностями настройки. Позволяет искать несколько строк одновременно с опциями игнорирования пробелов и регистра. Возвращает детализированный результат поиска с информацией о ненайденных строках. Особенно полезен для проверки содержимого сообщений, логов и UI-элементов.

## Public методы
1. **findAllRows(String expText, String actText, boolean ignoreSpaces, boolean ignoreCase)**  
   Ищет все строки из expText в actText. Параметры: expText - ожидаемый текст (разделенный \\n), actText - актуальный текст, ignoreSpaces - игнорировать пробелы, ignoreCase - игнорировать регистр.

2. **findAllRows(String[] expRows, String actText, boolean ignoreSpaces, boolean ignoreCase)**  
   Ищет массив строк в тексте. Параметры: expRows - массив ожидаемых строк, actText - актуальный текст, ignoreSpaces - игнорировать пробелы, ignoreCase - игнорировать регистр.

## Вложенный класс Result
- **isSuccessful()** - возвращает true если все строки найдены
- **getNotFoundRows()** - возвращает список ненайденных строк

## Dependencies
- bugbusters.modules.helper.utils.StringUtils
- SLF4J (логирование)

## Usage Example
```java
String expected = "Строка 1\nСтрока 2\nСтрока 3";
String actual = "Текст... Строка 1 ... Строка 3 ...";

SubstringSearcher.Result result = SubstringSearcher.findAllRows(
    expected, 
    actual, 
    true,  // ignoreSpaces
    false  // ignoreCase
);

if (!result.isSuccessful()) {
    System.out.println("Не найдены: " + result.getNotFoundRows());
}
```

## Uncertainties
1. Удаление найденных подстрок из текста может привести к потере контекста для последующего поиска
2. Нет возможности искать строки в определенном порядке
3. Обработка неразрывных пробелов (\\u00A0) может быть неполной