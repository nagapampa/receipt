package com.carie.receipt.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Receipt {

    private int id;
    private BigDecimal subtotal;
    private BigDecimal salesTaxTotal;
    private BigDecimal importTaxTotal;
    Set<ReceiptItem> receiptItems;

    public Receipt(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getSubtotal() {
        subtotal = new BigDecimal(0.00);
        for(ReceiptItem receiptItem: getReceiptItems()){
            subtotal = subtotal.add(receiptItem.getItem().getPrice().multiply(new BigDecimal(receiptItem.getQuantity())));
        }return subtotal;
    }

    public BigDecimal getSalesTaxTotal() {
        salesTaxTotal = new BigDecimal(0.00);
        for(ReceiptItem receiptItem: getReceiptItems()){
            salesTaxTotal = salesTaxTotal.add(receiptItem.getSalesTax());
        }
        return salesTaxTotal;
    }

    public BigDecimal getImportTaxTotal() {
        importTaxTotal = new BigDecimal(0.00);
        for(ReceiptItem receiptItem: getReceiptItems()){
            importTaxTotal = importTaxTotal.add(receiptItem.getImportTax());
        }
        return importTaxTotal;
    }

    public BigDecimal getGrandTaxTotal(){
        return getSalesTaxTotal().add(getImportTaxTotal());
    }

    public BigDecimal getGrandTotal() {
        return getSubtotal().add(getGrandTaxTotal());
    }

    public Set<ReceiptItem> getReceiptItems() {
        return receiptItems == null ? new HashSet<ReceiptItem>() : receiptItems;
    }

    public void setReceiptItems(Set<ReceiptItem> receiptItems) {
        this.receiptItems = receiptItems;
    }

    public String getReceiptItemsDisplay(){
        StringBuilder b = new StringBuilder();
        for(ReceiptItem item: getReceiptItems()){
            b.append(item.getQuantity() + " "
                    + item.getItem().getName() + ": " + item.getTotalWithTaxes()
                    + "\n");
        }

        return b.toString();
    }

}
