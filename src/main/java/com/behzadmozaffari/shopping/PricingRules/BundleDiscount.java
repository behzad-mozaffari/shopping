package com.behzadmozaffari.shopping.PricingRules;

import com.behzadmozaffari.shopping.Item;

import java.math.BigDecimal;

public class BundleDiscount implements PricingRule {
    private final Item paid;
    private final Item free;
    private int paidCount;
    private int freeCount;

    public BundleDiscount(Item paid, Item free) {
        if (paid == free) {
            throw new InvalidBundleItems();
        }
        this.paid = paid;
        this.free = free;
    }

    @Override
    public BigDecimal getDiscount() {
        return free.getPrice().multiply(new BigDecimal(Math.min(freeCount, paidCount)));
    }

    @Override
    public void scan(Item item) {
        if (item == paid) {
            paidCount ++;
        } else if (item == free) {
            freeCount ++;
        }
    }
}
