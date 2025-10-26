src\main\java\db
# Table.java

## Summary
Класс-обертка для выполнения SQL-запросов к базе данных. Предоставляет синхронизированные методы для различных типов запросов: SELECT, UPDATE, INSERT, DELETE. Поддерживает возврат результатов в различных форматах: одиночные значения, списки, таблицы. Включает подробное логирование всех операций.

## Public методы
1. **execute(String query, String columName)**  
   Выполняет запрос и возвращает ResultSet. Параметры: query - SQL запрос, columName - имя колонки.

2. **executeQuery(String query, String columName)**  
   Выполняет SELECT и возвращает значение указанной колонки из первой строки.

3. **executeQuery(String query, int columnIndex)**  
   Аналогично предыдущему, но с указанием индекса колонки.

4. **executeQueryData(String query, int row)**  
   Возвращает все колонки указанной строки в виде списка.

5. **executeQueryData(String query, String columnName)**  
   Возвращает все значения указанной колонки в виде списка.

6. **getTable(String query)**  
   Возвращает всю результирующую таблицу как List<LinkedHashMap>.

7. **execute(String sql)**  
   Выполняет любой SQL запрос, возвращает boolean результат.

8. **update_DB(String sql)**  
   Выполняет UPDATE/INSERT/DELETE запросы с коммитом.

## Dependencies
- JDBC (ResultSet, Statement, SQLException)
- Внутренняя утилита StringUtils для форматирования
- RFT_Table.таблица_toString для преобразования таблиц
- JDBCConnect для получения соединения с БД

## Usage Example
```java
// Получение одного значения
String userName = Table.executeQuery(
    "SELECT name FROM users WHERE id = 1", 
    "name"
);

// Получение списка значений
List<String> userNames = Table.executeQueryData(
    "SELECT name FROM users", 
    "name"
);

// Выполнение UPDATE
Table.update_DB("UPDATE users SET status = 'inactive'");
```

## Uncertainties
1. Два deprecated метода (executeStatement) не синхронизированы
2. Метод executeQueryData(String, int) может быть неочевидным в использовании
3. Нет обработки случаев, когда запрос возвращает пустой результат
4. Логирование может раскрывать чувствительные данные
5. Не ясна логика работы с большими объемами данных