package org.hacking.nessieproj.pizza

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_pizza.*
import org.hacking.nessieproj.DividerLineComponent
import org.hacking.nessieproj.R

class PizzaActivity : AppCompatActivity() {

    private lateinit var viewmodel : PizzaViewModel
    private lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza)
        viewmodel = ViewModelProviders.of(this).get(PizzaViewModel::class.java)
        progressDialog = ProgressDialog(this)
        setupObservers()
        startProgressDialog()
        viewmodel.getPizzaMerchants()
    }

    private fun setupRecyclerView() {
        val adapter = PizzaListAdapter(this, viewmodel.pizzaMerchants.value)
        val layoutManager = LinearLayoutManager(this)
        pizza_recycler_view.layoutManager = layoutManager
        pizza_recycler_view.adapter = adapter
        pizza_recycler_view.addItemDecoration(DividerLineComponent(this@PizzaActivity))
    }

    private fun setupObservers() {
        viewmodel.loadingIndicator.observe(this, Observer {
            if (!it!!) {
                progressDialog.dismiss()
            }
        })
        viewmodel.pizzaMerchants.observe(this, Observer {
            it?.let {
                setupRecyclerView()
            }
        })
    }

    private fun startProgressDialog() {
        progressDialog.setMessage("Loading....")
        progressDialog.show()
    }
}