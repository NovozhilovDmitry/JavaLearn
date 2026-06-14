src\main\java\logger
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