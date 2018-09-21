package org.hacking.nessieproj.customerAccount

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import org.hacking.nessieproj.MessageText
import org.hacking.nessieproj.R

class CustomerActivity : AppCompatActivity() {

    lateinit var viewmodel : CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)
        val bundle = Bundle()
        bundle.putString("customerId", intent.getStringExtra("customerId"))
        viewmodel = ViewModelProviders.of(this).get(CustomerViewModel::class.java)
        setObserver()
        savedInstanceState ?: run {
            val customerAccount = CustomerAccountButtonsFragment()
            customerAccount.arguments = bundle
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_layout, customerAccount)
            transaction.commit()
        }
    }

    private fun setObserver() {
        viewmodel.apiResponse.observe(this, Observer {
            Toast.makeText(this, MessageText.getMessageFromApiCode(it), Toast.LENGTH_SHORT).show()
        })
    }
}
