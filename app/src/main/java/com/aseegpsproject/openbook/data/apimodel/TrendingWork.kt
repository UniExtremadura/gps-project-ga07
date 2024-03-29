package  com.aseegpsproject.openbook.data.apimodel

import com.google.gson.annotations.SerializedName


data class TrendingWork(
    @SerializedName("key") var key: String,
    @SerializedName("title") var title: String? = null,
    @SerializedName("first_publish_year") var firstPublishYear: Int? = null,
    @SerializedName("cover_i") var coverI: Int? = null,
    @SerializedName("author_name") var authorName: ArrayList<String> = arrayListOf(),
    @SerializedName("author_key") var authorKey: ArrayList<String> = arrayListOf()
)