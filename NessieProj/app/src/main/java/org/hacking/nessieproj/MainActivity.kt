package org.hacking.nessieproj

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.hacking.nessieproj.addCustomer.AddCustomerActivity
import org.hacking.nessieproj.atm.AtmActivity
import org.hacking.nessieproj.customerAccount.CustomerActivity
import org.hacking.nessieproj.pizza.PizzaActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        customerInfo.setOnClickListener {
            viewmodel.getCustomers()
        }
        atms.setOnClickListener {
            startActivity(Intent(this, AtmActivity::class.java))
        }
        all_the_pizza.setOnClickListener {
            startActivity(Intent(this, PizzaActivity::class.java))
        }
    }

    private fun setupObservers() {
        viewmodel.customers.observe(this, Observer {
            if (it == null || it.isEmpty()) {
                startActivity(Intent(this, AddCustomerActivity::class.java))
            } else {
                val intent = Intent(this, CustomerActivity::class.java)
                intent.putExtra("customerId", it[0].customerId)
                startActivity(intent)
            }
        })
    }
}