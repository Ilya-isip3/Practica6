package com.example.myprapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myprapp.repository.PostRepository
import com.example.myprapp.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    // упрощённый вариант
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
}