package org.hacking.nessieproj.addCustomer

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.hacking.nessieproj.api.GetDataService
import org.hacking.nessieproj.api.RetrofitClientInstance
import org.hacking.nessieproj.models.APIResponse
import org.hacking.nessieproj.models.Customer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCustomerViewModel : ViewModel() {

    private val service = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)
    val apiResponse: MutableLiveData<Int> = MutableLiveData()
    val customer = MutableLiveData<Customer>()
    val apiObjectCreated = MutableLiveData<Customer>()

    fun addCustomer(customer : Customer?) {
        val call = service.addCustomer(customer!!, RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<APIResponse<Customer>> {
            override fun onResponse(call: Call<APIResponse<Customer>>, response: Response<APIResponse<Customer>>) {
                apiResponse.value = Character.getNumericValue(response.code().toString()[0])
                response.body()?.objectCreated?.let {
                    apiObjectCreated.value = it
                }
            }

            override fun onFailure(call: Call<APIResponse<Customer>>, t: Throwable) {
                apiResponse.value = -1
            }
        })
    }
}