package com.behzadmozaffari.shopping;

import java.math.BigDecimal;
import java.util.List;

public class Checkout {
    private final List<PricingRule> rules;

    public Checkout(List<PricingRule> rules) {
        this.rules = rules;
    }

    public void scan(Item i) {

    }

    public BigDecimal total() {
        return BigDecimal.ZERO;
    }
}
