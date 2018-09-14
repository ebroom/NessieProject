package org.hacking.nessieproj

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_customer.*
import org.hacking.nessieproj.databinding.ActivityAddCustomerBinding
import org.hacking.nessieproj.models.Customer

class AddCustomerActivity : AppCompatActivity() {

    private lateinit var viewmodel : AddCustomerViewModel
    private lateinit var binding : ActivityAddCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_customer)
        viewmodel = ViewModelProviders.of(this).get(AddCustomerViewModel::class.java)
        savedInstanceState ?: kotlin.run {
            viewmodel.customer.value = Customer()
        }
        binding.customer = viewmodel.customer.value
        setupListeners()
        setupObservers()
    }

    fun setupListeners() {
        add_customer.setOnClickListener {
            viewmodel.addCustomer(binding.customer)
        }
    }

    fun setupObservers() {
        viewmodel.apiResponse.observe(this, Observer {
            var message = ""
            when(it!!) {
                -1 -> message = "Something went wrong...Please try later!"
                2 -> message = "Request was successful!"
                4 -> message = "Invalid request"
                5 -> message = "Server error...Please try later!"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }
}