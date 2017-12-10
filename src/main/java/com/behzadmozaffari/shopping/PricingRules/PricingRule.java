package com.behzadmozaffari.shopping.PricingRules;

import com.behzadmozaffari.shopping.Item;

import java.math.BigDecimal;

public interface PricingRule {

    BigDecimal getDiscount();

    void scan(Item item);
}
