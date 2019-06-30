package by.itacademy.pvt.homework.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import by.itacademy.pvt.homework.R
import kotlinx.android.synthetic.main.activity_student_list_dz6.*

class DZ6StudentListActivity : Activity(), DZ6Adapter.ClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list_dz6)

        recyclerViewDZ6.setHasFixedSize(true)
        recyclerViewDZ6.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewDZ6.isNestedScrollingEnabled = false
        recyclerViewDZ6.adapter = DZ6Adapter(DZ6Singleton.instance.getStudentsList(), this)

        addStudentImageView.setOnClickListener {
            startAddStudentActivity()
        }
    }

    override fun onItemClickListener(item: Student) {
        intent = Intent(this@DZ6StudentListActivity, DZ6StudentDetailsActivity::class.java)
        val id: Long = item.id
        intent.putExtra("ID", id)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        (recyclerViewDZ6.adapter as DZ6Adapter).updateList()
    }

    private fun startAddStudentActivity() {
        val intent = Intent(this@DZ6StudentListActivity, DZ6StudentEditActivity::class.java)
        startActivity(intent)
    }
}
