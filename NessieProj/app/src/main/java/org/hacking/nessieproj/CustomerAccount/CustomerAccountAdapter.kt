package org.hacking.nessieproj.customerAccount

import android.annotation.SuppressLint
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.hacking.nessieproj.R
import org.hacking.nessieproj.databinding.CustomerAccountBinding
import org.hacking.nessieproj.models.CustomerAccount
import org.hacking.nessieproj.models.Deposit
import org.hacking.nessieproj.models.Purchase

@SuppressLint("IncorrectAdapter")
class CustomerAccountAdapter(private val context: Context, private val dataList: List<CustomerAccount>, private val viewmodel: CustomerViewModel) :
        RecyclerView.Adapter<CustomerAccountAdapter.CustomViewHolder>() {

    class CustomViewHolder(val binding: CustomerAccountBinding, private val viewmodel: CustomerViewModel) :
            RecyclerView.ViewHolder(binding.root) {
        init {
            binding.pizza.setOnClickListener {
                viewmodel.orderPizza(binding.accountId.text.toString(), Purchase())
            }
            binding.deposit.setOnClickListener {
                binding.depositAmount.text?.toString()?.toDouble()?.let {value ->
                    if (value > 0) {
                        val deposit = Deposit()
                        deposit.amount = value
                        viewmodel.deposit(binding.accountId.text.toString(), deposit)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CustomerAccountBinding = DataBindingUtil.inflate(layoutInflater, R.layout.customer_account, parent, false)
        return CustomViewHolder(binding, viewmodel)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.customerAccount = dataList[position]
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}