package com.example.sergionaudecitalan_2022_schools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sergionaudecitalan_2022_schools.Core.Retrofit
import com.example.sergionaudecitalan_2022_schools.Core.StateResults
import com.example.sergionaudecitalan_2022_schools.dataSource.models.SchoolDetails.ResultsSchoolDetails
import com.example.sergionaudecitalan_2022_schools.dataSource.models.SchoolDetails.ResultsSchoolDetailsItem
import com.example.sergionaudecitalan_2022_schools.dataSource.models.SchoolResultsItem
import com.example.sergionaudecitalan_2022_schools.repository.ISchoolsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SchoolsViewModel: ViewModel() {

    private val _state: MutableLiveData<StateResults> = MutableLiveData(StateResults.LOADING)
    val state: LiveData<StateResults> get() = _state

    lateinit var schoolDetails: ResultsSchoolDetails

    fun getSchools() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val schoolsResponse = async {  Retrofit.retrofitService.getSchools() }.await()
                val schoolResultsResponse = async { Retrofit.retrofitService.getSchoolsDetails() }.await()

                if (schoolsResponse.isSuccessful && schoolResultsResponse.isSuccessful) {
                    schoolsResponse.body()?.let { school ->
                        schoolResultsResponse.body()?.let { details ->
                            schoolDetails = details
                            _state.postValue(StateResults.SUCCESS(school))
                        } ?: throw Exception("Empty Body")
                    } ?: throw Exception("Body is empty")
                } else {
                    throw Exception("Not Success!!")
                }
            } catch (e: Exception) {
                _state.postValue(StateResults.ERROR(e))
            }
        }
    }

    fun getSchoolDetails(dbn: String): ResultsSchoolDetailsItem? =
        schoolDetails.filter {
            it.dbn == dbn
        }.firstOrNull()
}

