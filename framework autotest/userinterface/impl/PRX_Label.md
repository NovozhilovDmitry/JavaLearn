src\main\java\userinterface\impl
## PRX_Label.java

**Summary**:  
Прокси-объект для работы с текстовыми метками. Реализует интерфейс Label и предоставляет методы для проверки текстового содержимого и всплывающих подсказок. Поддерживает получение текста с опциональным удалением HTML-тегов.

**Public методы**:
- Методы проверки сообщений по части строки (`проверка_сообщения_по_части_строки` и др.)
- `getText()`/`getText(boolean removeTags)` – Получение текста метки
- `getTestObject()`/`getFreshTestObject()` – Получение GUI-объектов RFT

**Dependencies**:
- Родительский класс: `Proxy<RFT_Label>`
- GUI-объект: `GuiTestObject` из RFT
- Интерфейсы: `Label`, `Container`

**Usage Example**:
```java
PRX_Label label = new PRX_Label(parent, properties);
label.проверка_сообщения_по_части_строки("успешно");
String text = label.getText(true);
```

**Uncertainties**:
- Разница между основными и "fresh" test objects
- Критерии сравнения "по части строки"
- Логика удаления HTML-тегов в getText()