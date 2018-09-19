package org.hacking.nessieproj

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.hacking.nessieproj.api.GetDataService
import org.hacking.nessieproj.api.RetrofitClientInstance
import org.hacking.nessieproj.models.Customer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val service = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)
    val customers = MutableLiveData<List<Customer>>()

    fun getCustomers() {
        val call = service.getCustomers(RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<List<Customer>> {
            override fun onResponse(call: Call<List<Customer>>, response: Response<List<Customer>>) {
                customers.value = response.body()
            }

            override fun onFailure(call: Call<List<Customer>>?, t: Throwable?) {
                customers.value = null
            }
        })
    }
}