package net.nyt.network

import net.nyt.models.MostPopular
import net.nyt.network.Urls.MOST_POPULAR
import retrofit2.http.GET
import retrofit2.http.Path


interface Apis {

    @GET(MOST_POPULAR)
    suspend fun getMostPopularNews(
        @Path("period") period: Int
    ): MostPopular

}