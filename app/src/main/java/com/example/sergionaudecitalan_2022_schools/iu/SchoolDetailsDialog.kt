package com.example.sergionaudecitalan_2022_schools.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.sergionaudecitalan_2022_schools.dataSource.models.SchoolDetails.ResultsSchoolDetailsItem
import com.example.sergionaudecitalan_2022_schools.databinding.SchoolDetailsBinding
import com.example.sergionaudecitalan_2022_schools.viewmodel.SchoolsViewModel

class SchoolDetailsDialog(private val schoolDetailsItem: ResultsSchoolDetailsItem): DialogFragment() {

    private var _binding: SchoolDetailsBinding? = null
    private val binding: SchoolDetailsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SchoolDetailsBinding.inflate(inflater, container, false)

        binding.tvSchoolNumOfSat.text = schoolDetailsItem.numOfSatTestTakers
        binding.tvSchoolSatCritical.text = schoolDetailsItem.satCriticalReadingAvgScore
        binding.tvSchoolSatMath.text = schoolDetailsItem.satMathAvgScore
        binding.tvSchoolSatWriting.text = schoolDetailsItem.satWritingAvgScore
        binding.tvSchoolSchoolName.text = schoolDetailsItem.schoolName

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {

        const val TAG = "SchoolDetailsDialog"

        fun mewSchoolDetails(schoolDetailsItem: ResultsSchoolDetailsItem) =
            SchoolDetailsDialog(schoolDetailsItem).apply {
                setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            }

    }
}