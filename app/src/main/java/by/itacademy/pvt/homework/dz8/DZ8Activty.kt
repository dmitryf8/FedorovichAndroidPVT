package by.itacademy.pvt.homework.dz8

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import by.itacademy.pvt.homework.R

class DZ8Activty : FragmentActivity() {

    private lateinit var fragmentStudentList: DZ8StudentListFragment
    private lateinit var appPrefManager: AppPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8)

        fragmentStudentList = DZ8StudentListFragment()

        appPrefManager = AppPrefManager(this)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.containerOneDZ8, fragmentStudentList)

            transaction.commit()
        } else {

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.containerDZ8, fragmentStudentList)

            transaction.commit()
        }
    }

    fun addStudent() {

        val fragmentEditStudent = DZ8StudentEditFragment()

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.containerOneDZ8, fragmentStudentList)
                .replace(R.id.containerTwoDZ8, fragmentEditStudent)

            transaction.commit()
        } else {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.containerDZ8, fragmentEditStudent).addToBackStack("add")

            transaction.commit()
        }
    }

    fun showStudentDetail(id: Long) {
        val fragmentDetailStudent = DZ8StudentDetailsFragment.getInstance(id)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.containerOneDZ8, fragmentStudentList)
                .replace(R.id.containerTwoDZ8, fragmentDetailStudent)

            transaction.commit()
        } else {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.containerDZ8, fragmentDetailStudent).addToBackStack("detail")

            transaction.commit()
        }
    }

    fun editStudent(id: Long) {
        val fragmentEditStudent = DZ8StudentEditFragment.getInstance(id)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.containerOneDZ8, fragmentStudentList)
                .replace(R.id.containerTwoDZ8, fragmentEditStudent)

            transaction.commit()
        } else {
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.containerDZ8, fragmentEditStudent).addToBackStack("edit")

            transaction.commit()
        }
    }

    fun listEdited() {
        fragmentStudentList.refreshList()
    }

    fun removeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.remove(fragment)

        transaction.commit()
    }

    fun getUserText(): String {
        return appPrefManager.getUserText()
    }

    fun saveUserText(s: String) {
        appPrefManager.saveUserText(s)
    }
}