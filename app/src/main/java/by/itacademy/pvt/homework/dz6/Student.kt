package by.itacademy.pvt.homework.dz6

class Student(url: String, name: String, age: Int) {
    val id = System.currentTimeMillis() + (Math.random() * 1000).toLong()
    var url = url
    var name = name
    var age = age
}