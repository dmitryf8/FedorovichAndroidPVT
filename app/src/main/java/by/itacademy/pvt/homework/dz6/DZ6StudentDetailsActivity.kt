package by.itacademy.pvt.homework.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import by.itacademy.pvt.homework.R
import by.itacademy.pvt.homework.load
import kotlinx.android.synthetic.main.activity_dz6_detail_student.*

class DZ6StudentDetailsActivity : Activity() {

    lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_detail_student)

        val studentSinglton = DZ6Singleton.instance
        val id = intent.getLongExtra("ID", 0)

        student = studentSinglton.getStudent(id)

        deleteButtonDZ6.setOnClickListener {
            studentSinglton.deleteStudent(student)
            Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show()
            this.finish()
        }

        editButtonDZ6.setOnClickListener {
            val intent = Intent(this@DZ6StudentDetailsActivity, DZ6StudentEditActivity::class.java)
            intent.putExtra("ID", student.id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        load(imageViewDetailDZ6, student.url)
        studentNameTextView.text = student.name
        studentAgeTextView.text = student.age.toString()
    }
}