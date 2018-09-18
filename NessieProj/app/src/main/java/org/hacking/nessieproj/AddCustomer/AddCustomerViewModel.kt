package org.hacking.nessieproj.AddCustomer

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.hacking.nessieproj.Api.GetDataService
import org.hacking.nessieproj.Api.RetrofitClientInstance
import org.hacking.nessieproj.models.APIResponse
import org.hacking.nessieproj.models.Customer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCustomerViewModel : ViewModel() {

    private val service = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)
    val apiResponse: MutableLiveData<Int> = MutableLiveData()
    val customer = MutableLiveData<Customer>()

    fun addCustomer(customer : Customer?) {
        val call = service.addCustomer(customer!!, RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                apiResponse.value = Character.getNumericValue(response.code().toString()[0])
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                apiResponse.value = -1
            }
        })
    }
}