package com.batista.recyclerviewapp.adapter

import com.batista.recyclerviewapp.model.Produto

interface ProdutoClickListener {

    fun onTaskClicked(produto: Produto)
}