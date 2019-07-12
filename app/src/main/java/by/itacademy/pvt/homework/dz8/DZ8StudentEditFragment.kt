package by.itacademy.pvt.homework.dz8

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.itacademy.pvt.homework.R
import by.itacademy.pvt.homework.dz8.DZ8Singleton.Companion.ID_KEY
import by.itacademy.pvt.homework.isURL
import kotlinx.android.synthetic.main.fragment_dz8_student_edit.*

class DZ8StudentEditFragment : Fragment() {

    companion object {

        fun getInstance(id: Long): DZ8StudentEditFragment {

            val fragment = DZ8StudentEditFragment()

            val bundle = Bundle()
            bundle.putLong(ID_KEY, id)

            fragment.arguments = bundle

            return fragment
        }
    }

    private var id: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        id = arguments?.getLong(ID_KEY, 0) ?: 0

        return inflater.inflate(R.layout.fragment_dz8_student_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val studentSinglton = DZ8Singleton.instance

        if (id > 0) {
            try {
                val student = studentSinglton.getStudent(id)

                nameEditTextDZ8.setText(student.name)
                ageEditTextDZ8.setText(student.age.toString())
                urlEditTextDZ8.setText(student.url)
            } catch (e: Exception) {
                Toast.makeText(
                    activity!!.applicationContext,
                    resources.getString(R.string.student_not_find),
                    Toast.LENGTH_SHORT
                )
            }

            saveButtonDZ8.setOnClickListener {
                if (nameEditTextDZ8.text.isNotEmpty() && ageEditTextDZ8.text.isNotEmpty() && isURL(urlEditTextDZ8.text.toString())) {

                    val student = Student(
                        id,
                        urlEditTextDZ8.text.toString(),
                        nameEditTextDZ8.text.toString(),
                        ageEditTextDZ8.text.toString().toInt()
                    )

                    studentSinglton.editStudent(student)
                    (activity as DZ8Activty).listEdited()
                } else {
                    checkEditTexts()
                }
            }
        } else {
            saveButtonDZ8.setOnClickListener {
                if (nameEditTextDZ8.text.isNotEmpty() && ageEditTextDZ8.text.isNotEmpty() && isURL(urlEditTextDZ8.text.toString())) {

                    val student = Student(
                        studentSinglton.generateID(),
                        urlEditTextDZ8.text.toString(),
                        nameEditTextDZ8.text.toString(),
                        ageEditTextDZ8.text.toString().toInt()
                    )

                    studentSinglton.addStudent(student)
                    (activity as DZ8Activty).listEdited()
                } else {
                    checkEditTexts()
                }
            }
        }
    }

    private fun checkEditTexts() {
        if (!isURL(urlEditTextDZ8.text.toString())) Toast.makeText(
            activity!!.applicationContext,
            resources.getString(R.string.enter_correct_url),
            Toast.LENGTH_SHORT
        ).show()

        if (nameEditTextDZ8.text.isEmpty()) Toast.makeText(
            activity!!.applicationContext,
            resources.getString(R.string.enter_correct_name),
            Toast.LENGTH_SHORT
        ).show()

        if (ageEditTextDZ8.text.isEmpty()) Toast.makeText(
            activity!!.applicationContext,
            resources.getString(R.string.enter_correct_age),
            Toast.LENGTH_SHORT
        ).show()
    }
}