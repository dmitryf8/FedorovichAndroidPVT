package by.itacademy.pvt.homework.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import by.itacademy.pvt.homework.R
import kotlinx.android.synthetic.main.activity_student_list_dz6.*

class DZ6StudentListActivity : Activity(), DZ6Adapter.ClickListener {

    private lateinit var appPrefManager: AppPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list_dz6)

        recyclerViewDZ6.setHasFixedSize(true)
        recyclerViewDZ6.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewDZ6.isNestedScrollingEnabled = false
        recyclerViewDZ6.adapter = DZ6Adapter(DZ6Singleton.instance.getStudentsList(), this)

        appPrefManager = AppPrefManager(this)

        addStudentImageView.setOnClickListener {
            startAddStudentActivity()
        }

        findStudentEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                (recyclerViewDZ6.adapter as DZ6Adapter).showList(
                    (DZ6Singleton.instance.getStudentsList().filter {
                        it.name.contains(s as CharSequence, true)
                    } as ArrayList<Student>)
                )
            }
        })
    }

    override fun onItemClicked(item: Student) {
        intent = Intent(this@DZ6StudentListActivity, DZ6StudentDetailsActivity::class.java)
        val id: Long = item.id
        intent.putExtra(DZ6StudentDetailsActivity.ID, id)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        (recyclerViewDZ6.adapter as DZ6Adapter).showList(DZ6Singleton.instance.getStudentsList())

        findStudentEditText.setText(appPrefManager.getUserText())
    }

    override fun onPause() {
        super.onPause()
        appPrefManager.saveUserText(findStudentEditText.text.toString())
    }

    private fun startAddStudentActivity() {
        val intent = Intent(this@DZ6StudentListActivity, DZ6StudentEditActivity::class.java)
        startActivity(intent)
    }
}
