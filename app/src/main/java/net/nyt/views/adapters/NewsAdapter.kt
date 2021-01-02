package net.nyt.views.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import net.nyt.models.Result
import net.nyt.nytsample.databinding.NewsItemBinding
import net.nyt.utils.Constants.NEWS_RESPONSE_KEY
import net.nyt.views.activities.DetailsActivity

class NewsAdapter(
    private val list: ArrayList<Result>,
    private val activity: AppCompatActivity
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            navigateToDetailsActivity(list[position])
        }
    }

    private fun navigateToDetailsActivity(result: Result) {
        val intent = Intent(activity, DetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(NEWS_RESPONSE_KEY, result)
        intent.putExtras(bundle)
        activity.startActivity(intent)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
            binding.item = item
            binding.executePendingBindings()
        }

    }

}