src\main\java\env
# Environment.java

## Summary
Утилитный класс для управления конфигурацией тестового окружения и файловыми операциями. Предоставляет методы для чтения и установки свойств из различных конфигурационных файлов (БД, клиентские настройки, заголовки, таймауты). Обеспечивает копирование тестовых файлов из ресурсов в шлюзы системы с интеграцией с Allure-отчетами.

## Public методы
1. **readPropertyFromConfig(String property, String fullConfigFilePath, String encoding)**  
   Deprecated - читает свойство из конфиг-файла с указанной кодировкой.
2. **stringEncoded(String property, String initialEncoding)**  
   Перекодирует строку из заданной кодировки. Параметры: property - исходная строка, initialEncoding - исходная кодировка.
3. **readPropertyFromConfig(String property, String fullConfigFilePath)**  
   Deprecated - читает свойство из конфиг-файла в UTF-8.
4. **readIntPropertyFromConfig(String property, String fullConfigFilePath)**  
   Deprecated - читает целочисленное свойство из конфиг-файла.
5. **readPropertyFromConfig(String property)**  
   Читает свойство из конфигурации через ProjectProperties.
6. **getDbProperties(String propertyName, String... defaultValue)**  
   Возвращает свойство БД для текущего проекта. Параметры: propertyName - имя свойства, defaultValue - опциональное значение по умолчанию.
7. **getClientProperties(String propertyName, String... defaultValue)**  
   Возвращает клиентское свойство для текущего проекта.
8. **setClientProperties(String propertyName, String value)**  
   Устанавливает клиентское свойство для текущего проекта.
9. **setClientProperties(Projects project, String propertyName, String value)**  
   Устанавливает клиентское свойство для указанного проекта.
10. **setTitleProperties(String propertyName, String value)**  
    Устанавливает свойство заголовка для текущего проекта.
11. **setTitleProperties(Projects project, String propertyName, String value)**  
    Устанавливает свойство заголовка для указанного проекта.
12. **getConfigProperties(String propertyName, String... defaultValue)**  
    Возвращает общее свойство конфигурации.
13. **getTitleProperties(String propertieName, String... defaultValue)**  
    Возвращает свойство заголовка (с особой логикой для "UniqueARM" и "waitForm").
14. **getTimeOutProperties(String propertyName, String... defaultValue)**  
    Возвращает свойство таймаута как Integer.
15. **скопироватьФайлИзРесурсов(String path, String fileName, Шлюзы шлюз)**  
    Копирует файл из ресурсов в указанный шлюз. Параметры: path - относительный путь, fileName - имя файла, шлюз - целевой шлюз. Может вызвать fail при ошибках IO.
16. **main(String[] args)**  
    Выводит системные свойства и значения конфигурации (для отладки).

## Dependencies
- bugbusters.modules.helper.utils.ProjectProperties
- enums.Projects, enums.Шлюзы
- Allure Framework (@Step, Attachments.attachFile)
- SLF4J (логирование)
- Java NIO (Files, Paths) и IO (InputStream, BufferedReader)
- JUnit 5 (Assertions.fail)
- base.MainClass (currentProject, threadSleep)

## Usage Example
```java
// Получение параметров БД
String dbHost = Environment.getDbProperties("ip_addr");
String dbUser = Environment.getDbProperties("user", "default_user");

// Копирование тестового файла в шлюз
Environment.скопироватьФайлИзРесурсов(
    "testdata", 
    "input.xml", 
    Шлюзы.ODBR
);

// Установка клиентских свойств
Environment.setClientProperties("timeout", "5000");
```

## Uncertainties
1. Несколько методов помечены как Deprecated без четкой миграционной стратегии
2. Назначение config_enviroment не до конца ясно (системная property + точка)
3. Логика в getTitleProperties для "UniqueARM" и "waitForm" может быть неочевидной
4. Метод stringEncoded() имеет неясное практическое применение
5. Переменная user объявлена но не используется
6. Обработка ошибок в deprecated методах через printStackTrace не соответствует лучшим практикам
7. Не ясна структура и расположение конфигурационных файлов