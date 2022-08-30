package org.example;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class Main {
    //region TestData
    private static List<Price> getOldProducts() {
        List<Price> result = new ArrayList<>();
        result.add(new Price(1, "122856", 1, 1,
                LocalDateTime.of(2013, Month.JANUARY, 5, 0, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 15, 23, 59, 59), 11000));
        result.add(new Price(2, "122856", 1, 1,
                LocalDateTime.of(2013, Month.JANUARY, 16, 0, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 25, 23, 59, 59), 12000));
        result.add(new Price(10, "122856", 1, 1,
                LocalDateTime.of(2013, Month.JANUARY, 26, 0, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 30, 23, 59, 59), 13000));
        result.add(new Price(3, "6654", 1, 2,
                LocalDateTime.of(2013, Month.JANUARY, 1, 0, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 31, 23, 59, 59), 5000));
        return result;
    }

    private static List<Price> getNewProducts() {
        List<Price> result = new ArrayList<>();
        result.add(new Price(4, "122856", 1, 1,
                LocalDateTime.of(2013, Month.JANUARY, 8, 0, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 18, 23, 59, 59), 11000));
        result.add(new Price(7, "122856", 1, 1,
                LocalDateTime.of(2013, Month.JANUARY, 19, 0, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 28, 23, 59, 59), 11002));
        result.add(new Price(5, "122856", 2, 1,
                LocalDateTime.of(2013, Month.JANUARY, 23, 0, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 27, 23, 59, 59), 92000));
        result.add(new Price(6, "6654", 1, 2,
                LocalDateTime.of(2013, Month.JANUARY, 12, 0, 0, 0),
                LocalDateTime.of(2013, Month.JANUARY, 13, 23, 59, 59), 4000));
        return result;
    }


    //endregion


    public static void main(String[] args) {
        var prices = Controller.mergePrices(getOldProducts(), getNewProducts());
        for(var price:prices) System.out.println(price);
    }
}
