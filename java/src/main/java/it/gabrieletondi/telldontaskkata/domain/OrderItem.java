package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.OrderCreationUseCase;
import it.gabrieletondi.telldontaskkata.useCase.SellItemRequest;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal taxedAmount;
    private BigDecimal tax;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.tax = getTax(product, quantity);
        

    }


    private BigDecimal getTax(Product product, int quantity) {
        return product.getUnitaryTax().multiply(BigDecimal.valueOf(quantity));
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTaxedAmount() {
        return taxedAmount;
    }

    public void setTaxedAmount(BigDecimal taxedAmount) {
        this.taxedAmount = taxedAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTaxedAmount(SellItemRequest itemRequest, BigDecimal unitaryTaxedAmount, OrderCreationUseCase orderCreationUseCase) {
        return getTaxAmount(itemRequest, unitaryTaxedAmount).setScale(2, HALF_UP);
    }

    public BigDecimal getTaxAmount(SellItemRequest itemRequest, BigDecimal unitaryTax) {
        return unitaryTax.multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
    }
}
