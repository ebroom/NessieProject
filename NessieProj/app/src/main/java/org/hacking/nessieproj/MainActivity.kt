package org.hacking.nessieproj

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListeners()
    }

    fun setupListeners() {
        customerInfo.setOnClickListener {
            startActivity(Intent(this, CustomerActivity::class.java))
        }
        addCustomer.setOnClickListener {
            startActivity(Intent(this, AddCustomerActivity::class.java))
        }
        atms.setOnClickListener {
            startActivity(Intent(this, AtmActivity::class.java))
        }
    }
}