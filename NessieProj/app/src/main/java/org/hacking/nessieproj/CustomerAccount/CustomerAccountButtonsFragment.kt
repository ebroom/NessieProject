package org.hacking.nessieproj.CustomerAccount

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.customer_account_buttons.*
import org.hacking.nessieproj.R
import org.hacking.nessieproj.databinding.CustomerBillBinding

class CustomerAccountButtonsFragment : Fragment() {

    private lateinit var viewmodel : CustomerViewModel
    private lateinit var progressDialog : ProgressDialog
    private lateinit var noAccountsTextView : TextView
    private var viewGroup : ViewGroup? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.customer_account_buttons, container, false)
        viewmodel = ViewModelProviders.of(this).get(CustomerViewModel::class.java)
        progressDialog = ProgressDialog(activity)
        noAccountsTextView = TextView(activity)
        viewGroup = container
        return view
    }

    private fun setupFragment() {
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        getAccounts.setOnClickListener {
            val allAccountsFragment = AllAccountsFragment()
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.main_layout, allAccountsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        postAccounts.setOnClickListener {
            val addAccountsFragment = AddAccountFragment()
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.main_layout, addAccountsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        deleteAccounts.setOnClickListener{
            startProgressDialog()
            viewmodel.deleteCustomerAccounts()
        }
        getBills.setOnClickListener {
            startProgressDialog()
            viewmodel.getCustomerBills()
        }
    }

    private fun setObservers() {
        viewmodel.customerBills.observe(this, Observer {
            it?.let {
                val layoutInflater = LayoutInflater.from(context)
                val binding : CustomerBillBinding = DataBindingUtil.inflate(layoutInflater, R.layout.customer_bill, viewGroup, false)
                binding.bill = viewmodel.customerBills.value
                val billView = binding.root
                customer_account_layout.addView(billView)
            } ?: run {
                noAccountsTextView.text = "No Bills Found"
                if (customer_account_layout.indexOfChild(noAccountsTextView) == -1) {
                    noAccountsTextView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    customer_account_layout.addView(noAccountsTextView)
                }
            }
        })

        viewmodel.loadingIndicator.observe(this, Observer {
            if (!it!!) {
                progressDialog.dismiss()
            }
        })

        viewmodel.apiResponse.observe(this, Observer {
            var message = ""
            when(it!!) {
                -1 -> message = "Something went wrong...Please try later!"
                2 -> message = "Request was successful!"
                4 -> message = "Invalid request"
                5 -> message = "Server error...Please try later!"
            }
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun startProgressDialog() {
        progressDialog.setMessage("Loading....")
        progressDialog.show()
    }
}