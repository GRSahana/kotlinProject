package com.nannaapp.kotlin

import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nannaapp.kotlin.Adapter.ListItemAdapter
import com.nannaapp.kotlin.Model.ListItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creates a vertical Layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)
        getAllData();

    }

    private fun getAllData() {
        val model: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        model.getHeroes().observe(this,  Observer{ itemList ->
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility = View.GONE
            // Access the RecyclerView Adapter and load the data into it
            recyclerview.adapter = ListItemAdapter(this, itemList)
        })
    }

    override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmer()
    }

//    private fun getAllData() {
//        disposable =
//            retrofitService.getJsonData()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    { result  -> showResult(result) },
//                    { error -> showError(error.message) }
//                )
//    }
//
//    private fun showError(message: String?) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//    }
//
//    private fun showResult(result: ArrayList<ListItem>) {
//        shimmer_view_container.stopShimmer()
//        shimmer_view_container.visibility = View.GONE
//        // Access the RecyclerView Adapter and load the data into it
//        recyclerview.adapter = ListItemAdapter(this, result)
//    }

    override fun onPause() {
        super.onPause()
//        disposable?.dispose()
        shimmer_view_container.stopShimmer()
    }
}