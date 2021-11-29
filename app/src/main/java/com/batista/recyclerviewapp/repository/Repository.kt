package com.batista.recyclerviewapp.repository

import com.batista.recyclerviewapp.api.RetrofitInstance
import com.batista.recyclerviewapp.model.Produto
import retrofit2.Response

class Repository {


    suspend fun listProduto(): Response<List<Produto>> {
        return RetrofitInstance.api.listProduto()
    }

    suspend fun addProduto(produto: Produto): Response<Produto> {
        return RetrofitInstance.api.addProduto(produto)
    }

    suspend fun updateProduto(produto: Produto): Response<Produto> {
        return RetrofitInstance.api.updateProduto(produto)
    }

    suspend fun deleteProduto(valor: Int): Response<Produto> {
        return RetrofitInstance.api.deleteProduto(valor)
    }

}