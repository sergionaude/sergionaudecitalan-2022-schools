package com.example.sergionaudecitalan_2022_schools.Core

import com.example.sergionaudecitalan_2022_schools.dataSource.models.SchoolDetails.ResultsSchoolDetails
import com.example.sergionaudecitalan_2022_schools.dataSource.models.SchoolResults
import com.example.sergionaudecitalan_2022_schools.dataSource.models.SchoolResultsItem

sealed class StateResults{
    object LOADING: StateResults()
    class SUCCESS(val schoolResponse: SchoolResults): StateResults()
    class ERROR(val throwable: Throwable): StateResults()
}
