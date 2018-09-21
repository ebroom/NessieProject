package org.hacking.nessieproj.customerAccount

import android.app.ProgressDialog
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.post_accounts.*
import org.hacking.nessieproj.R
import org.hacking.nessieproj.databinding.PostAccountsBinding
import org.hacking.nessieproj.models.ObservableAccount

class AddAccountFragment : Fragment() {

    private lateinit var viewmodel : CustomerViewModel
    private lateinit var progressDialog : ProgressDialog
    private lateinit var binding : PostAccountsBinding
    private var viewGroup : ViewGroup? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPostAccountsFields()
    }

    private fun showPostAccountsFields() {
        //val rootView = LayoutInflater.from(this).inflate(R.layout.post_accounts, this.findViewById(android.R.id.content), true)
        //val binding: PostAccountsBinding? = DataBindingUtil.bind(rootView)

        //customer_account_layout.addView(binding.accountLayout, 2)
        //binding.editType.text = account.edit_type.text
        binding.account = viewmodel.postAccount.value
        addAccountButton.setOnClickListener {
            //startProgressDialog()
            viewmodel.postCustomerAccounts(binding.account)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //val view = inflater!!.inflate(R.layout.post_accounts_fragment, container, false)
        val layoutInflater = LayoutInflater.from(activity)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.post_accounts, viewGroup, false)
        viewmodel = ViewModelProviders.of(activity).get(CustomerViewModel::class.java)
        progressDialog = ProgressDialog(activity)
        viewGroup = container
        savedInstanceState ?: run { viewmodel.postAccount.value = ObservableAccount() }
        return binding.root
    }
}