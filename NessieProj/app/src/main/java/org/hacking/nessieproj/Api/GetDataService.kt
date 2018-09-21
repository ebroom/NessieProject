package org.hacking.nessieproj.api

import org.hacking.nessieproj.models.*
import retrofit2.Call
import retrofit2.http.*


interface GetDataService {

    @GET("/customers/{customer_id}/accounts")
    fun getCustomerAccounts(@Path("customer_id", encoded = true) customerId: String,
                            @Query("key") key: String): Call<List<CustomerAccount>>

    @POST("/customers/{customer_id}/accounts")
    fun postCustomerAccounts(@Path("customer_id", encoded = true) customerId: String,
                             @Body account: ObservableAccount,
                             @Query("key") key: String): Call<APIResponse<ObservableAccount>>

    @DELETE("/data")
    fun deleteCustomerAccounts(@Query("type") type: String,
                               @Query("key") key: String): Call<APIResponse<String>>

    @GET("/customers/{customer_id}/bills")
    fun getCustomerBills(@Path("customer_id", encoded = true) customerId: String,
                         @Query("key") key: String): Call<List<Bill>>

    @POST("/customers")
    fun addCustomer(@Body customer: Customer,
                    @Query("key") key: String): Call<APIResponse<Customer>>

    @GET("/atms")
    fun getAtms(@Query("lat") lat: Double,
                @Query("lng") lng: Double,
                @Query("rad") radius: Int = 1,
                @Query("page") page: Int = 1,
                @Query("key") key: String): Call<AtmList>

    @GET
    fun getNextPage(@Url url : String): Call<AtmList>

    @POST("/accounts/{id}/purchases")
    fun orderPizza(@Path("id") accountId: String,
                   @Query("key") key: String,
                   @Body purchase: Purchase): Call<APIResponse<Purchase>>

    @POST("/accounts/{id}/deposits")
    fun deposit(@Path("id") accountId: String,
                @Query("key") key: String,
                @Body deposit: Deposit): Call<APIResponse<Deposit>>

    @GET("/enterprise/merchants")
    fun getEnterpriseMerchants(@Query("key") key: String): Call<EnterpriseMerchantList>

    @GET("/customers")
    fun getCustomers(@Query("key") key: String): Call<List<Customer>>
}