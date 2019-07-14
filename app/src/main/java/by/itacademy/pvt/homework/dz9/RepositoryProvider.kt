package by.itacademy.pvt.myapplication.dz9

fun provideCarRepository(): CarRepository {
    return CarRepositoryRemote(
        NetProvider.provideApi(
            NetProvider.provideRetrofit(
                "http://kiparo.ru/",
                NetProvider.provideOkHttp(),
                NetProvider.provideGson())
        )
    )
}