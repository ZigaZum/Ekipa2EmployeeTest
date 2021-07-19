package com.zumer.ekipa2employeestest.model

import com.google.gson.annotations.SerializedName

class SearchResponse {
    @SerializedName("organic_results")
    var organicResults = emptyList<OrganicResult>()
}