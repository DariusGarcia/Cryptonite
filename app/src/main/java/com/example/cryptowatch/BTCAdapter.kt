package com.example.cryptowatch

import android.view.View
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BTCAdapter(private val context: Context, private val btcs: MutableList<BTC>)
    : RecyclerView.Adapter<BTCAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvER = itemView.findViewById<TextView>(R.id.tvER)
        private val tvTime = itemView.findViewById<TextView>(R.id.tvTIme)
        private val tvTicker = itemView.findViewById<TextView>(R.id.tvTicker)

        fun bind(btc: BTC) {
            tvER.text = btc.rate.toString()
            tvTicker.text = btc.asset_id_quote
            tvTime.text = btc.time
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_currency, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val btc = btcs[position]
        holder.bind(btc)
    }

    override fun getItemCount() = btcs.size
    }



