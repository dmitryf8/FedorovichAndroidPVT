package by.itacademy.pvt.homework
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.dz0Button)
            .setOnClickListener() {
                startDZ0()
            }
        findViewById<View>(R.id.dz1Button)
            .setOnClickListener() {
                startDZ1()
            }
        findViewById<View>(R.id.dz2Button)
            .setOnClickListener() {
                startDZ2()
            }
        findViewById<View>(R.id.dz2loginButton)
            .setOnClickListener() {
                startDZ2Login()
            }
    }
    private fun startDZ0() {
        val intent = Intent(this, DZ0Activity::class.java)
        startActivity(intent)
    }
    private fun startDZ1() {
        val intent = Intent(this, DZ1Activity::class.java)
        startActivity(intent)
    }
    private fun startDZ2() {
        val intent = Intent(this, DZ2Activity::class.java)
        startActivity(intent)
    }
    private fun startDZ2Login() {
        val intent = Intent(this, DZ2LoginActivity::class.java)
        startActivity(intent)
    }
}