package com.mastercoding.movieapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mastercoding.movieapp.databinding.ActivityMainBinding;
import com.mastercoding.movieapp.model.Movie;
import com.mastercoding.movieapp.view.MovieAdapter;
import com.mastercoding.movieapp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        getPopularMovies();

        swipeRefreshLayout = binding.swipeLayout;
        swipeRefreshLayout.setColorSchemeColors(R.color.black);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });

        // MVVM = Model, View, View-Model
        //when making an HTTP request like below:
        //https://www.example.com/widgets?colour=blue&sort=newest
        //the "?" is teh start of parameters, this is also to append query params to the end of a URL.
        //the "colour" is the key
        //the "blue" is the value
        //the "&" is the separator

        //HTTP STATUS Codes:
        // 200 = Success/OK

        // 3xx redirection
        // 301 = Permanent redirect
        // 302 = Temporary Redirect
        // 304 = not modified

        // 4xx Client Error
        // 401 Unauthorized Error
        // 403 Forbidden
        // 404 Not found
        // 405 Method not allowed

        // 5xx Server Error
        // 501 Not Implemented
        // 502 Bad Gateway
        // 503 Service Unavailable
        // 504 Gateway Timeout.

        // we will be using the below http:
        // https://api.themoviedb.org/3/movie/popular?api_key=d921119500022480dc8689002a2290fa
        // api.themoviedb.org/3 => base URL
        // movie/popular => Endpoint
        // ? => start of query

        // JSON Elements (Java Script Object Notation) it is an independent data exchange format:
        // 1-Array which in a JSON file. square bracket represents a JSON array
        // 2-Objects which in a JSON file, curly bracket represents a JSON object
        // 3-Key A JSON object contains a key that is just a string. Pairs of key/value make up a JSON object
        // 4-Value each key has a value that could be a string, integer, or double etc.



    }

    private void getPopularMovies() {

        viewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movies = (ArrayList<Movie>) moviesFromLiveData;
                displayMoviesInRecyclerView();
            }
        });

    }

    private void displayMoviesInRecyclerView() {
        recyclerView = binding.recyclerview;

        movieAdapter = new MovieAdapter(this, movies);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // notify an adapter associated with a RecyclerView
        // that the underlying dataset has changed
        movieAdapter.notifyDataSetChanged();


    }
}