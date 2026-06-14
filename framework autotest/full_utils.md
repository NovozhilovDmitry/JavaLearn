# full_utils.md - Объединенная документация

## Объединенные файлы:
- ClassUtils.md
- ConcurrentUtils.md  
- DateGeneratorLocal.md
- FileSearcher.md
- HTMLtoCyrillic.md
- RegexpUtil.md
- SubstringSearcher.md
- ZIP_archive.md
- gates/GatesCollector.md
- gates/GatesCleaner.md
- xml/XmlChecker.md
- xml/NewXML.md
- xml/Tag.md

---

# ClassUtils.java

## Summary
Утилитный класс для расширенной работы с рефлексией в Java. Предоставляет методы для поиска конструкторов и методов по аргументам, преобразования примитивных типов в объектные, и обработки исключений. Особенно полезен для динамического создания объектов и вызова методов в тестовом фреймворке.

## Public методы
1. **objectiveClass(Class<?> cl)**  
   Преобразует примитивный класс в соответствующий объектный класс.

2. **objectiveClasses(Class<?>[] clss)**  
   Преобразует массив классов (включая примитивы) в объектные классы.

3. **objectiveClasses(Object[] objects)**  
   Возвращает объектные классы для массива объектов.

4. **foundConstructor(Class<T> newInstanceClass, Object... args)**  
   Находит конструктор по классу и аргументам. Может выбросить NoSuchMethodException.

5. **foundConstructor(Class<T> newInstanceClass, List<Class<?>> argsTypes)**  
   Находит конструктор по классу и списку типов аргументов.

6. **asTypeArray(Object... argsArray)**  
   Преобразует массив объектов в массив их классов.

7. **methodBy(Class<?> srcClass, String name, Object... args)**  
   Находит метод по имени и аргументам. Может выбросить NoSuchMethodException.

8. **areTheSame(Class<?>[] expectedTypes, Class<?>[] actualTypes, boolean strict)**  
   Сравнивает два массива классов на совместимость.

9. **constructorBy(Class<E> srcClass, Object... args)**  
   Находит конструктор по аргументам. Может выбросить NoSuchMethodException.

10. **reThrow(Throwable e)**  
    Извлекает и пробрасывает исходное исключение из оберток. Может выбросить RuntimeException.

## Dependencies
- Java Reflection (Constructor, Method, InvocationTargetException)
- bugbusters.modules.helper.utils.StringUtils
- userInterface.utils.TestObjectFinder.TestObjectFinderException
- SLF4J (логирование)

## Usage Example
```java
// Поиск конструктора по аргументам
Constructor<MyClass> constructor = ClassUtils.constructorBy(
    MyClass.class, 
    "строка", 
    123
);

// Поиск метода
Method method = ClassUtils.methodBy(
    MyClass.class, 
    "myMethod", 
    arg1, 
    arg2
);

// Обработка исключений
try {
    method.invoke(object, args);
} catch (InvocationTargetException e) {
    ClassUtils.reThrow(e);
}
```

## Uncertainties
1. Сложная логика сравнения типов может быть подвержена ошибкам
2. Обработка null аргументов в methodBy и constructorBy может привести к неоднозначностям
3. Метод reThrow() может потерять информацию о первоначальном исключении
4. Нет обработки security exceptions при работе с reflection
5. Производительность может страдать при частом использовании рефлексии

---

# ConcurrentUtils.java

## Summary
Утилитный класс для управления параллельным выполнением задач. Обеспечивает запуск и ожидание завершения набора Runnable задач в рамках заданного таймаута. Использует CompletableFuture для асинхронного выполнения и предоставляет перегруженные методы для разных сценариев использования.

## Public методы
1. **waitForAllTasksToComplete(Runnable... tasks)**  
   Ожидает выполнения всех переданных задач в течение 60 секунд. Параметры: tasks - массив задач для выполнения.

2. **waitForAllTasksToComplete(List<CompletableFuture<?>> tasks)**  
   Ожидает выполнения всех переданных CompletableFuture задач в течение 60 секунд.

3. **waitForAllTasksToComplete(int timeLimitMillis, CompletableFuture<?>... tasks)**  
   Ожидает выполнения всех переданных задач в течение заданного времени. Параметры: timeLimitMillis - лимит времени в миллисекундах, tasks - массив CompletableFuture задач.

## Dependencies
- Java Concurrency (CompletableFuture, ExecutionException, TimeoutException)
- SLF4J (логирование)

## Usage Example
```java
// Параллельное выполнение нескольких задач
ConcurrentUtils.waitForAllTasksToComplete(
    () -> выполнитьОперацию1(),
    () -> выполнитьОперацию2(),
    () -> выполнитьОперацию3()
);

// С пользовательским таймаутом
CompletableFuture<?> future1 = CompletableFuture.runAsync(...);
CompletableFuture<?> future2 = CompletableFuture.runAsync(...);
ConcurrentUtils.waitForAllTasksToComplete(30_000, future1, future2);
```

## Uncertainties
1. Ошибки выполнения задач логируются, но не пробрасываются дальше
2. Фиксированный таймаут 60 секунд может быть недостаточным для некоторых операций
3. Нет возможности отменить выполнение задач при превышении таймаута

---

# DateGeneratorLocal.java

## Summary
Расширение стандартного DateGenerator для поддержки специфических форматов дат, используемых в системе ДКО. Добавляет дополнительные форматы представления дат и времени через вложенный enum ФорматыДКО. Статически расширяет доступные форматы DateGenerator при загрузке класса.

## Public методы
Нет public методов, только вложенный enum.

## Вложенные enum'ы
- **ФорматыДКО**: предоставляет дополнительные форматы дат для ДКО (в настоящее время только ДМГ_ЧMC_МСмсc)

## Dependencies
- bugbusters.modules.helper.utils.DateGenerator
- SLF4J (логирование)

## Usage Example
```java
// Использование кастомного формата ДКО
DateGenerator generator = new DateGenerator(DateGeneratorLocal.ФорматыДКО.ДМГ_ЧMC_МСмсc);
String formattedDate = generator.getFormattedDate();
```

## Uncertainties
1. Большинство форматов в enum'е закомментированы с предупреждением об их неиспользовании
2. Не ясно, почему закомментированные форматы нельзя использовать
3. Логика работы с regex в enum'е не реализована (методы возвращают null)
4. Назначение формата ДМГ_ЧMC_МСмсc не документировано

---

# FileSearcher.java

## Summary
Класс для поиска файлов в шлюзах системы по части имени и расширению. Осуществляет поиск в исходящих шлюзах с поддержкой таймаутов и повторных попыток. Автоматически прикрепляет найденные файлы к Allure-отчетам и проверяет корректность файлов (например, пустоту .ready файлов).

## Public методы
1. **FileSearcher(String fileNamePart, Расширения_файлов type)**  
   Конструктор - инициализирует поисковик с частью имени файла и типом расширения.

2. **getFilePath()**  
   Возвращает путь к найденному файлу. Может вызвать fail если файл не найден.

3. **getFullFilePath()**  
   Возвращает полный путь к файлу (путь + имя). Может вызвать fail если файл не найден.

4. **getFileName()**  
   Возвращает имя найденного файла. Может вызвать fail если файл не найден.

5. **поискФайлаВИсхШлюзах(Шлюзы... gates)**  
   Основной метод поиска файла в исходящих шлюзах. Параметры: gates - ограничивающие шлюзы (опционально).

6. **проверка_количества_выгруженных_файлов(int количество)**  
   Проверяет количество выгруженных файлов без указания шлюзов.

7. **проверка_количества_выгруженных_файлов(int количество, Шлюзы... gates)**  
   Проверяет количество выгруженных файлов в указанных шлюзах.

## Dependencies
- Allure Framework (@Step, Attachments.attachFile)
- enums.Расширения_файлов, enums.Шлюзы
- model.Gate
- utils.Helper (threadSleep)
- org.apache.commons.lang3.StringUtils
- JUnit 5 (Assertions.fail)

## Usage Example
```java
// Поиск файла в шлюзах
FileSearcher searcher = new FileSearcher("report_", Расширения_файлов.XLS)
    .поискФайлаВИсхШлюзах(Шлюзы.ODBR, Шлюзы.KO);

// Получение результатов
String filePath = searcher.getFullFilePath();

// Проверка количества файлов
new FileSearcher("data_", Расширения_файлов.XML)
    .проверка_количества_выгруженных_файлов(3);
```

## Uncertainties
1. Логика обработки .ready файлов (проверка пустоты) может быть неочевидной
2. Поиск с таймаутом может приводить к долгому выполнению тестов
3. Обработка нескольких найденных файлов приводит к fail, что не всегда желательно
4. Метод getExtension() имеет сложную логику обработки "ready" расширений
5. Нет возможности искать файлы по нескольким расширениям одновременно

---

# HTMLtoCyrillic.java

## Summary
Утилитный класс для преобразования HTML-кодированных символов в кириллические символы. Специализируется на обработке числовых HTML-сущностей (например, &#160;) и их преобразовании в соответствующие Unicode символы. Использует Jsoup для парсинга HTML и кэширует преобразованные символы для оптимизации.

## Public методы
1. **convertHTMLSymbols(String text)**  
   Преобразует HTML-кодированные символы в кириллицу. Параметр: text - текст с HTML-сущностями.

## Dependencies
- Jsoup (для парсинга HTML сущностей)
- java.util.regex (Pattern, Matcher)

## Usage Example
```java
// Преобразование HTML сущностей
String htmlText = "Текст с&#160;неразрывными пробелами";
String converted = HTMLtoCyrillic.convertHTMLSymbols(htmlText);
// Результат: "Текст с\u00A0неразрывными пробелами"
```

## Uncertainties
1. Обрабатываются только числовые HTML-сущности (&#160;), но не именованные (&nbsp;)
2. Производительность может страдать при обработке больших текстов
3. Нет обработки ошибок для некорректных HTML-сущностей
4. Кэш PARSED_HTML_CHARS не ограничен по размеру

---

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

---

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

---

# ZIP_archive.java

## Summary
Комплексный класс для работы с ZIP-архивами в тестовом фреймворке. Предоставляет полный цикл операций: создание, открытие, поиск в шлюзах, извлечение и добавление файлов, редактирование содержимого. Поддерживает работу с различными типами файлов внутри архивов (XML, XLS) и интеграцию с Allure-отчетами.

## Public методы
1. **ZIP_archive(String часть_имени, Расширения_файлов расширение)**  
   Конструктор - создает объект архива по части имени и расширению.

2. **reference(String часть_имени, Расширения_файлов расширение)**  
   Статический метод для создания ссылки на эталонный архив.

3. **создать(String путь)**, **создать()**  
   Создает пустой архив по указанному пути или во временном шлюзе.

4. **открыть(String путь)**  
   Открывает существующий архив.

5. **найти_в_шлюзах(Шлюзы... шлюзы)**  
   Ищет архив в указанных шлюзах по части имени.

6. **извлечь_файл(String часть_имени, Шлюзы шлюз)**  
   Извлекает файл из архива в указанный шлюз.

7. **извлечь_файлы()**, **извлечь_файлы(Шлюзы шлюз)**  
   Извлекает все файлы из архива.

8. **добавить_файлы(String... полные_имена_файлов)**  
   Добавляет файлы в архив.

9. **получить_файлы_архива()**  
   Читает содержимое всех файлов архива в память.

10. **проверитьКоличествоФайловАрхива(int count)**  
    Проверяет количество файлов в архиве.

11. **поместить_в_шлюз(Шлюзы шлюз)**  
    Копирует архив в указанный шлюз.

12. **редактировать()**  
    Возвращает Editor для редактирования содержимого архива.

## Вложенные классы
- **Editor** - для редактирования содержимого архивов (изменение текста, добавление файлов)
- **Envelope** - специализированный класс для работы с конвертами документов

## Dependencies
- Java ZIP API (ZipFile, ZipEntry, ZipOutputStream)
- Allure Framework (@Step, Attachments)
- utils.FileSearcher для поиска файлов
- base.FileChecker, XlsFileChecker для работы с файлами
- utils.XML.NewXML для работы с XML
- enums.Шлюзы, Расширения_файлов

## Usage Example
```java
// Создание и наполнение архива
new ZIP_archive("report", Расширения_файлов.ZIP)
    .создать()
    .добавить_файлы("file1.xml", "file2.xls")
    .поместить_в_шлюз(Шлюзы.ODBR);

// Поиск и извлечение из архива
ZIP_archive archive = new ZIP_archive("data", Расширения_файлов.ZIP)
    .найти_в_шлюзах(Шлюзы.KO)
    .извлечь_файл("config", Шлюзы.tmp);

// Редактирование архива
archive.редактировать()
    .изменить_текст_в_файле("config", "old_value", "new_value")
    .применить();
```

## Uncertainties
1. Сложная логика работы с путями может вызвать проблемы на разных ОС
2. Обработка больших файлов может привести к проблемам с памятью
3. Многопоточная безопасность не гарантирована
4. Закомментированный код для вложенных архивов (InnerZIP) не функционирует
5. Методы с кириллическими названиями могут вызвать проблемы в некоторых средах
6. Обработка ошибок в некоторых местах может быть недостаточной

---

# GatesCollector.java

## Описание
Утилита для сбора шлюзов и их абсолютных путей в виде ordered map. Преобразует относительные пути в абсолютные используя server_path, и сопоставляет их с enum шлюзов по наличию ключевых слов в путях.

## Public методы
- `collectAll(List<String> relativePaths)` - заполняет коллекцию на основе списка относительных путей

## Зависимости
- `enums.Шлюзы` - перечисление шлюзов
- `env.Environment` - для получения server_path

## Пример использования
```java
GatesCollector collector = new GatesCollector();
collector.collectAll(Arrays.asList("gate1/forms", "gate2/downloads"));
// Результат: {ШЛЮЗ1: "C:\server\gate1\forms\", ШЛЮЗ2: "C:\server\gate2\downloads\"}
```

## Неясности
1. Алгоритм сопоставления путей с enum - что если путь содержит несколько ключевых слов?
2. Обработка дубликатов - поведение при повторном вызове collectAll
3. Кодировка путей - как обрабатываются пути с не-ASCII символами

---

# GatesCleaner.java

## Описание
Вспомогательный класс для очистки файлов в шлюзах. Поддерживает многопоточное удаление файлов по заданным критериям (регулярные выражения имён файлов и расширения). Автоматически закрывает процессы Excel перед очисткой и повторяет попытки удаления в течение заданного таймаута.

## Public методы
- `clearDownloadedForms(Шлюзы... gates)` - статический метод для очистки всех или указанных шлюзов
- `clearDownloadedForms(GatesCleaner cleaner, Шлюзы... gates)` - статический метод с кастомной конфигурацией
- `withTimeoutMillis(int timeoutMillis)` - установка таймаута операций (мс)
- `withFileNamesRegex(String fileNamesRegex)` - установка regex для обязательного удаления файлов
- `withIncExtS(Расширения_файлов... incExtS)` - фильтр по расширениям файлов
- `withErrorsKeeper(ErrorsKeeper errorsKeeper)` - кастомный обработчик ошибок
- `clear(List<Gate> gates)` - основной метод очистки списка шлюзов

## Зависимости
- `base.App` - для завершения процессов Excel
- `bugbusters.modules.helper.utils.StringUtils` - утилиты строк
- `enums.Расширения_файлов` - перечисление расширений
- `enums.Шлюзы` - перечисление шлюзов  
- `model.Gate` - модель шлюза
- `utils.ConcurrentUtils` - многопоточные утилиты

## Пример использования
```java
// Базовая очистка всех шлюзов
GatesCleaner.clearDownloadedForms();

// Кастомная очистка
GatesCleaner cleaner = new GatesCleaner()
    .withTimeoutMillis(5000)
    .withFileNamesRegex("Form0(11|22).*");
GatesCleaner.clearDownloadedForms(cleaner, Шлюзы.ШЛЮЗ1, Шлюзы.ШЛЮЗ2);
```

## Неясности
1. Логика повторных попыток удаления - неясно как именно работает цикл while с таймаутом
2. Поведение `App.terminate_excel(false)` - что делает параметр false?
3. Ожидание задач - как `ConcurrentUtils.waitForAllTasksToComplete` обрабатывает исключения

---

# XmlChecker.java

## Описание
Устаревший класс для поиска, парсинга и валидации XML-файлов в шлюзах автотестов. Обеспечивает загрузку XML из файлов и строк, валидацию по XSD-схемам, сравнение XML и извлечение данных через XPath. Интегрирован с системой шлюзов и шаблонов XML.

## Public методы
- `XmlChecker(String fileNamePart, XmlTemplate album, Расширения_файлов extension, Шлюзы... шлюзы)` - конструктор с поиском файла
- `сравнитьXML(String ожидаемый_xml, String проверяемый_xml)` - сравнение двух XML строк
- `static fromHTML(Path fullFileName)` - создание из HTML файла
- `static fromHTML(String html_content)` - создание из HTML строки
- `получитьXMLизСтроки(String xml_content)` - парсинг XML из строки
- `получитьЗначениеЧерезXpath(String xpath_выражение)` - извлечение значения через XPath
- `проверить_по_шаблону(NewXML album)` - проверка XML против шаблона
- `изменить_значение_атрибутов(String имяАтрибута, String новоеЗначение)` - массовое изменение атрибутов

## Зависимости
- `org.custommonkey.xmlunit.*` - утилиты сравнения XML
- `enums.XmlTemplate` - перечисление шаблонов XML
- `utils.FileSearcher` - поиск файлов
- `javax.xml.*` - стандартные XML утилиты Java

## Пример использования
```java
XmlChecker checker = new XmlChecker("ED410_", XmlTemplate.ED410, XML, Шлюзы.ШЛЮЗ1);
checker.проверить_по_шаблону(expectedAlbum);
String value = checker.получитьЗначениеЧерезXpath("//igr:File/@igr:fileIdentity");
```

## Неясности
1. Логика работы `getXpathNamespaceContext()` - жестко закодированные namespace
2. Алгоритм поиска файлов в шлюзах - как определяется приоритет шлюзов
3. Обработка ошибок валидации - какие исключения ожидаются
4. Работа с датами в `изменить_значение_атрибута()` - преобразование форматов

---

# NewXML.java

## Описание
Устаревший класс-наследник Tag для работы с XML-альбомами УФЭБС. Предоставляет функциональность для создания, сериализации и выгрузки XML-документов по шаблонам. Включает вложенный класс Table для парсинга HTML-таблиц из XML. Класс помечен как deprecated.

## Public методы
- `NewXML(XmlTemplate template, String tagName, String[] attributes, String text, Tag... innerTag)` - конструктор с шаблоном
- `NewXML(String xmlContent)` - конструктор из XML строки
- `выгрузить_файл(Шлюзы шлюз, String имяФайла, Расширения_файлов... exts)` - выгрузка в шлюз
- `выгрузить_файл(String имяФайла, Расширения_файлов... exts)` - выгрузка во временный каталог
- `static t1_contains_t2(List<LinkedHashMap<String, String>> t1, List<LinkedHashMap<String, String>> t2)` - сравнение таблиц
- `static map(String key1, String value1, String... keyValue_pair)` - создание мапы для таблиц

## Зависимости
- `org.apache.xml.serialize.*` - сериализация XML
- `base.NewTXT` - работа с текстовыми файлами
- `enums.Шлюзы` - перечисление шлюзов
- `org.apache.commons.lang.StringEscapeUtils` - обработка HTML

## Пример использования
```java
NewXML ed410 = new NewXML(XmlTemplate.ED410, "ED410", new String[]{}, "");
ed410.выгрузить_файл(Шлюзы.ШЛЮЗ1, "test_ed410", XML);

NewXML.Table table = new NewXML.Table(htmlContent);
List<LinkedHashMap<String, String>> data = table.table();
```

## Неясности
1. Назначение поля `standalone` - как влияет на генерацию XML
2. Логика работы `prepareString()` - сложная обработка HTML-строк
3. Алгоритм выгрузки файлов - копирование между временными каталогами и шлюзами
4. Работа с кодировками - почему используется windows-1251

---

# Tag.java

## Описание
Устаревший класс для работы с XML-тегами в автотестах. Представляет собой модель тега с атрибутами, текстовым содержимым и вложенными тегами. Обеспечивает сравнение тегов, поиск по имени и извлечение атрибутов. Класс помечен как deprecated и должен быть заменен более современными решениями.

## Public методы
- `Tag(String name, String[] attributes, String text, Tag... innerTags)` - конструктор с массивом атрибутов
- `getAsElement(Document doc)` - преобразует тег в DOM Element
- `compareTo(Tag tag)` - сравнивает два тега (выбрасывает исключение при различиях)
- `static compare(Tag tag1, Tag tag2)` - статический метод сравнения двух тегов с прикреплением в Allure
- `getInnerTagsByName(String name)` - возвращает вложенные теги по имени
- `get_значение_атрибута(String attr_name)` - ищет значение атрибута в текущем и вложенных тегах
- `equals(Object other)` - сравнение тегов на равенство

## Зависимости
- `io.qameta.allure.Step` - для аннотаций Allure
- `org.w3c.dom.*` - для работы с DOM
- `base.XlsChecker` - утилиты для проверки XLS
- `bugbusters.modules.extensions.allure.utils.Attachments` - вложения в Allure

## Пример использования
```java
Tag address = new Tag("Address", new String[]{"city=Moscow", "street=Lenina"}, "");
Tag person = new Tag("Person", new String[]{"id=123"}, "John Doe", address);
person.compareTo(expectedPerson);
```

## Неясности
1. Назначение поля `attribute_exceptions` - почему именно эти атрибуты исключаются из сравнения?
2. Логика работы `without_exceptions()` - как именно фильтруются атрибуты
3. Поведение при сравнении тегов с разной структурой вложенности
4. Назначение флага `isRequired` - не используется в предоставленном коде