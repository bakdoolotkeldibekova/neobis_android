package com.example.googlebooksapp.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooksapp.R
import com.example.googlebooksapp.adapter.BookAdapter
import com.example.googlebooksapp.model.Book
import com.example.googlebooksapp.model.Result
import com.example.googlebooksapp.network.RetrofitClientInstance
import com.example.googlebooksapp.network.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BookActivity : AppCompatActivity() {

    var bookList: List<Book>? = null
    lateinit var bookAdapter: BookAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var webService: WebService
    lateinit var progressBar: ProgressBar
    lateinit var noInternet: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar = findViewById(R.id.progressBar)
        noInternet = findViewById(R.id.no_internet)

        if (checkConnection()){
            webService = RetrofitClientInstance.getRetrofitInstance().create(WebService::class.java)

            var searchWord: String = intent.getStringExtra("Search")!!.replace(" ", "+")
            val call: Call<Result> = webService.getBooks(searchWord)
            call.enqueue(object : Callback<Result> {
                override fun onResponse(call: Call<Result>, response: Response<Result>) {

                    bookList = response.body()!!.bookList
                    bookAdapter = BookAdapter(this@BookActivity, bookList!!)
                    bookAdapter.notifyDataSetChanged()
                    recyclerView.adapter = bookAdapter
                    progressBar.visibility = View.GONE

                }
                override fun onFailure(call: Call<Result>?, t: Throwable?) {
                    Toast.makeText(this@BookActivity, "Mistake Occurred!", Toast.LENGTH_SHORT).show();
                }
            })
        }

    }

    fun checkConnection() : Boolean{
        val connectivityManager: ConnectivityManager? =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo = connectivityManager!!.getActiveNetworkInfo()
        return if (networkInfo != null && networkInfo.isConnected()) true
        else {
            progressBar.visibility = View.GONE
            noInternet.text = "No internet connection"
            false
        }
    }
}