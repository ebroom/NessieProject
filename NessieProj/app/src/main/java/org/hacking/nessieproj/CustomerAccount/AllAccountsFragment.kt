package org.hacking.nessieproj.customerAccount

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.accounts_recycler_view.*
import org.hacking.nessieproj.R
import org.hacking.nessieproj.models.CustomerAccount

class AllAccountsFragment : Fragment() {
    private lateinit var viewmodel : CustomerViewModel
    private var viewGroup : ViewGroup? = null
    private lateinit var noAccountsTextView : TextView

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.accounts_recycler_view, container, false)
        viewmodel = ViewModelProviders.of(activity).get(CustomerViewModel::class.java)
        noAccountsTextView = TextView(activity)
        viewGroup = container
        return view
    }

    private fun setupFragment() {
        viewmodel.getCustomerAccounts()
        setObservers()
    }

    private fun setObservers() {
        viewmodel.customerAccounts.observe(this, Observer {
            if (it == null || it.isEmpty()) {
                noAccountsTextView.text = "No Accounts Found"
                if (accounts_layout.indexOfChild(noAccountsTextView) == -1) {
                    noAccountsTextView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    accounts_layout.addView(noAccountsTextView)
                }
            } else {
                if (accounts_layout.indexOfChild(noAccountsTextView) != -1) {
                    accounts_layout.removeView(noAccountsTextView)
                }
                generateDataList(it)
            }
        })
        viewmodel.wasDeposited.observe(this, Observer {
            viewmodel.getCustomerAccounts()
        })
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    fun generateDataList(accountList: List<CustomerAccount>) {
        val adapter = CustomerAccountAdapter(activity, accountList, viewmodel)
        val layoutManager = LinearLayoutManager(activity)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
    }
}