package com.example.tourguide

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class LocationAdapter(context: Activity?, locations: ArrayList<Location>?) :
    ArrayAdapter<Location?>(context!!, 0, locations!! as List<Location?>) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView =
                LayoutInflater.from(context).inflate(R.layout.location_list, parent, false)
        }
        val currentLocation = getItem(position)

        val nameOfLocation = listItemView!!.findViewById<View>(R.id.locations_name) as TextView
        nameOfLocation.text = currentLocation!!.nameOfLocation

        val locationIcon = listItemView.findViewById<View>(R.id.location_icon) as ImageView
        locationIcon.visibility = View.GONE
        val address = listItemView.findViewById<View>(R.id.location_address) as TextView
        address.text = currentLocation.address
        address.paint.isUnderlineText = true
        address.visibility = View.GONE
        address.setOnClickListener {
            val addressIntent = Intent(Intent.ACTION_VIEW)
            val title = "Open with app:"
            val chooser = Intent.createChooser(
                addressIntent.setData(Uri.parse("geo:0,0?q=" + currentLocation.address)),
                title
            )
            context.startActivity(chooser)
        }

        val number = listItemView.findViewById<View>(R.id.phone_number) as TextView
        number.visibility = View.GONE
        val phoneIcon = listItemView.findViewById<View>(R.id.phone_number_icon) as ImageView
        phoneIcon.visibility = View.GONE

        val imageLocation = listItemView.findViewById<View>(R.id.image) as ImageView
        Glide.with(context).load(currentLocation.imageResourceId).into(imageLocation)

        val wHours = listItemView.findViewById<View>(R.id.w_hours) as TextView
        wHours.visibility = View.GONE
        val wHoursIcon = listItemView.findViewById<View>(R.id.w_hours_icon) as ImageView
        wHoursIcon.visibility = View.GONE

        val priceText = listItemView.findViewById<View>(R.id.priceText) as TextView
        priceText.visibility = View.GONE
        val moneyIcon = listItemView.findViewById<View>(R.id.money_icon) as ImageView
        moneyIcon.visibility = View.GONE

        val shortText = listItemView.findViewById<View>(R.id.description_icon) as ImageView
        shortText.visibility = View.GONE

        val description = listItemView.findViewById<View>(R.id.description) as TextView
        description.visibility = View.GONE

        val show = listItemView.findViewById<View>(R.id.show_more_button) as ImageView
        show.visibility = View.VISIBLE

        val hide = listItemView.findViewById<View>(R.id.hide_button) as ImageView
        hide.visibility = View.GONE

        show.setOnClickListener {
            hide.visibility = View.VISIBLE
            show.visibility = View.GONE

            if (currentLocation.hasDescription()){
                shortText.visibility = View.VISIBLE
                description.text = currentLocation.description
                description.visibility = View.VISIBLE
            }
            if (currentLocation.hasLocation()){
                address.visibility = View.VISIBLE
                locationIcon.visibility = View.VISIBLE
            }
            if (currentLocation.hasNumber()) {
                phoneIcon.visibility = View.VISIBLE
                number.text = currentLocation.getPhoneNumber()
                number.paint.isUnderlineText = true
                number.visibility = View.VISIBLE
                number.setOnClickListener {
                    val numberIntent = Intent(Intent.ACTION_DIAL)
                    context.startActivity(numberIntent.setData(Uri.parse("tel:" + currentLocation.getPhoneNumber())))
                }
            }
            if (currentLocation.haswHours()) {
                wHours.text = currentLocation.getwHours()
                wHours.visibility = View.VISIBLE
                wHoursIcon.visibility = View.VISIBLE
            }
            if (currentLocation.hasPrice()) {
                priceText.text = currentLocation.getPrice()
                priceText.visibility = View.VISIBLE
                moneyIcon.visibility = View.VISIBLE
            }
        }
        hide.setOnClickListener {
            show.visibility = View.VISIBLE
            hide.visibility = View.GONE

            if (currentLocation.hasNumber()) {
                phoneIcon.visibility = View.GONE
                number.visibility = View.GONE
            }
            if (currentLocation.haswHours()) {
                wHours.visibility = View.GONE
                wHoursIcon.visibility = View.GONE
            }
            if (currentLocation.hasPrice()) {
                priceText.visibility = View.GONE
                moneyIcon.visibility = View.GONE
            }
            if (currentLocation.hasDescription()){
                shortText.visibility = View.GONE
                description.visibility = View.GONE
            }
            if (currentLocation.hasLocation()){
                address.visibility = View.GONE
                locationIcon.visibility = View.GONE
            }
        }
        return listItemView
    }
}