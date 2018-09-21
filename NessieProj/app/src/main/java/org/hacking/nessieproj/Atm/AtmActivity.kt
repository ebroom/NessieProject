package org.hacking.nessieproj.atm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_atm.*
import org.hacking.nessieproj.DividerLineComponent
import org.hacking.nessieproj.R

class AtmActivity : AppCompatActivity() {

    private lateinit var viewmodel: AtmViewModel
    private lateinit var invalidInputTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atm)
        viewmodel = ViewModelProviders.of(this).get(AtmViewModel::class.java)
        setupInvalidTextView()
        setupRecyclerView()
        setListeners()
        setObservers()

    }

    private fun setupInvalidTextView() {
        invalidInputTextView = TextView(this)
        invalidInputTextView.text = getString(R.string.atm_invalid_input)
        invalidInputTextView.setPadding(8, 8, 8, 8)
        invalidInputTextView.textSize = 18.0f
        invalidInputTextView.setTextColor(Color.RED)
        invalidInputTextView.gravity = Gravity.CENTER_HORIZONTAL
    }

    private fun setListeners() {
        get_atms_btn.setOnClickListener {
            if (lat.text.toString().isEmpty() || lng.text.toString().isEmpty() || radius.text.toString().isEmpty()) {
                if (atm_layout.indexOfChild(invalidInputTextView) == -1) {
                    invalidInputTextView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT)
                    atm_layout.addView(invalidInputTextView)
                }
            } else {
                if (atm_layout.indexOfChild(invalidInputTextView) != -1) {
                    atm_layout.removeView(invalidInputTextView)
                }
                viewmodel.getAtms(lat.text.toString().toDouble(), lng.text.toString().toDouble(), radius.text.toString().toInt())
            }
        }
        atm_recycler_view.addItemDecoration(DividerLineComponent(this@AtmActivity))
        atm_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager: LinearLayoutManager = atm_recycler_view.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                val isAtEnd: Boolean = lastVisible + 3 >= totalItemCount
                if (totalItemCount > 0 && isAtEnd && !viewmodel.isLoading.value!!) {
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