package com.example.cryptowatch

import org.json.JSONArray

data class BTC(

    val asset_id_quote: String,
    val rate: Int,
    val time: String,
) {
    companion object {
        fun fromJsonArray(btcJsonArray: JSONArray): MutableList<BTC> {
            val btc = mutableListOf<BTC>()
            for (i in 0 until btcJsonArray.length()) {
                val btcJson = btcJsonArray.getJSONObject(i)
                btc.add(
                    BTC(
                        btcJson.getString("asset_id_quote"),
                        btcJson.getInt("rate"),
                        btcJson.getString("time"),
                    )
                )
            }
            return btc

        }
    }
}