package com.behzadmozaffari.shopping;

import java.util.List;

public class Checkout {
    private final List<PricingRule> rules;

    public Checkout(List<PricingRule> rules) {
        this.rules = rules;
    }

    public void scan(Item i) {

    }
}
