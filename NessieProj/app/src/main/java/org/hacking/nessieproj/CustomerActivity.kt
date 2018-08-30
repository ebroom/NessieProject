package org.hacking.nessieproj

import CustomAdapter
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerActivity : AppCompatActivity() {

    private lateinit var adapter : CustomAdapter
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading....")
        progressDialog.show()

        val service = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)
        val call = service.getCustomerAccounts("5b845df9322fa06b67793c12", RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<List<CustomerAccount>> {
            override fun onResponse(call: Call<List<CustomerAccount>>, response: Response<List<CustomerAccount>>) {
                progressDialog.dismiss()
                response.body()?.let {
                    generateDataList(it)
                }
            }

            override fun onFailure(call: Call<List<CustomerAccount>>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@CustomerActivity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
            }
        })

    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private fun generateDataList(accountList: List<CustomerAccount>) {
        recyclerView  = findViewById(R.id.recycler_view)
        adapter = CustomAdapter(this, accountList)
        val layoutManager = LinearLayoutManager(this@CustomerActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
