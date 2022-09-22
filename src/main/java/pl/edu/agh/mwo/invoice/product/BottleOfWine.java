package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleOfWine extends Product {

    private static final BigDecimal excise = BigDecimal.valueOf(5.56);

    public BottleOfWine(String name, BigDecimal price) {
        super(name, price, BigDecimal.valueOf(0.23));
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
