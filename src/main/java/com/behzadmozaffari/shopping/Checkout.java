package com.behzadmozaffari.shopping;

import com.behzadmozaffari.shopping.PricingRules.PricingRule;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


public class Checkout {
    private final List<PricingRule> rules;
    private BigDecimal total = new BigDecimal(BigInteger.ZERO, 2);

    public Checkout(List<PricingRule> rules) {
        this.rules = rules;
    }

    public void scan(Item i) {
        rules.forEach(r -> r.scan(i));
        total = total.add(i.getPrice());
    }

    public BigDecimal total() {
        return rules.stream().
                map(PricingRule::getDiscount).
                peek(System.out::println).
                reduce(total, BigDecimal::subtract);
    }
}
