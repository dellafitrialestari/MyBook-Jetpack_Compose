package com.dicoding.mybook_sc.screens.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mybook_sc.data.local.Book
import com.dicoding.mybook_sc.data.local.BookRepository
import com.dicoding.mybook_sc.screens.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class HomeViewModel(
    private val repository: BookRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Book>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Book>>> get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        viewModelScope.launch {
            _query.value = newQuery
            repository.search(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { books ->
                    _uiState.value = UiState.Success(books)
                    Log.d("jsonData", _uiState.value.toString())
                }
        }
    }

    fun getBooks() {
        viewModelScope.launch {
            repository.getBooks()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { books ->
                    _uiState.value = UiState.Success(books)
                }
        }
    }
}