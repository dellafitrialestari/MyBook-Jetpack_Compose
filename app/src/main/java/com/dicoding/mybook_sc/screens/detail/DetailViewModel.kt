package com.dicoding.mybook_sc.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mybook_sc.data.local.Book
import com.dicoding.mybook_sc.data.local.BookRepository
import com.dicoding.mybook_sc.screens.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: BookRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Book>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Book>> get() = _uiState

    fun getBookById(id: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getBookById(id))
        }
    }
}