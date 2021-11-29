package com.batista.recyclerviewapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.batista.recyclerviewapp.R
import com.batista.recyclerviewapp.OrderViewModel
import com.batista.recyclerviewapp.model.Produto

class AdapterProduto(private val context: Context,
                     private val orderViewModel: OrderViewModel)
    : RecyclerView.Adapter<AdapterProduto.ProdutoViewHolder>() {

    private var listProduct = emptyList<Produto>()

    class ProdutoViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textNome = view.findViewById<TextView>(R.id.textNomeProduto)
        val textQtd = view.findViewById<TextView>(R.id.textQuantidade)
        val textStatus = view.findViewById<TextView>(R.id.textStatus)
        val textData = view.findViewById<TextView>(R.id.textData)
        val textEdit = view.findViewById<TextView>(R.id.buttonEditar)
        val buttonExcluir = view.findViewById<Button>(R.id.buttonExcluir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val layoutList = LayoutInflater.from(context).inflate(R.layout.fragment_item, parent, false)

        return ProdutoViewHolder(layoutList)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {

        val product = listProduct[position]

        holder.textNome.text = product.nomeProduto
        holder.textData.text = product.data
        holder.textQtd.text = product.quantidade
        holder.textStatus.text = product.status

    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    fun setData(produto: List<Produto>){
        this.listProduct = produto
        notifyDataSetChanged()
    }


}