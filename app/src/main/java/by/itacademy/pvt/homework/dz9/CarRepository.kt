package by.itacademy.pvt.myapplication.dz9

import by.itacademy.pvt.myapplication.dz9.entity.CoordinateParameters
import by.itacademy.pvt.myapplication.dz9.entity.Poi

interface CarRepository {
    fun getCarByCoord(params: CoordinateParameters, listener: CarRepositoryResultDZ9)
}

interface CarRepositoryResultDZ9 {
    fun onSucces(list: List<Poi>)
    fun onError(throwable: Throwable)
}