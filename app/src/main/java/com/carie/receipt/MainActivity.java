package com.carie.receipt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carie.receipt.adapter.ReceiptAdapter;
import com.carie.receipt.model.Item;
import com.carie.receipt.model.ItemType;
import com.carie.receipt.model.Receipt;
import com.carie.receipt.model.ReceiptItem;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Receipt[] mReceiptDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // populate receipt data
        populateReceiptData();

        // set up adapter to display list of receipts
        mRecyclerView = (RecyclerView) findViewById(R.id.list_receipts);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ReceiptAdapter(mReceiptDataSet);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void populateReceiptData(){
        // create inventory
        Item item1 = new Item(1, "16lb bag of Skittles", new BigDecimal(16.00), ItemType.CANDY, false);
        Item item2 = new Item(2, "Walkman", new BigDecimal(99.99), ItemType.TAXABLE, false);
        Item item3 = new Item(3, "Microwave Popcorn", new BigDecimal(00.99), ItemType.POPCORN, false);
        Item item4 = new Item(4, "Imported bag of Vanilla-Hazelnut Coffee", new BigDecimal(11.00), ItemType.COFFEE, true);
        Item item5 = new Item(5, "Imported Vespa", new BigDecimal(15001.25), ItemType.TAXABLE, true);
        Item item6 = new Item(6, "Imported crate of Almond Snickers", new BigDecimal(75.99), ItemType.CANDY, true);
        Item item7 = new Item(7, "Discman", new BigDecimal(55.00), ItemType.TAXABLE, false);
        Item item8 = new Item(8, "Imported Bottle of Wine", new BigDecimal(10.00), ItemType.TAXABLE, true);
        Item item9 = new Item(9, "300# bag of Fair-Trade Coffee", new BigDecimal(997.99), ItemType.COFFEE, false);

        // create first receipt
        Receipt receipt1 = new Receipt(1);
        ReceiptItem[] receipt1Items = {
                new ReceiptItem(receipt1, item1, 1),
                new ReceiptItem(receipt1, item2, 1),
                new ReceiptItem(receipt1, item3, 1)};
        receipt1.setReceiptItems(new HashSet<>(Arrays.asList(receipt1Items)));

        // create second receipt
        Receipt receipt2 = new Receipt(2);
        ReceiptItem[] receipt2Items = {
                new ReceiptItem(receipt2, item4, 1),
                new ReceiptItem(receipt2, item5, 1)};
        receipt2.setReceiptItems(new HashSet<>(Arrays.asList(receipt2Items)));

        // create third receipt
        Receipt receipt3 = new Receipt(3);
        ReceiptItem[] receipt3Items = {
                new ReceiptItem(receipt3, item6, 1),
                new ReceiptItem(receipt3, item7, 1),
                new ReceiptItem(receipt3, item8, 1),
                new ReceiptItem(receipt3, item9, 1)};
        receipt3.setReceiptItems(new HashSet<>(Arrays.asList(receipt3Items)));

        // add receipts to dataset
        mReceiptDataSet = new Receipt[]{receipt1, receipt2, receipt3};
    }
}
