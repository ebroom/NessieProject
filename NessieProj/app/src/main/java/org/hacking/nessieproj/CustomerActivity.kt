package org.hacking.nessieproj

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_customer.*
import org.hacking.nessieproj.databinding.PostAccountsBinding



class CustomerActivity : AppCompatActivity() {

    private lateinit var adapter : CustomAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var viewmodel : CustomerViewModel
    private lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)
        progressDialog = ProgressDialog(this)
        viewmodel = ViewModelProviders.of(this).get(CustomerViewModel::class.java)

        viewmodel.customerAccounts.observe(this, Observer {
            generateDataList(it!!)
        })


        viewmodel.loadingIndicator.observe(this, Observer {
            if (!it!!) {
                progressDialog.dismiss()
            }
        })

        viewmodel.apiSuccess.observe(this, Observer {
            if (!it!!) {
                Toast.makeText(this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
            }
        })

        getAccounts.setOnClickListener {
            progressDialog.setMessage("Loading....")
            progressDialog.show()

            viewmodel.getCustomerAccounts()
        }
        postAccounts.setOnClickListener { showPostAccountsFields() }
    }

    private fun showPostAccountsFields() {
        //val rootView = LayoutInflater.from(this).inflate(R.layout.post_accounts, this.findViewById(android.R.id.content), true)
        //val binding: PostAccountsBinding? = DataBindingUtil.bind(rootView)
        val layoutInflater = LayoutInflater.from(this)
        val binding : PostAccountsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.post_accounts,
                findViewById(R.id.main_layout), true)
        //binding.editType.text = account.edit_type.text
        binding.account = Account()
        postAccounts.setOnClickListener {
            progressDialog.setMessage("Loading....")
            progressDialog.show()
            viewmodel.postCustomerAccounts(binding.account)
        }
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    fun generateDataList(accountList: List<CustomerAccount>) {
        recyclerView  = findViewById(R.id.recycler_view)
        adapter = CustomAdapter(this, accountList)
        val layoutManager = LinearLayoutManager(this@CustomerActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
