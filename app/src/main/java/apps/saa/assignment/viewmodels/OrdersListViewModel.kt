package apps.saa.assignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import apps.saa.networking.getRetrofitObj
import apps.saa.networking.model.OrdersInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersListViewModel: ViewModel() {

    private var _ordersInfo = MutableLiveData<OrdersInfo>()
    val ordersInfo: LiveData<OrdersInfo>
    get() = _ordersInfo

    private var _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    init {
        fetchOrders()
    }

    private fun fetchOrders() {
        val call = getRetrofitObj.apiService.fetchOrders()
        call.enqueue(object : Callback<OrdersInfo>{
            override fun onResponse(call: Call<OrdersInfo>, response: Response<OrdersInfo>) {
                if(response.isSuccessful) {
                    val body = response.body()
                    _ordersInfo.value = body
                }
            }

            override fun onFailure(call: Call<OrdersInfo>, t: Throwable) {
                _error.value = true
            }

        })
    }
}