package net.nyt.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import net.nyt.models.MostPopular
import net.nyt.network.Apis
import net.nyt.network.RetrofitBuilder

class NewsListVM : ViewModel() {

    private var client: Apis = RetrofitBuilder.instant

    fun getMostPopularNews(period: Int):
            LiveData<MostPopular> {
        return liveData(Dispatchers.IO) { emit(client.getMostPopularNews(period)) }
    }
}
