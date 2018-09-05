package org.hacking.nessieproj

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomerViewModel : ViewModel() {

    private val service = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)

    val customerAccounts: MutableLiveData<List<CustomerAccount>> = MutableLiveData()
    val loadingIndicator: MutableLiveData<Boolean> = MutableLiveData()
    val apiSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun getCustomerAccounts() {
        val call = service.getCustomerAccounts(RetrofitClientInstance.CUSTOMER_ID, RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<List<CustomerAccount>> {
            override fun onResponse(call: Call<List<CustomerAccount>>, response: Response<List<CustomerAccount>>) {
                customerAccounts.value = response.body()!!
                loadingIndicator.value = false
            }

            override fun onFailure(call: Call<List<CustomerAccount>>, t: Throwable) {
                loadingIndicator.value = false
                apiSuccess.value = false
            }
        })
    }

    fun postCustomerAccounts(account: Account?) {
        val call = service.postCustomerAccounts(RetrofitClientInstance.CUSTOMER_ID, account!!, RetrofitClientInstance.API_KEY)

        call.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                loadingIndicator.value = false
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                loadingIndicator.value = false
                apiSuccess.value = false
            }
        })
    }

    fun deleteCustomerAccounts() {
//        val progressDialog = ProgressDialog(activity)
//        progressDialog.setMessage("Loading....")
//        progressDialog.show()

        val call = service.deleteCustomerAccounts("Accounts", RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
//                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
//                progressDialog.dismiss()
//                Toast.makeText(activity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}