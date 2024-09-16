package com.rishabh.kotlinexample.RxJavaExampple

import retrofit2.Response
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rishabh.kotlinexample.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException

class RxJavaActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable() // To manage RxJava subscriptions
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        callApi();
    }

    private fun callApi() {
        val requestBody = WinningHistoryRequest(
            header = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJuYW1lIjoiQXJ5YW4iLCJtb2JpbGUiOiI5MzA1MjA0MzU0IiwicGFzc3dvcmQiOiIxMjM0NTY3ODkiLCJnZW5kZXIiOiJtIiwidXNlcl90eXBlIjoiMCIsIkFQSV9USU1FIjoxNzI2MjEyNTU5fQ.1yMPmVQGdJKxxp2gt9k63ZNiJQBSKFR7L82Ye97ezNs",
            user_id = "1",
            type = "1"
        )

        val disposable =
            RetrofitClientRxJava.apiService.getWinningHistory(
                requestBody.header,
                requestBody.user_id,
                requestBody.type
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    // Handle successful response
                    response.transaction.forEach {
                        // Handle successful response
                        val transactions = response.transaction
                        adapter = TransactionAdapter(transactions)
                        recyclerView.adapter = adapter
                    }
                }, { throwable ->
                    // Handle errors
                    if (throwable is HttpException) {
                        Log.e(
                            "Error",
                            "API call failed: ${throwable.response()?.errorBody()?.string()}"
                        )
                    } else {
                        Log.e("Error", "API call failed: ${throwable.message}")
                    }
                })

        // Add the disposable to the CompositeDisposable
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear() // Clear the disposable to prevent memory leaks
    }
}