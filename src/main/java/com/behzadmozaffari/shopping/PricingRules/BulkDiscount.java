package com.behzadmozaffari.shopping.PricingRules;

import com.behzadmozaffari.shopping.Item;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class BulkDiscount implements PricingRule {
    private final static Logger LOGGER = Logger.getLogger(BulkDiscount.class.getName());

    private Item itemType;
    private int minimumCount;
    private BigDecimal priceAfterDiscount;
    private int scanned;

    public BulkDiscount(Item itemType, int minimumCount, BigDecimal priceAfterDiscount) {
        this.itemType = itemType;
        this.minimumCount = minimumCount;
        this.priceAfterDiscount = priceAfterDiscount;
    }

    @Override
    public BigDecimal getDiscount() {
        BigDecimal discount = itemType.getPrice().subtract(priceAfterDiscount).multiply(BigDecimal.valueOf(scanned));
        LOGGER.info("getting bulk item discount, counted " + scanned + " items, discount : " + discount);
        return discount;
    }

    @Override
    public void scan(Item item) {
        if (item == itemType) {
            scanned ++;
        }
    }
}
