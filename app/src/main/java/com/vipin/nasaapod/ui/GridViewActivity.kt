package com.vipin.nasaapod.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vipin.nasaapod.ApodApplication
import com.vipin.nasaapod.R
import com.vipin.nasaapod.di.ApodVMFactory
import com.vipin.nasaapod.util.DateRange
import com.vipin.nasaapod.viewmodel.MultiGridViewModel
import org.joda.time.LocalDate

/**
 * Created by vipin.c on 17/09/2019
 */
class GridViewActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gridViewAdapter: GridViewAdapter
    private lateinit var viewModel: MultiGridViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_layout)

        recyclerView = findViewById(R.id.recycler_view)
        gridViewAdapter = GridViewAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = gridViewAdapter

        viewModel = ViewModelProviders.of(this, ApodVMFactory(application as ApodApplication))
            .get(MultiGridViewModel::class.java)
        val today = LocalDate.now()
        viewModel.getApodList(DateRange((today.minusDays(30)), today)).observe(this, Observer {
            it?.let {

                Log.e("GridViewActivity : $it", " ")
                gridViewAdapter.apodList(it)
            }
        })
    }
}