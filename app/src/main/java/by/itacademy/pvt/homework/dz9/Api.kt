package by.itacademy.pvt.myapplication.dz9

import by.itacademy.pvt.myapplication.dz9.entity.CarResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("t/fake-api/car-service.php")
    fun getCarsByCoord(
        @Query("p1Lat") latOne: Double,
        @Query("p1Lon") lonOne: Double,
        @Query("p2Lat") latTwo: Double,
        @Query("p2Lon") lonTwo: Double
    ): Call<CarResponse>
}