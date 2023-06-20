package com.example.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.model.Search
import com.example.moivesearch.R
import com.example.moivesearch.databinding.RawMovieBinding
import java.security.AccessController.getContext

class RvAdapter(var movieList : List<Search>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    private var onClickListener : OnClickListener? = null

    inner class ViewHolder(val binding: RawMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.ViewHolder {
        val binding = RawMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvAdapter.ViewHolder, position: Int) {
        with(holder){
            with(movieList[position]){
                binding.txvTitle.text = this.Title
                binding.txvrRleaseYear.text=this.Year
                Glide.with(binding.imgThumbnail.context)
                    .load(this.Poster)
                    .error(R.drawable.img_not_available)
                    .centerCrop()
                    .into(binding.imgThumbnail)
            }
        }
        holder.itemView.setOnClickListener {
            if(onClickListener!=null){
                onClickListener!!.onClick(position,movieList[position])
            }
        }

    }

    // A function to bind the onclickListener.
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // onClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, model: Search)
    }


    override fun getItemCount(): Int {
        return movieList.size
    }
}