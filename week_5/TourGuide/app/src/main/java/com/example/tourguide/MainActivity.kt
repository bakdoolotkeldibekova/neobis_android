package com.example.tourguide

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewPager: ViewPager =  findViewById(R.id.viewpager);

        var adapter = PagerAdapter(supportFragmentManager);
        viewPager.adapter = adapter;

        var tabLayout: TabLayout = findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#FF6200EE"))

    }
}