package com.example.tourguide

class Location {
    var nameOfLocation: String? = null
    var address: String? = null
    private var phoneNumber = NOT_PROVIDED
    var description: String? = null
    private var wHours = NOT_PROVIDED
    private var price = NOT_PROVIDED
    var imageResourceId = 0

    constructor() {}
    constructor(
        nameOfLocation: String?,
        address: String?,
        description: String?,
        imageResourceId: Int,
        price: String?,
        wHours: String?
    ) {
        this.nameOfLocation = nameOfLocation
        this.address = address
        this.description = description
        this.imageResourceId = imageResourceId
        this.price = price
        this.wHours = wHours
    }

    constructor(
        nameOfLocation: String?,
        address: String?,
        price: String?,
        phoneNumber: String?,
        imageResourceId: Int
    ) {
        this.nameOfLocation = nameOfLocation
        this.address = address
        this.price = price
        this.phoneNumber = phoneNumber
        this.imageResourceId = imageResourceId
    }

    constructor(
        imageResourceId: Int,
        nameOfLocation: String?,
        address: String?,
        phoneNumber: String?,
        description: String?
    ) {
        this.imageResourceId = imageResourceId
        this.nameOfLocation = nameOfLocation
        this.address = address
        this.phoneNumber = phoneNumber
        this.description = description
    }

    fun getwHours(): String? {
        return wHours
    }

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun getPrice(): String? {
        return price
    }

    fun hasNumber(): Boolean {
        return getPhoneNumber() !== NOT_PROVIDED
    }

    fun haswHours(): Boolean {
        return getwHours() !== NOT_PROVIDED
    }

    fun hasPrice(): Boolean {
        return getPrice() !== NOT_PROVIDED
    }

    fun hasDescription(): Boolean {
        return description !== null
    }

    fun hasLocation(): Boolean {
        return address !== null
    }

    companion object {
        private val NOT_PROVIDED: String? = null
    }
}