package com.androiddevs.mvvmnewsapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.databinding.ItemArticlePreviewBinding
import com.androiddevs.mvvmnewsapp.models.Article
import com.bumptech.glide.Glide
import java.lang.Exception

/*import kotlinx.android.synthetic.main.item_article_preview.view.**/

class NewsAdapter(): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    val TAG = "NewsAdapter"

    private lateinit var mOnArticleClickListner: OnArticleClickListner

    fun setOnArticleClickListner(onArticleClickListner: OnArticleClickListner){
        mOnArticleClickListner = onArticleClickListner
    }

    //inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    inner class ArticleViewHolder(val binding: ItemArticlePreviewBinding):
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        /*return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )*/

        val binding = ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
         val binding = holder.binding
                Glide.with(this)
                    .load(article.urlToImage)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.ivArticleImage)
            binding.tvSource.text = article.source?.name
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            //onItemClickListner?.let{ it(article)}
            holder.itemView.setOnClickListener {
                mOnArticleClickListner.OnArticleClick(article)
            }

        }
    }


    interface OnArticleClickListner {
        fun OnArticleClick(article: Article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

   /*
    private var onItemClickListner: ((Article) -> Unit)?=null

    fun setOnItemClickListner(listner: (Article) -> Unit){
        onItemClickListner = listner
    }*/


}