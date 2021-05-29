package com.example.tourguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import java.util.ArrayList

class ParksFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.list_view, container, false)
        val locations: ArrayList<Location> = ArrayList<Location>()
        val parkNames = resources.getStringArray(R.array.parks)
        val parkAddress = resources.getStringArray(R.array.parkAddress)
        val parkPrices = resources.getStringArray(R.array.parkPrice)
        val wHours = resources.getStringArray(R.array.parkWorkingHours)
        val parkDescriptions = resources.getStringArray(R.array.park_description)
        val images = intArrayOf(
            R.drawable.park_1,
            R.drawable.park_2,
            R.drawable.park_3,
            R.drawable.park_4,
            R.drawable.park_5,
            R.drawable.park_6,
            R.drawable.park_7,
            R.drawable.park_8
        )
        for (i in parkNames.indices) {
            locations.add(
                Location(
                    parkNames[i],
                    parkAddress[i],
                    parkDescriptions[i],
                    images[i],
                    "Price: " + "💰"  + parkPrices[i],
                    "Working hours: " + wHours[i]
                )
            )
        }
        val locAdapter = LocationAdapter(activity, locations)
        val listView = rootView.findViewById<View>(R.id.list) as ListView
        listView.adapter = locAdapter
        return rootView
    }
}