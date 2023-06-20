package com.example.moivesearch

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapters.RvAdapter
import com.example.model.MovieViewModel
import com.example.model.MovieViewModelFactory
import com.example.model.Search
import com.example.moivesearch.databinding.ActivityMainBinding
import com.example.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MovieViewModel
    private lateinit var rvAdapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MovieViewModelFactory(repository)
        viewModel =ViewModelProvider(this,viewModelFactory)[MovieViewModel::class.java]

        binding.btnSearch.setOnClickListener {
            binding.progressBar.visibility=View.VISIBLE
            binding.txtMovie.clearFocus()
            hideKeyboard()
            val movieName = binding.txtMovie.text.toString()
            if(movieName.isEmpty()){
                Toast.makeText(this, "Enter Movie Name", Toast.LENGTH_SHORT).show()
            }else{
                getSearchedMovie(movieName)
            }
        }
    }

    private fun getSearchedMovie(movieName: String) {
        viewModel.getMovieList(movieName,"1")

        viewModel.myResponseOfMovieList.observe(this) {
            if(it.Response=="False"){
                Toast.makeText(this,"Too many results",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,it.Search.toString(),Toast.LENGTH_LONG).show()

                val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
                binding.rvMovielist.layoutManager=layoutManager
                rvAdapter = RvAdapter(it.Search)
                binding.rvMovielist.adapter=rvAdapter

                binding.progressBar.visibility = View.INVISIBLE

                rvAdapter.setOnClickListener(object : RvAdapter.OnClickListener{
                    override fun onClick(position: Int, model: Search) {
                        val intent = Intent(this@MainActivity,MovieDetail::class.java)
                        intent.putExtra("MOVIE_ID",model.imdbID)
                        startActivity(intent)
                    }
                })
            }
        }
    }

    private fun hideKeyboard(){
        val imm=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.btnSearch.windowToken, 0)
    }
}