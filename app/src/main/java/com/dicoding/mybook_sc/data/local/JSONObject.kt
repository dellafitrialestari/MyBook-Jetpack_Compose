package com.dicoding.mybook_sc.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


object JSONObject {
    fun read(context: Context): List<Book> {
        val fileName = "book.json"
        val file = File(context.filesDir, fileName)
        var jsonString = ""

        try {
            jsonString = file.readText()
        } catch (ioException: IOException) {
            Log.e("errorJson", ioException.message.toString())
        }

        val listBookType = object : TypeToken<List<Book>>() {}.type
        return Gson().fromJson(jsonString, listBookType)
    }

    fun copyToInternalStorage(context: Context) {
        val fileName = "book.json"
        val jsonFile = File(context.filesDir, fileName)

        if (!jsonFile.exists()) {
            try {
                val inputStream: InputStream = context.assets.open(fileName)
                val outputStream: OutputStream = jsonFile.outputStream()
                inputStream.copyTo(outputStream)
                inputStream.close()
                outputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    @SuppressLint("DiscouragedApi")
    fun getResourceIdByName(context: Context, resourceName: String): Int {
        return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }
}