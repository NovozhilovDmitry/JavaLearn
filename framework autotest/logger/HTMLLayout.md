src\main\java\logger
# HTMLLayout.java

## Summary
Кастомная реализация HTML-лоута для Logback, переопределяющая стили генерируемого log.html файла. Настраивает внешний вид таблицы логов с чередованием цветов строк, выделением ошибок и предупреждений. Улучшает читаемость логов за счет специального форматирования для разных уровней сообщений.

## Public методы
1. **HTMLLayout()**  
   Конструктор - инициализирует лайаут с кастомным CSS билдером.

## Dependencies
- Logback Classic (ch.qos.logback.classic.html.HTMLLayout)
- Logback Core (ch.qos.logback.core.html.CssBuilder, CoreConstants)

## Usage Example
```xml
<!-- В logback.xml -->
<appender name="HTML" class="ch.qos.logback.core.FileAppender">
    <file>logs/log.html</file>
    <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
        <layout class="logger.HTMLLayout"/>
    </encoder>
</appender>
```

## Uncertainties
1. Не ясно, используется ли этот лайаут в текущей конфигурации логирования
2. Стили могут конфликтовать с корпоративными стандартами оформления
3. Отсутствует адаптивность для мобильных устройств