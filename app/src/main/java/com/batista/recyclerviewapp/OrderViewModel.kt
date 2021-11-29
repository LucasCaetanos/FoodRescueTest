package com.batista.recyclerviewapp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batista.recyclerviewapp.model.Produto
import com.batista.recyclerviewapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _myGetResponse = MutableLiveData<Response<List<Produto>>>()
    val myResponse: LiveData<Response<List<Produto>>> = _myGetResponse

    private val _myDeleteResponse = MutableLiveData<Response<Produto>>()
    val myDeleteResponse: LiveData<Response<Produto>> = _myDeleteResponse


    val selectedDateLiveData: MutableLiveData<String> = MutableLiveData()

    var produtoSelecionado: Produto? = null

    init {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = formatter.format(Date())
        selectedDateLiveData.postValue(date.toString())
    }

    fun listProduto(){
        viewModelScope.launch {
            val response = repository.listProduto()
            _myGetResponse.value = response
        }
    }

    fun addProduto(produto: Produto){
        viewModelScope.launch {
            val response = repository.addProduto(produto)
        }
    }

    fun updateProduto(produto: Produto){
        viewModelScope.launch {
            val response = repository.updateProduto(produto)
        }
    }

    fun deleteProduto(valor: Int){
        viewModelScope.launch {
            val response = repository.deleteProduto(valor)
            _myDeleteResponse.value = response
        }
    }
}