package by.itacademy.pvt.homework.dz8

class DZ8Singleton private constructor() {

    private var studentList: ArrayList<Student>

    companion object {
        val instance = DZ8Singleton()

        public const val ID_KEY = "ID_KEY"
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
        var aList = arrayListOf(Student(generateID(), "https://sp.freehat.cc/upload/iblock/05a/stan.png", "Стэн Марш", 10),
            Student(generateID(), "https://sp.freehat.cc/upload/iblock/d61/kyle.png", "Кайл Брофловски", 10),
            Student(generateID(), "https://sp.freehat.cc/upload/iblock/cc0/eric_.png", "Эрик Картман", 10),
            Student(generateID(), "https://sp.freehat.cc/upload/iblock/039/kenny.png", "Кенни Маккормик", 10),
            Student(generateID(), "https://sp.freehat.cc/upload/iblock/f13/butters.png", "Баттерс Стотч", 10),
            Student(generateID(), "https://sp.freehat.cc/upload/iblock/8b0/craig.png", "Крэйг Такер", 10),
            Student(generateID(), "https://sp.freehat.cc/upload/iblock/62e/wendy.png", "Венди Тестабургер", 10),
            Student(generateID(), "https://sp.freehat.cc/upload/iblock/c73/ike.png", "Айк Брофловски", 6),
            Student(generateID(), "https://sp.freehat.cc/upload/iblock/48d/jimmy.png", "Джимми Волмер", 10),
            Student(generateID(), "https://sp.freehat.cc/upload/iblock/e1a/hat.png", "Мистер Шляпа", 20))

        return aList
    }

    fun generateID(): Long {
        return System.currentTimeMillis() + (Math.random() * 1000).toLong()
    }

    fun addStudent(student: Student) {
        studentList.add(student)
    }

    fun getStudent(id: Long): Student {
        lateinit var tmpStudent: Student
        for (student in studentList) {
            if (student.id == id) {
                return student
            }
        }
        return tmpStudent
    }

    fun editStudent(student: Student) {
        deleteStudent(student)
        studentList.add(student)
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