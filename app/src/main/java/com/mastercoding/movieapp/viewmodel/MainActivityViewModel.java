package com.mastercoding.movieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mastercoding.movieapp.model.Movie;
import com.mastercoding.movieapp.model.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    // AndroidViewModel vs ViewModel
    // AVM = used when VM class needs to access Android-specific components
    // VM = no reference to Android application, suitable for non-Android-specific logic

    // because our MovieRepository uses an Application context we need to use Android base View model

    private MovieRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

    // Live Data
    public LiveData<List<Movie>> getAllMovies(){
        return repository.getMutableLivedata();
    }
}
