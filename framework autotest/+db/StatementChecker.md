src\main\java\db
# StatementChecker.java

## Summary
Утилитный класс для проверок существования записей в базе данных через SQL-запросы. Предоставляет статические методы для проверки наличия, отсутствия и обновления записей. Все методы включают Allure-степы для отчетности и используют JUnit assertions для провала тестов.

## Public методы
1. **checkRecordExists(String query)**  
   Проверяет существование хотя бы одной записи по запросу. Параметр: query - SQL запрос, возвращающий количество записей.

2. **checkRecordExists(String query, String msg)**  
   Аналогично предыдущему, но с кастомным сообщением об ошибке.

3. **checkNoRecord(String query, String msg)**  
   Проверяет отсутствие записей по запросу с кастомным сообщением.

4. **checkNoRecord(String query)**  
   Проверяет отсутствие записей по запросу.

5. **checkRecordUpdated(String query)**  
   Проверяет успешность обновления записей.

6. **executeSql(String query)**  
   Выполняет SQL запрос и возвращает результат как строку.

## Dependencies
- JUnit 5 (Assertions.fail)
- Allure Framework (@Step)
- Table.executeQuery для выполнения SQL-запросов
- Environment.getTimeOutProperties для таймаутов

## Usage Example
```java
StatementChecker.checkRecordExists(
    "SELECT COUNT(*) FROM users WHERE status = 'active'"
);

StatementChecker.checkNoRecord(
    "SELECT COUNT(*) FROM temp_data", 
    "Временные данные должны быть очищены"
);
```

## Uncertainties
1. Метод executeSql_rep() объявлен но не реализован
2. Нет валидации SQL-запросов на безопасность
3. Предполагается, что все запросы возвращают числовой результат
4. Закомментированный код содержит альтернативную реализацию
5. Не обрабатываются случаи, когда запрос возвращает NULL