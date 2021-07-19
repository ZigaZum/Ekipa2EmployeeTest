package com.zumer.ekipa2employeestest.model

import com.google.gson.annotations.SerializedName

class OrganicResult {
    @SerializedName("position")
    var position: Int = 0
    @SerializedName("title")
    var title: String = ""
    @SerializedName("link")
    var link: String = ""
    @SerializedName("displayed_link")
    var displayed_link: String = ""
    @SerializedName("snippet")
    var snippet: String = ""
    @SerializedName("cached_page_link")
    var cached_page_link: String = ""
    @SerializedName("related_pages_link")
    var related_pages_link: String = ""

}