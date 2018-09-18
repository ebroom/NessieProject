package org.hacking.nessieproj

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.hacking.nessieproj.models.Atm
import org.hacking.nessieproj.models.AtmList
import org.hacking.nessieproj.models.Paging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AtmViewModel : ViewModel() {

    private val service = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)
    val atmList = MutableLiveData<List<Atm>>()
    private val atmArrayList = ArrayList<Atm>()
    val paging = MutableLiveData<Paging>()
    val isLoading = MutableLiveData<Boolean>().apply { postValue(false) }

    fun getAtms(lat: Double, lng: Double, radius: Int, page: Int = 0) {
        isLoading.value = true
        atmArrayList.clear()
        val call = service.getAtms(lat, lng, radius, page, RetrofitClientInstance.API_KEY)
        call.enqueue(object : Callback<AtmList> {
            override fun onResponse(call: Call<AtmList>, response: Response<AtmList>) {
                response.body()?.data?.let {
                    atmArrayList.addAll(it)
                    atmList.value = it
                }
                response.body()?.paging?.let {
                    paging.value = it
                } ?: kotlin.run {
                    paging.value = null
                }
                isLoading.value = false
            }

            override fun onFailure(call: Call<AtmList>, t: Throwable) {
                isLoading.value = false
            }
        })
    }

    fun getNextPage(url : String) {
        isLoading.value = true
        val call = service.getNextPage(url)
        call.enqueue(object : Callback<AtmList> {
            override fun onResponse(call: Call<AtmList>, response: Response<AtmList>) {
                response.body()?.let {
                    it.paging?.let {
                        paging.value = it
                    } ?: kotlin.run {
                        paging.value = null
                    }
                    it.data?.let {
                        if (it.isEmpty()) {
                            paging.value = null
                        } else {
                            atmArrayList.addAll(it)
                            atmList.value = atmArrayList
                        }
                    }
                }
                isLoading.value = false
            }

            override fun onFailure(call: Call<AtmList>, t: Throwable) {
                isLoading.value = false
            }
        })
    }

}