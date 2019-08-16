package com.carie.receipt;

import com.carie.receipt.model.Item;
import com.carie.receipt.model.ItemType;

import com.carie.receipt.model.Receipt;
import com.carie.receipt.model.ReceiptItem;

import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;

public class ReceiptUnitTest {

    private  static Item item1 = new Item(1, "16lb bag of Skittles", new BigDecimal(16.00), ItemType.CANDY, false);
    private  static Item item2 = new Item(2, "Walkman", new BigDecimal(99.99), ItemType.TAXABLE, false);
    private  static Item item3 = new Item(3, "Microwave Popcorn", new BigDecimal(00.99), ItemType.POPCORN, false);
    private  static Item item4 = new Item(4, "Imported bag of Vanilla-Hazelnut Coffee", new BigDecimal(11.00), ItemType.COFFEE, true);
    private  static Item item5 = new Item(5, "Imported Vespa", new BigDecimal(15001.25), ItemType.TAXABLE, true);
    private  static Item item6 = new Item(6, "Imported crate of Almond Snickers", new BigDecimal(75.99), ItemType.CANDY, true);
    private  static Item item7 = new Item(7, "Discman", new BigDecimal(55.00), ItemType.TAXABLE, false);
    private  static Item item8 = new Item(8, "Imported Bottle of Wine", new BigDecimal(10.00), ItemType.TAXABLE, true);
    private  static Item item9 = new Item(9, "300# bag of Fair-Trade Coffee", new BigDecimal(997.99), ItemType.COFFEE, false);

    private static BigDecimal receipt1Tax = new BigDecimal(10.00).setScale(2, RoundingMode.HALF_UP);
    private static BigDecimal receipt1GrandTotal = new BigDecimal(126.98).setScale(2, RoundingMode.HALF_UP);

    private static BigDecimal receipt2Tax = new BigDecimal(2250.8).setScale(2, RoundingMode.HALF_UP);
    private static BigDecimal receipt2GrandTotal = new BigDecimal(17263.05).setScale(2, RoundingMode.HALF_UP);

    private static BigDecimal receipt3Tax = new BigDecimal(10.8).setScale(2, RoundingMode.HALF_UP);
    private static BigDecimal receipt3GrandTotal = new BigDecimal(1149.78).setScale(2, RoundingMode.HALF_UP);

    @Test
    public void receipt1Validator_CorrectTotals_ReturnsTrue() {

        Receipt receipt1 = new Receipt(1);
        ReceiptItem[] receipt1Items = {
                new ReceiptItem(receipt1, item1, 1),
                new ReceiptItem(receipt1, item2, 1),
                new ReceiptItem(receipt1, item3, 1)};
        receipt1.setReceiptItems(new HashSet<>(Arrays.asList(receipt1Items)));

        assertEquals(receipt1.getReceiptItems().size(), 3);

        assertEquals(receipt1.getGrandTaxTotal(), receipt1Tax);
        assertEquals(receipt1.getGrandTotal(), receipt1GrandTotal);
    }

    @Test
    public void receipt2Validator_CorrectTotals_ReturnsTrue() {

        Receipt receipt2 = new Receipt(1);
        ReceiptItem[] receipt2Items = {
                new ReceiptItem(receipt2, item4, 1),
                new ReceiptItem(receipt2, item5, 1)};
        receipt2.setReceiptItems(new HashSet<>(Arrays.asList(receipt2Items)));

        assertEquals(receipt2.getReceiptItems().size(), 2);

        assertEquals(receipt2.getGrandTaxTotal(), receipt2Tax); //TODO glad to have unit test! however, is there a chance your calculation for sales tax on the vespa is incorrect?
        assertEquals(receipt2.getGrandTotal(), receipt2GrandTotal);
    }

    @Test
    public void receipt3Validator_CorrectTotals_ReturnsTrue() {

        Receipt receipt3 = new Receipt(1);
        ReceiptItem[] receipt3Items = {
                new ReceiptItem(receipt3, item6, 1),
                new ReceiptItem(receipt3, item7, 1),
                new ReceiptItem(receipt3, item8, 1),
                new ReceiptItem(receipt3, item9, 1)};
        receipt3.setReceiptItems(new HashSet<>(Arrays.asList(receipt3Items)));

        assertEquals(receipt3.getReceiptItems().size(), 4);

        assertEquals(receipt3.getGrandTaxTotal(), receipt3Tax);
        assertEquals(receipt3.getGrandTotal(), receipt3GrandTotal);
    }

}
