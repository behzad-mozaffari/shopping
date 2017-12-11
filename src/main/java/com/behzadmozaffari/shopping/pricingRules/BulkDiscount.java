package com.behzadmozaffari.shopping.pricingRules;

import com.behzadmozaffari.shopping.Item;

import java.math.BigDecimal;

public class BulkDiscount implements PricingRule {

    private Item itemType;
    private int minimumCount;
    private BigDecimal priceAfterDiscount;
    private int scanned;

    public BulkDiscount(Item itemType, int minimumCount, BigDecimal priceAfterDiscount) {
        if ((priceAfterDiscount.compareTo(itemType.getPrice()) > 0)
                || (priceAfterDiscount.compareTo(BigDecimal.ZERO) < 0)) {
            throw new com.behzadmozaffari.shopping.PricingRules.InvalidDiscountedPrice();
        }
        this.itemType = itemType;
        this.minimumCount = minimumCount;
        this.priceAfterDiscount = priceAfterDiscount;
    }

    @Override
    public BigDecimal getDiscount() {
        if (scanned < minimumCount) {
            return BigDecimal.valueOf(0, 2);
        }
        return itemType.getPrice().subtract(priceAfterDiscount).multiply(BigDecimal.valueOf(scanned));
    }

    @Override
    public void scan(Item item) {
        if (item == itemType) {
            scanned ++;
        }
    }
}
