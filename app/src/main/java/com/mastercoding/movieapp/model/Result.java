package com.mastercoding.movieapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;

public class Result {
    // this is the first class for pulling results from teh https:
    // if you recall from the JSON pull, there are 4 objects, 1 of them array.
    // since there are 4 objects we need to create four variables.

    // Below are teh list of GSON converters which will convert the JSON data into pretty data.
    @SerializedName("page") // this isn't required if the variable name below is the same from the JSON
    @Expose
    private Integer pageNumber;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("results")
    @Expose
    private List<Movie> results = null;

    public Result() {
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
