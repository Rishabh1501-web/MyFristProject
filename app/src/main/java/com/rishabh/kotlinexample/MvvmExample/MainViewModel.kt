package com.rishabh.kotlinexample.MvvmExample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val postRepository = PostRepository()
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    init {
        loadPosts()
    }

    private fun loadPosts() {
        postRepository.getPosts(object : PostRepository.PostCallback {
            override fun onSuccess(posts: List<Post>) {
                _posts.value = posts
            }
            override fun onError(t: Throwable) {
                Log.e(">>>>>", "" + t.message)
            }
        })
    }
}
