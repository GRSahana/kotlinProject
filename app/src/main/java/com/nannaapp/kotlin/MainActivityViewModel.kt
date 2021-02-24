package com.nannaapp.kotlin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nannaapp.kotlin.Model.ListItem
import com.nannaapp.kotlin.Retrofit.RetrofitService
import io.reactivex.disposables.Disposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityViewModel : ViewModel() {

    var listItem = MutableLiveData<ArrayList<ListItem>>()

    val retrofitService by lazy {
        RetrofitService.create()
    }

    //we will call this method to get the data
    fun getHeroes(): LiveData<ArrayList<ListItem>> {
        //if the list is null
        //we will load it asynchronously from server in this method
        loadListItems()

        //finally we will return the list
        return listItem
    }

    private fun loadListItems() {
        val call: Call<ArrayList<ListItem>> = retrofitService.getJsonData()

        call.enqueue(object : Callback<ArrayList<ListItem>> {
            override fun onResponse(call: Call<ArrayList<ListItem>>, response: Response<ArrayList<ListItem>>) {

                //finally we are setting the list to our MutableLiveData
                listItem.setValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<ListItem>>, t: Throwable?) {
                //Toast.makeText(this, "Error ", Toast.LENGTH_LONG).show()
                Log.d("Error", t?.printStackTrace().toString())
            }
        })
    }


}