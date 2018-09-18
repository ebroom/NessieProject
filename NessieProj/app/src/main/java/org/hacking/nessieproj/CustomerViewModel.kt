package org.hacking.nessieproj

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.hacking.nessieproj.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomerViewModel : ViewModel() {

    private val service = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)

    val customerAccounts: MutableLiveData<List<CustomerAccount>> = MutableLiveData()
    val customerBills: MutableLiveData<Bill> = MutableLiveData()
    val loadingIndicator: MutableLiveData<Boolean> = MutableLiveData()
    val apiResponse: MutableLiveData<Int> = MutableLiveData()
    val postAccount = MutableLiveData<ObservableAccount>()

    fun getCustomerAccounts() {
        val call = service.getCustomerAccounts(RetrofitClientInstance.CUSTOMER_ID, RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<List<CustomerAccount>> {
            override fun onResponse(call: Call<List<CustomerAccount>>, response: Response<List<CustomerAccount>>) {
                customerAccounts.value = response.body()!!
                loadingIndicator.value = false
            }

            override fun onFailure(call: Call<List<CustomerAccount>>, t: Throwable) {
                updateValuesForApiFailure()
            }
        })
    }

    fun postCustomerAccounts(account: ObservableAccount?) {
        val call = service.postCustomerAccounts(RetrofitClientInstance.CUSTOMER_ID, account!!, RetrofitClientInstance.API_KEY)

        call.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                updateValuesForApiResponse(response)
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                updateValuesForApiFailure()
            }
        })
    }

    fun deleteCustomerAccounts() {
        val call = service.deleteCustomerAccounts("Accounts", RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                updateValuesForApiResponse(response)
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                updateValuesForApiFailure()
            }
        })
    }

    fun getCustomerBills() {
        val call = service.getCustomerBills(RetrofitClientInstance.CUSTOMER_ID, RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<List<Bill>> {
            override fun onResponse(call: Call<List<Bill>>?, response: Response<List<Bill>>) {
                if (response.body()!!.isEmpty()) {
                    customerBills.value = null
                } else {
                    customerBills.value = response.body()!![0]
                }
                loadingIndicator.value = false
            }

            override fun onFailure(call: Call<List<Bill>>?, t: Throwable?) {
                updateValuesForApiFailure()
            }
        })
    }

    fun orderPizza(accountId: String, purchase: Purchase) {
        val call = service.orderPizza(accountId,RetrofitClientInstance.API_KEY, purchase)
        call.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>?, response: Response<APIResponse>) {
                updateValuesForApiResponse(response)
            }

            override fun onFailure(call: Call<APIResponse>?, t: Throwable?) {
                updateValuesForApiFailure()
            }
        })
    }

//    fun getCustomerInfo() {
//        val call = service.getCustomerInfo()
//    }

    private fun updateValuesForApiResponse(response : Response<APIResponse>) {
        apiResponse.value = Character.getNumericValue(response.code().toString()[0])
        loadingIndicator.value = false
    }

    private fun updateValuesForApiFailure() {
        loadingIndicator.value = false
        apiResponse.value = -1
    }
}