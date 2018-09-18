package org.hacking.nessieproj

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_atm.*

class AtmActivity : AppCompatActivity() {

    private lateinit var viewmodel : AtmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atm)
        viewmodel = ViewModelProviders.of(this).get(AtmViewModel::class.java)
        setupRecyclerView()
        setListeners()
        setObservers()

    }

    private fun setListeners() {
        get_atms_btn.setOnClickListener {
            viewmodel.getAtms(lat.text.toString().toDouble(), lng.text.toString().toDouble(), radius.text.toString().toInt(), 1)
        }
        atm_recycler_view.addItemDecoration(DividerLineComponent(this@AtmActivity))
        atm_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager : LinearLayoutManager = atm_recycler_view.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                val isAtEnd : Boolean = lastVisible + 1 >= totalItemCount
                if (totalItemCount > 0 && isAtEnd && !viewmodel.isLoading.value!!) {
//                    Toast.makeText(this@AtmActivity, "hello", Toast.LENGTH_LONG).show()
                    viewmodel.paging.value?.next?.let {
                        viewmodel.getNextPage(it)
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        val adapter = AtmListAdapter(this, viewmodel.atmList.value)
        val layoutManager = LinearLayoutManager(this)
        atm_recycler_view.layoutManager = layoutManager
        atm_recycler_view.adapter = adapter
    }

    private fun setObservers() {
        viewmodel.atmList.observe(this, Observer {
            it?.let {
                (atm_recycler_view.adapter as AtmListAdapter).addData(it)
            }
        })
    }
}