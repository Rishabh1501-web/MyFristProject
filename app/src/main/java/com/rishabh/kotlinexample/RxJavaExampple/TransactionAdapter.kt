package com.rishabh.kotlinexample.RxJavaExampple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rishabh.kotlinexample.R

// TransactionAdapter.kt
class TransactionAdapter(private val transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTransactionId: TextView = itemView.findViewById(R.id.tvTransactionId)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.tvTransactionId.text = "ID: ${transaction.transaction_id}"
        holder.tvAmount.text = "Amount: ${transaction.amount}"
        holder.tvStatus.text = "Status: ${transaction.status}"
    }

    override fun getItemCount() = transactions.size
}
