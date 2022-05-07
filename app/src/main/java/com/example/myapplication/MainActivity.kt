package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    fun setup(){
        val textView = findViewById<TextView>(R.id.mytext)

        val cache = DiskBasedCache(cacheDir, 1024 * 1024)

        val network = BasicNetwork(HurlStack())

        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }

        val url = "https://jsonplaceholder.typicode.com/users"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->

                mytext.setText(response)

            },
            { error ->
                // Handle error
                textView.text = "ERROR: %s".format(error.toString())
            })

        requestQueue.add(stringRequest)
    }


}