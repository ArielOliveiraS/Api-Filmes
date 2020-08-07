package com.example.tmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmdb.api.remote.RetrofitService
import com.example.tmdb.model.Movie
import com.example.tmdb.model.MovieResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val movieListResult: MutableLiveData<MovieResult> = MutableLiveData()

    private val loading: MutableLiveData<Boolean> = MutableLiveData()

    //   private val movieList: MutableLiveData<MovieResult> = MutableLiveData()

    val movieList1: MutableLiveData<List<Movie>> = MutableLiveData()

    val loadingResult: LiveData<Boolean> = loading

    private val error: MutableLiveData<String> = MutableLiveData()

    val errorResult: LiveData<String> = error

    fun getAllMovies(apiKey: String) {
        disposable.add(
            RetrofitService.service.getMovieDetails(apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.value = true }
                .doAfterTerminate { loading.value = false }
                .subscribe({
                    movieListResult.value = it
                }, { e ->
                    error.value = e.message
                })

        )
    }
}