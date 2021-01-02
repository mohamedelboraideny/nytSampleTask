package net.nyt.models


import com.google.gson.annotations.SerializedName

data class MostPopular(
    @SerializedName("status")
    val status: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: ArrayList<Result>
) {

}