package org.hacking.nessieproj.addCustomer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_customer.*
import org.hacking.nessieproj.MessageText
import org.hacking.nessieproj.R
import org.hacking.nessieproj.customerAccount.CustomerActivity
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

    private fun setupListeners() {
        add_customer.setOnClickListener {
            viewmodel.addCustomer(binding.customer)
        }
    }

    private fun setupObservers() {
        viewmodel.apiResponse.observe(this, Observer {
            val message = MessageText.getMessageFromApiCode(it!!)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
        viewmodel.apiObjectCreated.observe(this, Observer {
            it?.let { customer ->
                val intent = Intent(this, CustomerActivity::class.java)
                intent.putExtra("customerId", customer.customerId)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        })
    }
}