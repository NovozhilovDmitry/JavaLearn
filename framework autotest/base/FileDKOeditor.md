src\main\java\base
# CurrentProjectSearcher.java - Документация

## Summary
Класс `CurrentProjectSearcher` предоставляет утилиты для поиска и классификации тестовых классов по проектам. Он автоматически сканирует файловую систему для построения карты тестовых классов, сгруппированных по проектам, и предоставляет методы для определения принадлежности класса к проекту.

## Public методы
- **`projectOfClass(String classNamePart)`** - возвращает проект по части имени класса (бросает `ProjectNotFoundError` если не найден)
- **`testClassesByProjects`** - статическое поле, содержащее сгруппированные тестовые классы

## Dependencies
- **`bugbusters.modules.helper.utils.FileSearcher`** - поиск файлов в файловой системе
- **`bugbusters.modules.helper.utils.StringUtils`** - утилиты для работы со строками
- **`enums.Projects`** - перечисление проектов
- **Внешние**: `java.io.File`, `java.util.*`, `java.util.function.Predicate`

## Usage Example
```java
// Определение проекта по имени класса
Projects project = CurrentProjectSearcher.projectOfClass("PaymentTest");

// Получение списка классов для проекта
List<String> testClasses = CurrentProjectSearcher.testClassesByProjects.get(project);
```

## Uncertainties
1. **Критерии поиска** - жесткая привязка к путям с содержанием "test" и имени проекта может быть ненадежной
2. **Инициализация** - статический блок может вызвать проблемы производительности при старте
3. **Обновление карты** - отсутствует механизм обновления карты классов при добавлении новых тестов
4. **Кэширование** - результаты поиска кэшируются статически, что может быть проблемой в динамической среде