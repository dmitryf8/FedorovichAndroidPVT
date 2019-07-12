package by.itacademy.pvt.homework.dz8

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.itacademy.pvt.homework.R
import by.itacademy.pvt.homework.dz8.DZ8Singleton.Companion.ID_KEY
import by.itacademy.pvt.homework.load
import kotlinx.android.synthetic.main.fragment_dz8_student_details.*

class DZ8StudentDetailsFragment : Fragment() {

    companion object {

        fun getInstance(id: Long): DZ8StudentDetailsFragment {

            val fragment = DZ8StudentDetailsFragment()

            val bundle = Bundle()
            bundle.putLong(ID_KEY, id)

            fragment.arguments = bundle

            return fragment
        }
    }

    private var id: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        id = arguments?.getLong(ID_KEY, 0) ?: 0

        return inflater.inflate(R.layout.fragment_dz8_student_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val studentSinglton = DZ8Singleton.instance

        val student = studentSinglton.getStudent(id)

        load(imageViewDetailDZ8, student.url)
        studentNameTextViewDZ8.text = student.name
        studentAgeTextViewDZ8.text = student.age.toString()

        deleteButtonDZ8.setOnClickListener {
            studentSinglton.deleteStudent(student)
            Toast.makeText(activity!!.applicationContext, "Student deleted", Toast.LENGTH_SHORT).show()
            (activity as DZ8Activty).listEdited()
            close(this)
        }

        editButtonDZ8.setOnClickListener {
            (activity as DZ8Activty).editStudent(id)
        }
    }

    private fun close(fragment: Fragment) {
        (activity as DZ8Activty).removeFragment(fragment)
    }
}