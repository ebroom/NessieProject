package org.hacking.nessieproj.atm

import android.annotation.SuppressLint
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.hacking.nessieproj.R
import org.hacking.nessieproj.databinding.AtmDataBinding
import org.hacking.nessieproj.models.Atm

@SuppressLint("IncorrectAdapter")
class AtmListAdapter(private val context: Context, private var dataList: List<Atm>?) :
        RecyclerView.Adapter<AtmListAdapter.CustomViewHolder>() {

    class CustomViewHolder(val binding: AtmDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : AtmDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.atm_data, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        dataList?.let {
            holder.binding.atm = it[position]
        }
    }

    override fun getItemCount(): Int {
        return dataList?.let {
            it.size
        } ?: kotlin.run {
            0
        }
    }

    fun addData(atms : List<Atm>) {
        dataList = atms
        notifyDataSetChanged()
    }
}