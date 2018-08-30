package org.hacking.nessieproj

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GetDataService {

    @GET("/customers/{customer_id}/accounts")
    fun getCustomerAccounts(@Path("customer_id", encoded = true) customerId: String,
                            @Query("key") key: String) : Call<List<CustomerAccount>>
}