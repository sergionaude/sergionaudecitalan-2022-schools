package com.example.sergionaudecitalan_2022_schools.Core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sergionaudecitalan_2022_schools.R
import com.example.sergionaudecitalan_2022_schools.dataSource.models.SchoolResultsItem

class AdapterSchool(
    private var schoolList: List<SchoolResultsItem> = listOf(),
    private val schoolClick: (schoolItem: SchoolResultsItem) -> Unit
): RecyclerView.Adapter<ItemViewHolder>() {

    fun setSchool(newSchoolResultsItem: List<SchoolResultsItem>) {
        schoolList = newSchoolResultsItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemschool, parent, false)
        return ItemViewHolder(view, schoolClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(schoolList[position])


    override fun getItemCount(): Int = schoolList.size
}

class ItemViewHolder(private val view: View, private val schoolClick: (schoolItem: SchoolResultsItem) -> Unit) : RecyclerView.ViewHolder(view){

    fun bind(schoolResultsItem: SchoolResultsItem) {
        val name : TextView = view.findViewById(R.id.txtV_nameSchool)
        val dbn : TextView = view.findViewById(R.id.txtV_dbn)
        val overview : TextView = view.findViewById(R.id.txtV_overview)

        name.text = schoolResultsItem.schoolName
        dbn.text = schoolResultsItem.dbn
        //overview.text = schoolResultsItem.overviewParagraph

        view.setOnClickListener { schoolClick(schoolResultsItem) }
    }
}