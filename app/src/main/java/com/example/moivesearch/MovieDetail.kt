package com.example.moivesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.model.MovieViewModel
import com.example.model.MovieDetailModel
import com.example.model.MovieViewModelFactory
import com.example.moivesearch.databinding.ActivityMovieDetailBinding
import com.example.repository.Repository

class MovieDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel : MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getStringExtra("MOVIE_ID")

        val repository = Repository()
        val viewModelFactory = MovieViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)[MovieViewModel::class.java]

        if (movieId != null) {
            getMovieDetail(movieId)
        }


    }

    private fun getMovieDetail(movieId : String) {
        viewModel.getMovieDetail(movieId)

        viewModel.myResponseOfMovieDetailView.observe(this){
            Glide.with(binding.imgPoster.context)
                .load(it.Poster)
                .error(R.drawable.img_not_available)
                .centerCrop()
                .into(binding.imgPoster)
            binding.txvTitle.text=it.Title
            binding.txvGenre.text=it.Genre
            binding.txvPlot.text=it.Plot
            binding.txvYear.text=it.Year
            binding.txvRated.text=it.Rated
            binding.txvReleased.text=it.Released
            binding.txvRunTime.text=it.Runtime
            binding.txvDirector.text=it.Director
            binding.txvWriter.text=it.Writer
            binding.txvActors.text=it.Actors
            binding.txvLanguage.text=it.Language
            binding.txvCountry.text=it.Country
            binding.txvAwards.text=it.Awards
            binding.txvMetascore.text=it.Metascore
            binding.txvRating.text=it.Ratings[0].Value
            binding.txvVotes.text=it.imdbVotes



        }
    }
}