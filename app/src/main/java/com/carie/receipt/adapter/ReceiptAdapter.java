package com.carie.receipt.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.carie.receipt.R;
import com.carie.receipt.model.Receipt;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder> {
    private Receipt[] mDataset;

    public static class ReceiptViewHolder extends RecyclerView.ViewHolder {

        public TextView receiptIdTextView;
        public TextView receiptItemsTextView;
        public TextView receiptSubtotalTextView;
        public TextView receiptTaxTextView;
        public TextView receiptGrandTotalTextView;
        public ReceiptViewHolder(LinearLayout v) {
            super(v);
            receiptIdTextView = v.findViewById(R.id.text_receipt_id);
            receiptItemsTextView = v.findViewById(R.id.text_receipt_items);
            receiptSubtotalTextView = v.findViewById(R.id.text_receipt_subtotal);
            receiptTaxTextView = v.findViewById(R.id.text_receipt_tax);
            receiptGrandTotalTextView = v.findViewById(R.id.text_receipt_grand_total);
        }
    }

    public ReceiptAdapter(Receipt[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ReceiptAdapter.ReceiptViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_card_item, parent, false);

        ReceiptViewHolder vh = new ReceiptViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ReceiptViewHolder holder, int position) {

        holder.receiptIdTextView.setText(holder.itemView.getContext().getString(R.string.adapter_receipt)
                + mDataset[position].getId());
        holder.receiptItemsTextView.setText(mDataset[position].getReceiptItemsDisplay());
        holder.receiptSubtotalTextView.setText(holder.itemView.getContext().getString(R.string.adapter_subtotal)
                + convertToCurrency(mDataset[position].getSubtotal()));
        holder.receiptTaxTextView.setText(holder.itemView.getContext().getString(R.string.adapter_taxes)
                + convertToCurrency(mDataset[position].getGrandTaxTotal()));
        holder.receiptGrandTotalTextView.setText(holder.itemView.getContext().getString(R.string.adapter_grand_total)
                + convertToCurrency(mDataset[position].getGrandTotal()));
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.length;
    }

    private String convertToCurrency(BigDecimal number){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(number);
    }
}