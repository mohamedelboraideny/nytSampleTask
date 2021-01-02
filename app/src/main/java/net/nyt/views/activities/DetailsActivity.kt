package net.nyt.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import net.nyt.models.Result
import net.nyt.nytsample.R
import net.nyt.nytsample.databinding.ActivityDetailsBinding
import net.nyt.utils.Constants.NEWS_RESPONSE_KEY

class DetailsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getActivityIntent()
    }

    private fun getActivityIntent() {
        val bundle = intent.extras
        if (bundle != null) {
            val newsResult = bundle.getSerializable(NEWS_RESPONSE_KEY) as Result
            mBinding.item = newsResult
        }
    }
}