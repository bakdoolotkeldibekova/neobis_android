package com.example.tourguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import java.util.ArrayList

class HotelFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View?  {
        val rootView = inflater.inflate(R.layout.list_view, container, false)
        val locations: ArrayList<Location> = ArrayList<Location>()
        val hotelsNames = resources.getStringArray(R.array.hotels)
        val hotelsAddress = resources.getStringArray(R.array.hotelsAddress)
        val hotelPrices = resources.getStringArray(R.array.hotelPrice)
        val hotelsPhoneNumber = resources.getStringArray(R.array.hotelNumber)
        val images = intArrayOf(
            R.drawable.hotel_1,
            R.drawable.hotel_2,
            R.drawable.hotel_3,
            R.drawable.hotel_4,
            R.drawable.hotel_5,
            R.drawable.hotel_6,
            R.drawable.hotel_7,
            R.drawable.hotel_8,
            R.drawable.hotel_9,
            R.drawable.hotel_10
        )
        for (i in hotelsNames.indices) {
            locations.add(
                Location(
                    hotelsNames[i],
                    hotelsAddress[i],
                    "Price: " + "💰" + hotelPrices[i],
                    hotelsPhoneNumber[i],
                    images[i]
                )
            )
        }
        val locAdapter = LocationAdapter(activity, locations)
        val listView = rootView.findViewById<View>(R.id.list) as ListView
        listView.adapter = locAdapter
        return rootView
    }
}