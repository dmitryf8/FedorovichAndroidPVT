package by.itacademy.pvt.homework.dz6
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import by.itacademy.pvt.homework.ImageLoader
import by.itacademy.pvt.homework.R
import kotlinx.android.synthetic.main.activity_dz6_student_edit.*

class DZ6StudentEditActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_student_edit)

        val studentSinglton = DZ6Singleton.instance
        val id = intent.getLongExtra("ID", 0)

        if (id > 2) {
            val student = studentSinglton.getStudent(id)
            nameEditTextDZ6.setText(student.name)
            ageEditTextDZ6.setText(student.age.toString())
            urlEditTextDZ6.setText(student.url)

            saveButtonDZ6.setOnClickListener {
                if (nameEditTextDZ6.text.length > 1 && ageEditTextDZ6.text.length > 0 && ImageLoader().isURL(urlEditTextDZ6.text.toString())) {
                    studentSinglton.editStudent(
                        studentSinglton.getStudent(id),
                        urlEditTextDZ6.text.toString(),
                        nameEditTextDZ6.text.toString(),
                        ageEditTextDZ6.text.toString().toInt()
                    )
                    this.finish()
                } else {
                    if (!ImageLoader().isURL(urlEditTextDZ6.text.toString())) Toast.makeText(this, "Please, enter correct image URL ", Toast.LENGTH_SHORT).show()

                    if (nameEditTextDZ6.text.length <= 1) Toast.makeText(this, "Please, enter correct name ", Toast.LENGTH_SHORT).show()

                    if (ageEditTextDZ6.text.length <= 0) Toast.makeText(this, "Please, enter correct age ", Toast.LENGTH_SHORT).show()
                }
            }
        } else {

            saveButtonDZ6.setOnClickListener {
                if (nameEditTextDZ6.text.length > 1 &&
                    ageEditTextDZ6.text.length > 0 &&
                    ImageLoader().isURL(urlEditTextDZ6.text.toString())) {
                    studentSinglton.addStudent(Student(urlEditTextDZ6.text.toString(), nameEditTextDZ6.text.toString(), ageEditTextDZ6.text.toString().toInt()))

                    this.finish()
                } else {
                    if (!ImageLoader().isURL(urlEditTextDZ6.text.toString())) Toast.makeText(this, "Please, enter correct image URL ", Toast.LENGTH_SHORT).show()

                    if (nameEditTextDZ6.text.length <= 1) Toast.makeText(this, "Please, enter correct name ", Toast.LENGTH_SHORT).show()

                    if (ageEditTextDZ6.text.length <= 0) Toast.makeText(this, "Please, enter correct age ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
