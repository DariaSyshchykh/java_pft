package ru.stqa.pft.sandbox;

import org.testng.Assert;                               // Импортирование класса Assert с функцией assertEquals
import org.testng.annotations.Test;                     // Полное название для TestNG (строка появляется автоматически
import ru.stqa.pft.sandbox.Square;
//после указания @Test перед функцией)

public class SquareTests {

    @Test                                               // Укороченное название для тестового фремворка TestNG
    public void testArea() {                            // Создаем функцию testArea
        Square s = new Square(5);                  // Обращаемся к классу Square и называем его s, добавляем
                                                        //аргумент 5

/*      assert s.area() == 25;          */              // Одинарный знак "=" это присваивание, а двойной - сравнение
                                                        //Assert утверждает что результат функции area равен 25
        Assert.assertEquals(s.area() , 25.0);  // С помощью функции assertEquals из класса Assert можем
                                                        //сравнить вычесленную площадь и ожидаемое значение ()
    }
}
