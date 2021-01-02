package net.nyt.views.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import net.nyt.models.Result
import net.nyt.nytsample.R
import net.nyt.nytsample.databinding.ActivityNewsListBinding
import net.nyt.utils.CustomPB
import net.nyt.viewModels.NewsListVM
import net.nyt.views.adapters.NewsAdapter

class NewsListActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityNewsListBinding
    private lateinit var mViewModel: NewsListVM
    private lateinit var customPB: CustomPB
    private var results: ArrayList<Result> = ArrayList()
    private lateinit var adapter: NewsAdapter
    private val daily = 1
    private val weekly = 7
    private val monthly = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_list)
        mViewModel = ViewModelProvider(this).get(NewsListVM::class.java)
        customPB = CustomPB(this)

        getMostPopularNews(daily)
    }

    private fun getMostPopularNews(period: Int) {
        customPB.show()
        mViewModel.getMostPopularNews(period).observe(this, {
            customPB.dismiss()
            if (results.size > 0) {
                results.clear()
            }
            results.addAll(it.results)
            setAdapter()
        })

    }

    private fun setAdapter() {
        if (this::adapter.isInitialized)
            adapter.notifyDataSetChanged()
        else {
            adapter = NewsAdapter(results, this)
            mBinding.mostPopularRv.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.daily -> {
                getMostPopularNews(daily)
                true
            }
            R.id.weekly -> {
                getMostPopularNews(weekly)
                true
            }
            R.id.monthly -> {
                getMostPopularNews(monthly)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}