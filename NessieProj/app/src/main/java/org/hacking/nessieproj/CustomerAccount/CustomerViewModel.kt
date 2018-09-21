package org.hacking.nessieproj.customerAccount

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.hacking.nessieproj.api.GetDataService
import org.hacking.nessieproj.api.RetrofitClientInstance
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
    val wasDeposited: MutableLiveData<Boolean> = MutableLiveData()
    val postAccount = MutableLiveData<ObservableAccount>()
    var customerId = ""

    fun getCustomerAccounts() {
        val call = service.getCustomerAccounts(customerId, RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<List<CustomerAccount>> {
            override fun onResponse(call: Call<List<CustomerAccount>>, response: Response<List<CustomerAccount>>) {
                customerAccounts.value = response.body()
                loadingIndicator.value = false
            }

            override fun onFailure(call: Call<List<CustomerAccount>>, t: Throwable) {
                updateValuesForApiFailure()
            }
        })
    }

    fun postCustomerAccounts(account: ObservableAccount?) {
        val call = service.postCustomerAccounts(customerId, account!!, RetrofitClientInstance.API_KEY)

        call.enqueue(object : Callback<APIResponse<ObservableAccount>> {
            override fun onResponse(call: Call<APIResponse<ObservableAccount>>, response: Response<APIResponse<ObservableAccount>>) {
                updateValuesForApiResponse(response.code())
            }

            override fun onFailure(call: Call<APIResponse<ObservableAccount>>, t: Throwable) {
                updateValuesForApiFailure()
            }
        })
    }

    fun deleteCustomerAccounts() {
        val call = service.deleteCustomerAccounts("Accounts", RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<APIResponse<String>> {
            override fun onResponse(call: Call<APIResponse<String>>, response: Response<APIResponse<String>>) {
                updateValuesForApiResponse(response.code())
            }

            override fun onFailure(call: Call<APIResponse<String>>, t: Throwable) {
                updateValuesForApiFailure()
            }
        })
    }

    fun getCustomerBills() {
        val call = service.getCustomerBills(customerId, RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<List<Bill>> {
            override fun onResponse(call: Call<List<Bill>>?, response: Response<List<Bill>>) {
                if (response.body()!!.isEmpty()) {
                    customerBills.value = null
                } else {
                    customerBills.value = response.body()!!.last()
                }
                loadingIndicator.value = false
            }

            override fun onFailure(call: Call<List<Bill>>?, t: Throwable?) {
                updateValuesForApiFailure()
            }
        })
    }

    fun orderPizza(accountId: String, purchase: Purchase) {
        val call = service.orderPizza(accountId, RetrofitClientInstance.API_KEY, purchase)
        call.enqueue(object : Callback<APIResponse<Purchase>> {
            override fun onResponse(call: Call<APIResponse<Purchase>>?, response: Response<APIResponse<Purchase>>) {
                updateValuesForApiResponse(response.code())
            }

            override fun onFailure(call: Call<APIResponse<Purchase>>?, t: Throwable?) {
                updateValuesForApiFailure()
            }
        })
    }

    fun deposit(accountId: String, deposit: Deposit) {
        val call = service.deposit(accountId, RetrofitClientInstance.API_KEY, deposit)
        call.enqueue(object : Callback<APIResponse<Deposit>> {
            override fun onResponse(call: Call<APIResponse<Deposit>>?, response: Response<APIResponse<Deposit>>) {
                updateValuesForApiResponse(response.code())
                wasDeposited.value = apiResponse.value == 2
            }

            override fun onFailure(call: Call<APIResponse<Deposit>>?, t: Throwable?) {
                updateValuesForApiFailure()
                wasDeposited.value = false
            }
        })
    }

    private fun updateValuesForApiResponse(responseCode : Int) {
        apiResponse.value = Character.getNumericValue(responseCode.toString()[0])
        loadingIndicator.value = false
    }

    private fun updateValuesForApiFailure() {
        loadingIndicator.value = false
        apiResponse.value = -1
    }
}