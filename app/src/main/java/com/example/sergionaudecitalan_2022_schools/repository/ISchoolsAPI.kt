package com.example.sergionaudecitalan_2022_schools.repository

import com.example.sergionaudecitalan_2022_schools.dataSource.models.SchoolDetails.ResultsSchoolDetails
import com.example.sergionaudecitalan_2022_schools.dataSource.models.SchoolResults
import com.example.sergionaudecitalan_2022_schools.dataSource.models.SchoolResultsItem
import retrofit2.Response
import retrofit2.http.GET

interface ISchoolsAPI {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools() :  Response<SchoolResults>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSchoolsDetails() :  Response<ResultsSchoolDetails>
}

//https://data.cityofnewyork.us/resource/f9bf-2cp4.json