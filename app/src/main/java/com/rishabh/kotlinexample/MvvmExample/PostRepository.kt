package com.rishabh.kotlinexample.MvvmExample

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository {
    private val apiService = RetrofitInstance.apiService

    fun getPosts(callback: PostCallback) {
        apiService.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onSuccess(it)
                    }
                }
                else {
                    callback.onError(Throwable("Response not successful"))
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    interface PostCallback {
        fun onSuccess(posts: List<Post>)
        fun onError(t: Throwable)
    }
}
