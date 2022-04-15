package com.example.sergionaudecitalan_2022_schools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sergionaudecitalan_2022_schools.Core.AdapterSchool
import com.example.sergionaudecitalan_2022_schools.Core.StateResults
import com.example.sergionaudecitalan_2022_schools.databinding.ActivityMainBinding
import com.example.sergionaudecitalan_2022_schools.iu.SchoolDetailsDialog
import com.example.sergionaudecitalan_2022_schools.viewmodel.SchoolsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel: SchoolsViewModel

    private val _binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val schoolAdapter = AdapterSchool { school ->
        viewmodel.getSchoolDetails(school.dbn)?.let {
            SchoolDetailsDialog.mewSchoolDetails(it)
                .show(supportFragmentManager, SchoolDetailsDialog.TAG)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)

        _binding.rvSchoolList.apply {
            adapter = schoolAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }

        viewmodel = ViewModelProvider(this)[SchoolsViewModel::class.java]

        viewmodel.state.observe(this) {
            when(it) {
                is StateResults.SUCCESS -> {
                    schoolAdapter.setSchool(it.schoolResponse)
                }
                is StateResults.LOADING -> {
                    //some loading state goes here
                }
                is StateResults.ERROR -> {
                    Log.d("ERROR", "Something Went wrong :(")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewmodel.getSchools()
    }
}