package com.example.anmp160420137week4new.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.privacysandbox.tools.core.model.Method
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmp160420137week4new.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Request

class DetailViewModel(application: Application):  AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch(studentId:String) {
        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        studentLD.value = student1
        loadingLD.value = true
        studentLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication()  )
        val url = "http://adv.jitusolution.com/student.php?id=$studentId"
        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Student>>() { }.type
                val result = Gson().fromJson<List<Student>>(it, sType)
                studentLD.value = result as Student?
                loadingLD.value = false

                Log.d("showvoley", result.toString())

            },
            {
                Log.d("showvoley", it.toString())
                studentLoadErrorLD.value = false
                loadingLD.value = false

            }

        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)


    }

}