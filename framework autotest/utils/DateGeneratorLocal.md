src\main\java\utils
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