package com.example.sergionaudecitalan_2022_schools.dataSource.models
import com.google.gson.annotations.SerializedName

data class SchoolResultsItem(
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("dbn")
    val dbn: String
)