package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import pl.edu.agh.mwo.invoice.product.DairyProduct;
import pl.edu.agh.mwo.invoice.product.Product;
import pl.edu.agh.mwo.invoice.product.TaxFreeProduct;

public class Invoice {
    private long number;
    private Map<Product, Integer> products;

    public static void main(String[] args) {
        Invoice invoice = new Invoice();
        invoice.addProduct(new DairyProduct("milkshake", BigDecimal.valueOf(5.0)));
        invoice.addProduct(new TaxFreeProduct("egg", BigDecimal.valueOf(1.0)), 10);

        System.out.println(invoice);
    }

    public Invoice() {
        Random randomGenerator = new Random();
        this.number = randomGenerator.nextLong();

        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        if(products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    public void addProduct(Product product, Integer quantity) {
        for(int i=0; i<quantity; i++) {
            this.addProduct(product);
        }
    }

    public int getNumberOfProducts() {
        return products.keySet().size();
    }

    public BigDecimal getSubtotal() {
        BigDecimal total = new BigDecimal(0.0);

        for(Product p: products.keySet()) {
            total = total.add(p.getPrice().multiply(BigDecimal.valueOf(products.get(p))));
        }

        return total;
    }

    public BigDecimal getTax() {
        BigDecimal total = new BigDecimal(0.0);

        for(Product p: products.keySet()) {
            total = total.add(p.getTaxPercent().multiply(p.getPrice().multiply(BigDecimal.valueOf(products.get(p)))));
        }

        return total;
    }

    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(0.0);

        for(Product p: products.keySet()) {
            total = total.add(p.getPriceWithTax().multiply(BigDecimal.valueOf(products.get(p))));
        }

        return total;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("#####################\n");

        stringBuilder.append("Invoice number: ");
        stringBuilder.append(this.number);
        stringBuilder.append("\n");

        int numOfProducts = 0;

        for(Map.Entry position: products.entrySet()) {
            stringBuilder.append(position.getKey().getClass().getSimpleName());
            stringBuilder.append(": ");
            stringBuilder.append(position.getValue());

            if(position.getValue() == Integer.valueOf(1)) {
                stringBuilder.append(" item with unit price of ");
            } else {
                stringBuilder.append(" items with unit price of ");
            }
            
            Product product = (Product) position.getKey();
            stringBuilder.append(product.getPriceWithTax());
            stringBuilder.append("\n");

            numOfProducts += 1;
        }

        stringBuilder.append("Total positions: ");
        stringBuilder.append(numOfProducts);

       stringBuilder.append("\n#####################");

        return stringBuilder.toString();
    }
}