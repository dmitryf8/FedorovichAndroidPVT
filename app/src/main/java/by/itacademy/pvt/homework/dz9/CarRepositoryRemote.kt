package by.itacademy.pvt.myapplication.dz9

import by.itacademy.pvt.myapplication.dz9.entity.CarResponse
import by.itacademy.pvt.myapplication.dz9.entity.CoordinateParameters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarRepositoryRemote(private val api: Api) : CarRepository {

    override fun getCarByCoord(params: CoordinateParameters, listener: CarRepositoryResultDZ9) {

        api.getCarsByCoord(
            params.coord1.latitude,
            params.coord1.longitude,
            params.coord2.latitude,
            params.coord2.longitude
        )
            .enqueue(object : Callback<CarResponse> {
                override fun onResponse(call: Call<CarResponse>, response: Response<CarResponse>) {
                    if (response.body() != null && response.body()?.poiList != null)
                        listener.onSucces(response.body()!!.poiList)
                    else
                        listener.onError(Throwable("ERROR"))
                }

                override fun onFailure(call: Call<CarResponse>, t: Throwable) {
                    listener.onError(t)
                }
            })
    }
}