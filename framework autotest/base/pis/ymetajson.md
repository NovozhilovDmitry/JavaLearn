src\main\java\base\Pis
# YMetaJson - Документация

## Summary
Класс `YMetaJson` представляет DTO для файла метаинформации `y.meta.json` в составе интеграционного конверта ПИС 1Х. Он содержит структурированные данные о маршрутизации, приоритете, вложенных файлах и дополнительных атрибутах сообщения. Класс предоставляет методы для удобного поиска и доступа к компонентам конверта, включая определение направления сообщения (входящее/исходящее).

## Public методы

### Основные методы
- **`isIncoming()`** - проверяет, является ли конверт входящим (по наличию "-ASDKO" в routeInstanceId)
- **`findContent(Predicate<PisContent> condition)`** - ищет контент по пользовательскому условию
- **`findContent(Type type)`** - ищет контент по типу (DOCUMENT, ESODRECEIPT, SIGN, STATUSINFO)

### Геттеры/сеттеры
Для всех основных полей: `baseSystemId`, `routeInstanceId`, `srcMsgId`, `tid`, `priority`, `content`, `extraAttributes`

### Методы вложенных классов
**`Protocol.Sender`/`Receiver`:**
- **`optionalFeatureByCode(String code)`** - ищет характеристику по коду (Optional)
- **`featureByCode(String code)`** - ищет характеристику по коду (бросает RuntimeException если не найдено)
- **`get(String code)`** - возвращает значение характеристики по коду

## Dependencies
- **`com.google.gson.annotations.SerializedName`** - аннотации для JSON сериализации
- Стандартные Java утилиты: `List`, `Optional`, `Predicate`, `Objects`

## Usage Example
```java
YMetaJson meta = gson.fromJson(jsonContent, YMetaJson.class);

// Проверка направления
if (meta.isIncoming()) {
    // Обработка входящего сообщения
}

// Поиск документа
Optional<YMetaJson.PisContent> document = meta.findContent(YMetaJson.Type.DOCUMENT);

// Получение ИНН отправителя
String inn = meta.getExtraAttributes().getProtocol().getSender().get("INN");
```

## Uncertainties
1. **Логика определения направления** - проверка на "-ASDKO" может быть ненадежной при изменении naming convention
2. **Назначение полей** - точное назначение `baseSystemId`, `routeInstanceId`, `tid` требует дополнительной документации
3. **Статус destinationId** - поле закомментировано, но упоминается в комментариях
4. **Валидные значения** - допустимые значения для `protocolType`, кодов характеристик (INN, OGRN и т.д.)
5. **Обработка получателей** - методы `getReceiver()`/`setReceiver()` работают только с первым получателем из списка
6. **Обязательность полей** - какие поля являются обязательными в реальных сценариях