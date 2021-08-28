package apps.saa.assignment.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import apps.saa.networking.getRetrofitObj
import apps.saa.networking.model.UIInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DynamicFormViewModel: ViewModel() {

    private var _uiInfo = MutableLiveData<UIInfo>()
    val uiInfo: LiveData<UIInfo>
    get() = _uiInfo

    private var _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    init {
        fetchDynamicForm()
    }

    private fun fetchDynamicForm() {
        val call = getRetrofitObj.apiService.fetchDynamicForm()
        call.enqueue(object : Callback<UIInfo>{
            override fun onResponse(call: Call<UIInfo>, response: Response<UIInfo>) {
                if(response.isSuccessful) {
                    val body = response.body()
                    _uiInfo.value = body
                }
            }

            override fun onFailure(call: Call<UIInfo>, t: Throwable) {
                _error.value = true
            }

        })

    }
}