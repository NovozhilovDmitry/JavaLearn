# full_logger.md - Объединенная документация

## Объединенные файлы:
- HTMLLayout.md
- TGExt.md  
- Watcher.md

---

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

---

# TGExt.java

## Summary
Пустая реализация JUnit 5 TestWatcher интерфейса. Класс предназначен для наблюдения за событиями тестов, но в текущей реализации не содержит никакой функциональности. Возможно, используется как заглушка или основа для будущей реализации.

## Public методы
Нет public методов.

## Dependencies
- JUnit 5 (TestWatcher)

## Usage Example
```java
// В текущем виде класс не имеет функциональности
// Может использоваться как extension в тестах
@ExtendWith(TGExt.class)
public class MyTestClass {
    // тестовые методы
}
```

## Uncertainties
1. Назначение класса не ясно - пустая реализация
2. Непонятно, планировалось ли добавить функциональность
3. Префикс "TG" в названии не расшифрован

---

# Watcher.java

## Summary
JUnit 5 extension для наблюдения за выполнением тестов и управления состоянием приложения. Обрабатывает события начала, успешного завершения и падения тестов. Автоматически создает скриншоты при падениях, управляет закрытием клиентских приложений и создает точки восстановления БД.

## Public методы
1. **beforeTestExecution(ExtensionContext extensionContext)**  
   Вызывается перед выполнением теста. Инициализирует тестовое окружение, устанавливает проект и создает точку восстановления.

2. **testSuccessful(ExtensionContext context)**  
   Обрабатывает успешное завершение теста.

3. **testAborted(ExtensionContext extensionContext, Throwable throwable)**  
   Обрабатывает прерванный тест.

4. **testDisabled(ExtensionContext extensionContext, Optional<String> optional)**  
   Обрабатывает отключенный тест.

5. **testFailed(ExtensionContext context, Throwable cause)**  
   Обрабатывает падение теста - создает скриншот и закрывает приложения.

## Dependencies
- JUnit 5 (TestWatcher, BeforeTestExecutionCallback, ExtensionContext)
- Allure Framework (makeScreenShot)
- base.App (terminate_clients)
- base.MainClass (getSuitesProject, targetProject, currentProject)
- db.Points (создание_короткой_точки)
- Environment (PATH_PROJECT_FOLDER, getConfigProperties)

## Usage Example
```java
// Использование в тестовом классе
@ExtendWith(Watcher.class)
public class MyTestClass {
    
    @Test
    public void myTest() {
        // тестовый код
    }
}
```

## Uncertainties
1. Закомментированный код для прикрепления логов в Allure не функционирует
2. Логика определения проекта (getSuitesProject) не показана в коде
3. Настройка "test.stay_opened_on_error" может быть неочевидной для пользователей
4. Создание точек восстановления зависит от внешней конфигурации
5. Многопоточная безопасность не гарантирована
6. Закомментированный код содержит legacy-логику от TestNG