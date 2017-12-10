package com.behzadmozaffari.shopping.pricingRules;

import com.behzadmozaffari.shopping.Item;

import java.math.BigDecimal;

public class TakeOneForFree implements PricingRule {
    private final Item discountedItem;
    private final int countToGetOneFree;
    private int scannedCount;

    public TakeOneForFree(Item discountedItem, int countToGetOneFree) {
        this.discountedItem = discountedItem;
        this.countToGetOneFree = countToGetOneFree;
    }

    @Override
    public BigDecimal getDiscount() {
        return discountedItem.getPrice().multiply(BigDecimal.valueOf(scannedCount / countToGetOneFree));
    }

    @Override
    public void scan(Item scannedItem) {
        if (scannedItem == discountedItem) {
            scannedCount ++;
        }
    }
}
