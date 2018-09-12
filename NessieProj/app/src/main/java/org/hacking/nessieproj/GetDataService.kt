package org.hacking.nessieproj

import org.hacking.nessieproj.models.APIResponse
import org.hacking.nessieproj.models.Bill
import org.hacking.nessieproj.models.CustomerAccount
import org.hacking.nessieproj.models.ObservableAccount
import retrofit2.Call
import retrofit2.http.*


interface GetDataService {

    @GET("/customers/{customer_id}/accounts")
    fun getCustomerAccounts(@Path("customer_id", encoded = true) customerId: String,
                            @Query("key") key: String) : Call<List<CustomerAccount>>

    @POST("/customers/{customer_id}/accounts")
    fun postCustomerAccounts(@Path("customer_id", encoded = true) customerId: String,
                             @Body account: ObservableAccount,
                             @Query("key") key: String) : Call<APIResponse>

    @DELETE("/data")
    fun deleteCustomerAccounts(@Query("type") type: String,
                               @Query("key") key: String) : Call<APIResponse>

    @GET("/customers/{customer_id}/bills")
    fun getCustomerBills(@Path("customer_id", encoded = true) customerId: String,
                         @Query("key") key: String) : Call<List<Bill>>
}