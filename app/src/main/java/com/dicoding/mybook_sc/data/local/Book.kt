package com.dicoding.mybook_sc.data.local

data class Book(
    val id: Long,
    val name: String,
    val penulis: String,
    val descDetail: String,
    val descHalaman: Int,
    val descPenerbit: String,
    val descISBN: String,
    val tanggal: String,
    val photo: String
)