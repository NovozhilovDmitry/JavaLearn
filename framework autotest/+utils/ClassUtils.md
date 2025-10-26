src\main\java\utils
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