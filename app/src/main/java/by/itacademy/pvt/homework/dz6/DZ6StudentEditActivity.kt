package by.itacademy.pvt.homework.dz6
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import by.itacademy.pvt.homework.R
import by.itacademy.pvt.homework.isURL
import kotlinx.android.synthetic.main.activity_dz6_student_edit.*
import java.lang.Exception

class DZ6StudentEditActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_edit)

        val studentSinglton = DZ6Singleton.instance

        if (intent.hasExtra(DZ6StudentDetailsActivity.ID)) {
            val id = intent.getLongExtra(DZ6StudentDetailsActivity.ID, 0)

            try {
                val student = studentSinglton.getStudent(id)
                nameEditTextDZ6.setText(student.name)
                ageEditTextDZ6.setText(student.age.toString())
                urlEditTextDZ6.setText(student.url)
            } catch (e: Exception) {
                Toast.makeText(this, "Студент не найден", Toast.LENGTH_SHORT)
                this.finish()
            }

            saveButtonDZ6.setOnClickListener {
                if (nameEditTextDZ6.text.isNotEmpty() && ageEditTextDZ6.text.isNotEmpty() && isURL(urlEditTextDZ6.text.toString())) {

                    val student = studentSinglton.getStudent(id)

                    student.name = nameEditTextDZ6.text.toString()
                    student.age = ageEditTextDZ6.text.toString().toInt()
                    student.url = urlEditTextDZ6.text.toString()

                    studentSinglton.editStudent(student)

                    this.finish()
                } else {
                    checkEditTexts()
                }
            }
        } else {

            saveButtonDZ6.setOnClickListener {
                if (nameEditTextDZ6.text.isNotEmpty() && ageEditTextDZ6.text.isNotEmpty() && isURL(urlEditTextDZ6.text.toString())) {

                    val student = Student(urlEditTextDZ6.text.toString(), nameEditTextDZ6.text.toString(), ageEditTextDZ6.text.toString().toInt())

                    studentSinglton.addStudent(student)

                    this.finish()
                } else {
                    checkEditTexts()
                }
            }
        }
    }

    private fun checkEditTexts() {
        if (!isURL(urlEditTextDZ6.text.toString())) Toast.makeText(this, "Please, enter correct image URL ", Toast.LENGTH_SHORT).show()

        if (nameEditTextDZ6.text.isEmpty()) Toast.makeText(this, "Please, enter correct name ", Toast.LENGTH_SHORT).show()

        if (ageEditTextDZ6.text.isEmpty()) Toast.makeText(this, "Please, enter correct age ", Toast.LENGTH_SHORT).show()
    }
}
