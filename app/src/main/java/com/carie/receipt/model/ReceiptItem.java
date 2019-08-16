package com.carie.receipt.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReceiptItem {

    private static BigDecimal SALES_TAX = new BigDecimal(0.10);
    private static BigDecimal IMPORT_TAX = new BigDecimal(0.05);
    private static RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private Receipt receipt;
    private Item item;
    private int quantity;
    private BigDecimal salesTax;

    public ReceiptItem(Receipt receipt, Item item, int quantity) {
        this.receipt = receipt;
        this.item = item;
        this.quantity = quantity;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSalesTax() {
        switch (getItem().getType()){
            case TAXABLE:
                salesTax = getItem().getPrice().multiply(SALES_TAX);
                break;
            default:
                salesTax = new BigDecimal(0.00);
        }
        return salesTax.multiply(new BigDecimal(getQuantity())).setScale(2, ROUNDING_MODE);
    }

    public BigDecimal getImportTax() {
        return getItem().isImported()
                ? getItem().getPrice().multiply(IMPORT_TAX).multiply(new BigDecimal(getQuantity())).setScale(2, ROUNDING_MODE)
                : new BigDecimal(0.00);
    }

    public BigDecimal getTotalWithTaxes(){
        return (getItem().getPrice().add(getImportTax()).add(getSalesTax()))
                .multiply(new BigDecimal(getQuantity()))
                .setScale(2, ROUNDING_MODE);
    }
}
