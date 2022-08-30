package org.example;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private static void editPricesList(List<Price> prices) {
        for (var i = 1; i < prices.size(); i++) {
            if (prices.get(i).equals(prices.get(i - 1)) && (prices.get(i).getIsNew()
                    || prices.get(i - 1).getIsNew())) {
                var newPrice = prices.get(i).getIsNew() ? prices.get(i) : prices.get(i - 1);
                var oldPrice = !prices.get(i).getIsNew() ? prices.get(i) : prices.get(i - 1);
                var areEqualsValues = oldPrice.getValue() == newPrice.getValue();
                i = checkNewPriceInsideOld(oldPrice, newPrice, areEqualsValues, i, prices);
                i = checkOldPriceInsideNew(oldPrice, newPrice, i, prices);
                i = checkNewPriceIntersectOldEnd(oldPrice, newPrice, areEqualsValues, i, prices);
                i = checkNewPriceIntersectOldBegin(oldPrice, newPrice, areEqualsValues, i, prices);
            }
        }
    }

    private static int checkOldPriceInsideNew(Price oldPrice, Price newPrice, int i, List<Price> prices) {
        if ((newBeginBeforeOldBegin(oldPrice, newPrice) || newPrice.getBegin().isEqual(oldPrice.getBegin()))
                && (newEndAfterOldEnd(oldPrice, newPrice) || newPrice.getEnd().isEqual(oldPrice.getEnd()))) {
            prices.remove(prices.get(i).getIsNew() ? i - 1 : i);
            return i - 1;
        }
        return i;
    }

    private static int checkNewPriceIntersectOldEnd(Price oldPrice, Price newPrice,
                                                    boolean areEqualsValues, int i, List<Price> prices) {
        if (newBeginAfterOldBegin(oldPrice, newPrice) && newEndAfterOldEnd(oldPrice, newPrice)) {
            if (areEqualsValues) {
                newPrice.setBegin(oldPrice.getBegin());
                prices.remove(prices.get(i).getIsNew() ? i - 1 : i);
                return i - 1;
            } else {
                oldPrice.setEnd(newPrice.getBegin().minusSeconds(1));
            }
        }
        return i;
    }

    private static int checkNewPriceIntersectOldBegin(Price oldPrice, Price newPrice,
                                                      boolean areEqualsValues, int i, List<Price> prices) {
        if (newEndAfterOldBegin(oldPrice, newPrice) && newBeginBeforeOldBegin(oldPrice, newPrice)
                && newEndBeforeOldEnd(oldPrice, newPrice)) {
            if (areEqualsValues) {
                newPrice.setEnd(oldPrice.getEnd());
                prices.remove(prices.get(i).getIsNew() ? i - 1 : i);
                return i - 1;
            } else {
                oldPrice.setBegin(newPrice.getEnd().plusSeconds(1));
            }
        }
        return i;
    }

    private static int checkNewPriceInsideOld(Price oldPrice, Price newPrice,
                                              boolean areEqualsValues, int i, List<Price> prices) {
        if ((newBeginAfterOldBegin(oldPrice, newPrice)|| newPrice.getBegin().isEqual(oldPrice.getBegin())) &&
                (newEndBeforeOldEnd(oldPrice, newPrice)||newPrice.getEnd().isEqual(oldPrice.getEnd()))) {
            if (areEqualsValues) {
                prices.remove(prices.get(i).getIsNew() ? i - 1 : i);
                return i - 1;
            } else {
                prices.add(i + 1, new Price(oldPrice, prices.size() + 1,
                        newPrice.getEnd().plusSeconds(1), oldPrice.getEnd()));
                oldPrice.setEnd(newPrice.getBegin().minusSeconds(1));
            }
        }
        return i;
    }

    private static boolean newEndAfterOldEnd(Price oldPrice, Price newPrice) {
        return oldPrice.getEnd().isBefore(newPrice.getEnd());
    }

    private static boolean newBeginBeforeOldBegin(Price oldPrice, Price newPrice) {
        return newPrice.getBegin().isBefore(oldPrice.getBegin());
    }

    private static boolean newEndAfterOldBegin(Price oldPrice, Price newPrice) {
        return newPrice.getEnd().isAfter(oldPrice.getBegin());
    }

    private static boolean newEndBeforeOldEnd(Price oldPrice, Price newPrice) {
        return newPrice.getEnd().isBefore(oldPrice.getEnd());
    }

    private static boolean newBeginAfterOldBegin(Price oldPrice, Price newPrice) {
        return newPrice.getBegin().isAfter(oldPrice.getBegin());
    }

    private static void redactResultArray(List<Price> result){
        for(int i = 0;i<result.size();i++){
            result.get(i).setId(i+1);
            result.get(i).setIsNew(false);
        }
    }
    public static List<Price> mergePrices(List<Price> oldPrices, List<Price> newPrices) {
        for (var price : newPrices) price.setIsNew(true);
        oldPrices.addAll(newPrices);
        Collections.sort(oldPrices);
        editPricesList(oldPrices);
        redactResultArray(oldPrices);
        return oldPrices;
    }
}
