package by.itacademy.pvt.homework.dz8

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import by.itacademy.pvt.homework.R
import kotlinx.android.synthetic.main.fragment_dz8_student_list.*

class DZ8StudentListFragment() : Fragment(), DZ8Adapter.ClickListener {

    private lateinit var recyclerViewDZ8: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerViewDZ8 = view.findViewById<RecyclerView>(R.id.recyclerViewDZ8)

        recyclerViewDZ8.setHasFixedSize(true)
        recyclerViewDZ8.layoutManager =
            LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerViewDZ8.isNestedScrollingEnabled = false
        recyclerViewDZ8.adapter = DZ8Adapter(DZ8Singleton.instance.getStudentsList(), this)

        val addStudentImageViewDZ8 = view.findViewById<ImageView>(R.id.addStudentImageViewDZ8)
        addStudentImageViewDZ8.setOnClickListener {
            addStudent()
        }

        val findStudentEditTextDZ8 = view.findViewById<EditText>(R.id.findStudentEditTextDZ8)

        findStudentEditTextDZ8.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                (recyclerViewDZ8.adapter as DZ8Adapter).showList(
                    (DZ8Singleton.instance.getStudentsList().filter {
                        it.name.contains(s as CharSequence, true)
                    } as ArrayList<Student>)
                )
            }
        })
    }

    override fun onItemClicked(item: Student) {
        showStudentDetail(item)
    }

    override fun onResume() {
        super.onResume()
        (recyclerViewDZ8.adapter as DZ8Adapter).showList(DZ8Singleton.instance.getStudentsList())
        findStudentEditTextDZ8.setText((activity as DZ8Activty).getUserText())
    }

    override fun onPause() {
        super.onPause()
        (activity as DZ8Activty).saveUserText(findStudentEditTextDZ8.text.toString())
    }

    private fun addStudent() {
        (activity as DZ8Activty).addStudent()
    }

    private fun showStudentDetail(student: Student) {
        (activity as DZ8Activty).showStudentDetail(student.id)
    }

    fun refreshList() {
        (recyclerViewDZ8.adapter as DZ8Adapter).showList(DZ8Singleton.instance.getStudentsList())
    }
}