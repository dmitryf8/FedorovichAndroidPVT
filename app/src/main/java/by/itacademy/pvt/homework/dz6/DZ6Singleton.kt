package by.itacademy.pvt.homework.dz6

class DZ6Singleton private constructor() {

    private var studentList: ArrayList<Student>

    companion object {
        val instance = DZ6Singleton()
    }

    init {
        studentList = createStudentList()
    }

    fun getStudentsList(): ArrayList<Student> {
        if (studentList.isEmpty()) {
            studentList = createStudentList()
        }
        return studentList
    }

    fun createStudentList(): ArrayList<Student> {
        var aList = arrayListOf(Student("https://sp.freehat.cc/upload/iblock/05a/stan.png", "Стэн Марш", 10),
            Student("https://sp.freehat.cc/upload/iblock/d61/kyle.png", "Кайл Брофловски", 10),
            Student("https://sp.freehat.cc/upload/iblock/cc0/eric_.png", "Эрик Картман", 10),
            Student("https://sp.freehat.cc/upload/iblock/039/kenny.png", "Кенни Маккормик", 10),
            Student("https://sp.freehat.cc/upload/iblock/f13/butters.png", "Баттерс Стотч", 10),
            Student("https://sp.freehat.cc/upload/iblock/8b0/craig.png", "Крэйг Такер", 10),
            Student("https://sp.freehat.cc/upload/iblock/62e/wendy.png", "Венди Тестабургер", 10),
            Student("https://sp.freehat.cc/upload/iblock/c73/ike.png", "Айк Брофловски", 6),
            Student("https://sp.freehat.cc/upload/iblock/48d/jimmy.png", "Джимми Волмер", 10),
            Student("https://sp.freehat.cc/upload/iblock/e1a/hat.png", "Мистер Шляпа", 20))

        return aList
    }

    fun addStudent(student: Student) {
        studentList.add(student)
    }

    fun getStudent(id: Long): Student {
        lateinit var tmpStudent: Student
        for (student in studentList) {
            if (student.id == id) {
                tmpStudent = student
                break
            } else {
                tmpStudent = Student("https://www.svgrepo.com/show/200098/close-error.svg", "Not find Student by ID", 0)
            }
        }
        return tmpStudent
    }

    fun editStudent(student: Student, url: String, name: String, age: Int) {
        student.url = url
        student.name = name
        student.age = age
    }

    fun deleteStudent(student: Student) {
        for (stdnt in studentList) {
            if (stdnt.id == student.id) {
                studentList.remove(stdnt)
                break
            }
        }
    }
}