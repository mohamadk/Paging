package com.pagingsample.core.utils.location

fun latitudeOffset(latitude: Double, offsetMeter: Int): Double {
    val earth = 6378.137
    //radius of the earth in kilometer
    val pi = Math.PI
    val m = 1 / (2 * pi / 360 * earth) / 1000  //1 meter in degree

    return latitude + offsetMeter * m
}

fun longitudeOffset(latitude: Double, longitude: Double, offsetMeter: Int): Double {
    val earth = 6378.137
    //radius of the earth in kilometer
    val pi = Math.PI

    val m = 1 / (2 * pi / 360 * earth) / 1000  //1 meter in degree

    return longitude + offsetMeter * m / Math.cos(latitude * (pi / 180))
}