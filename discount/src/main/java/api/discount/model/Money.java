package api.discount.model;

import java.math.BigDecimal;

public class Money {

    public static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;

    // ==== 생성자 ==== //
    protected Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }
    public static Money wons(BigDecimal amount) {
        return new Money(amount);
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    // ===== 접근자 ==== //
    public BigDecimal getAmount() {
        return this.amount;
    }

    // ==== 연산자 ==== //
    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public Money times(int count) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(count)));
    }
}
