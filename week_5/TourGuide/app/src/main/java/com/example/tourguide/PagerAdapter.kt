package com.example.tourguide

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ParksFragment()
            1 -> UniversityFragment()
            else -> HotelFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence{
        return when(position){
            0 -> "Parks"
            1 -> "Univer-s"
            else -> "Hotels"
        }
    }
}