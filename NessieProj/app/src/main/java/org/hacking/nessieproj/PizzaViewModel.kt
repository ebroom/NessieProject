package org.hacking.nessieproj

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import org.hacking.nessieproj.Api.GetDataService
import org.hacking.nessieproj.Api.RetrofitClientInstance
import org.hacking.nessieproj.models.EnterpriseMerchantList
import org.hacking.nessieproj.models.Merchant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PizzaViewModel : ViewModel() {
    private val service = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)
    val pizzaMerchants = MutableLiveData<List<Merchant>>()
    val loadingIndicator = MutableLiveData<Boolean>()

    fun getPizzaMerchants() {
        val call = service.getEnterpriseMerchants(RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<EnterpriseMerchantList> {
            override fun onResponse(call: Call<EnterpriseMerchantList>, response: Response<EnterpriseMerchantList>) {
                val tempPizzaMerchants = arrayListOf<Merchant>()
                for (merchant in response.body()!!.results!!) {
                    if (merchant.name!!.contains("pizza", ignoreCase = true)) {
                        tempPizzaMerchants.add(merchant)
                    }
                }
                pizzaMerchants.value = tempPizzaMerchants
                loadingIndicator.value = false
            }

            override fun onFailure(call: Call<EnterpriseMerchantList>, t: Throwable) {
                loadingIndicator.value =false
                Log.d("ehbroom", t.message)
            }
        })
    }
}