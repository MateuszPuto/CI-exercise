package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanister extends Product {

    public static final BigDecimal excise = BigDecimal.valueOf(5.56);

    public FuelCanister(String name, BigDecimal price) {
        super(name, price, BigDecimal.ZERO);
    }

    @Override
    public BigDecimal getTaxPercent() {
        return super.getTaxPercent().add(excise);
    }

    @Override
    public BigDecimal getPriceWithTax() {
        return super.getPriceWithTax().add(excise);
    }
}
