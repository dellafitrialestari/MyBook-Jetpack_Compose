package com.dicoding.mybook_sc.di

import android.content.Context
import com.dicoding.mybook_sc.data.local.BookRepository

object Injection {
    fun provideRepository(context: Context): BookRepository {
        return BookRepository.getInstance(context)
    }
}