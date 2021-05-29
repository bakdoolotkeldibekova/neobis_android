package com.example.tourguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import java.util.ArrayList

class UniversityFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.list_view, container, false)
        val locations: ArrayList<Location> = ArrayList<Location>()
        val universitiesNames = resources.getStringArray(R.array.universities)
        val universitiesAddress = resources.getStringArray(R.array.universitiesAddress)
        val universitiesDescription = resources.getStringArray(R.array.universitiesDescription)
        val universitiesPhoneNumber = resources.getStringArray(R.array.universitiesPhoneNumber)
        val images = intArrayOf(
            R.drawable.university_1,
            R.drawable.university_2,
            R.drawable.university_3,
            R.drawable.university_4,
            R.drawable.university_5,
            R.drawable.university_6,
            R.drawable.university_7,
            R.drawable.university_8
            )
        for (i in universitiesNames.indices){
            locations.add(
                Location(
                    images[i],
                    universitiesNames[i],
                    universitiesAddress[i],
                    universitiesPhoneNumber[i],
                    universitiesDescription[i]
                )
            )
        }
        val locAdapter = LocationAdapter(activity, locations)
        val listView = rootView.findViewById<View>(R.id.list) as ListView
        listView.adapter = locAdapter
        return rootView
    }
}