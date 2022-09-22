package pl.edu.agh.mwo.invoice;

import org.junit.jupiter.api.Test;
import pl.edu.agh.mwo.invoice.product.*;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class InvoiceTest {

    @Test
    public void calculatesTaxAndTotalValue() {
        Invoice invoice = new Invoice();

        invoice.addProduct(new DairyProduct("milkshake", BigDecimal.valueOf(5.0)));
        invoice.addProduct(new TaxFreeProduct("egg", BigDecimal.valueOf(1.0)), 10);

        assertEquals(BigDecimal.valueOf(15.0), invoice.getSubtotal());
        assertEquals(0, BigDecimal.valueOf(0.08 * 5.0).compareTo(invoice.getTax()));
        assertEquals(0, BigDecimal.valueOf(15.0 + 0.08 * 5.0).compareTo(invoice.getTotal()));
    }

    @Test
    public void calculatesTaxAndValueForFuel() {
        Invoice invoice = new Invoice();

        invoice.addProduct(new FuelCanister("Can. 7 oz.", BigDecimal.valueOf(35.0)), 2);

        assertEquals(0, BigDecimal.valueOf(2 * 5.56).compareTo(invoice.getTax()));
        assertEquals(0, BigDecimal.valueOf(70.0).compareTo(invoice.getSubtotal()));
        assertEquals(0, BigDecimal.valueOf(70.0 + 2 * 5.56).compareTo(invoice.getTotal()));
    }

    @Test
    public void dealsWithDuplicates() {
        Invoice invoice = new Invoice();
        Product product = new OtherProduct("Paper", BigDecimal.valueOf(12.0));

        invoice.addProduct(product);
        invoice.addProduct(product);

        assertEquals(1, invoice.getNumberOfProducts());
    }
}