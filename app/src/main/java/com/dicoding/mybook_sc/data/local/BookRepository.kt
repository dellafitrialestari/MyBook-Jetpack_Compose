package com.dicoding.mybook_sc.data.local

import android.annotation.SuppressLint
import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class BookRepository(
    context: Context
) {
    private val books = mutableListOf<Book>()

    init {
        JSONObject.copyToInternalStorage(context)
        if (books.isEmpty()) {
            JSONObject.read(context).forEach {
                books.add(it)
            }
        }
    }

    fun getBooks(): Flow<List<Book>> = flowOf(books)

    fun getBookById(id: Long): Book {
        return books.first {
            it.id == id
        }
    }

    fun search(query: String): Flow<List<Book>> =
        flowOf(books.filter { it.name.contains(query, ignoreCase = true) })

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: BookRepository? = null

        fun getInstance(context: Context): BookRepository =
            instance ?: synchronized(this) {
                BookRepository(context).apply {
                    instance = this
                }
            }
    }
}