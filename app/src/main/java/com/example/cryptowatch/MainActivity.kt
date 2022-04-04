package com.example.cryptowatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "MainActivity"
private const val BTC_CURR_PRICES = "https://rest.coinapi.io/v1/exchangerate/BTC?apikey=34A08B21-0633-46B7-9761-CFA57367506B"
class MainActivity : AppCompatActivity() {

    private val btcs = mutableListOf<BTC>()
    private lateinit var rvBtc: RecyclerView
    // 1. Define a data model class as the data [done already: data class btc]
    // 2. Add the recyclerView to the layout
    // 3. Create a custom row layout XML file to visualize the item
    // 4. Create an Adapter and ViewHolder to render the item
    // 5. Bind the ada[ter to the data source to populate the RecyclerView
    // 6. Bind a layout manager to the RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvBtc = findViewById(R.id.rvBtc)

        val btcAdapter = BTCAdapter(this, btcs)
        rvBtc.adapter = btcAdapter
        rvBtc.layoutManager = LinearLayoutManager(this)

        val client = AsyncHttpClient()
        client.get(BTC_CURR_PRICES, object: JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "onSuccess: Json data $json")
                try {
                    val btcJsonArray = json.jsonObject.getJSONArray("rates")
                    btcs.addAll(BTC.fromJsonArray(btcJsonArray))
                    btcAdapter.notifyDataSetChanged()
                    Log.i(TAG, "Currency Rates $btcs")
                } catch (e: JSONException) {
                    Log.e(TAG, "Encountered exception $e")
                }
            }

        })
    }
}