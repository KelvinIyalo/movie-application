package com.kelviniyalo.moviesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.kelviniyalo.moviesapp.common.response_handler.RepositoryResponse
import com.kelviniyalo.moviesapp.common.response_handler.UiState
import com.kelviniyalo.moviesapp.data.model.MoviesResponse
import com.kelviniyalo.moviesapp.domain.repository.DatabaseRepository
import com.kelviniyalo.moviesapp.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository,private val databaseRepo:DatabaseRepository) : ViewModel() {

    fun searchMovies(tittle: String) = liveData {
        emit(UiState.Loading)
        when (val result = repository.searchMovies(tittle)) {

            is RepositoryResponse.Success -> {
                emit(UiState.Success(result.data.Search))
            }

            is RepositoryResponse.Error -> {
                emit(UiState.DisplayError(result.error))
            }

            is RepositoryResponse.ApiError -> {
                emit(UiState.DisplayError(result.apiError))
            }
        }
    }

    fun getMovieById(tittle: String) = liveData {
        emit(UiState.Loading)
        when (val result = repository.getMovieById(tittle)) {

            is RepositoryResponse.Success -> {
                emit(UiState.Success(result.data))
            }

            is RepositoryResponse.Error -> {
                emit(UiState.DisplayError(result.error))
            }

            is RepositoryResponse.ApiError -> {
                emit(UiState.DisplayError(result.apiError))
            }
        }
    }

    fun saveToDb(entity:MoviesResponse){
        viewModelScope.launch {
         databaseRepo.saveToDb(entity)
        }
    }
    fun getAllSavedFromDb(): LiveData<List<MoviesResponse>> {
        return databaseRepo.getTransactionsFromDb()
    }
}