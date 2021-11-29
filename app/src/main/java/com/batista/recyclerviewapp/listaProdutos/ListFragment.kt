package com.batista.recyclerviewapp.listaProdutos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.batista.recyclerviewapp.OrderViewModel
import com.batista.recyclerviewapp.R
import com.batista.recyclerviewapp.adapter.AdapterProduto
import com.batista.recyclerviewapp.adapter.ProdutoClickListener
import com.batista.recyclerviewapp.databinding.FragmentItemListBinding
import com.batista.recyclerviewapp.model.Produto


class ListFragment : Fragment(), ProdutoClickListener {

    val orderViewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)

        val adapter = AdapterProduto(requireContext(),orderViewModel)
        binding.fragmentProdutos.layoutManager = LinearLayoutManager(context)
        binding.fragmentProdutos.adapter = adapter
        binding.fragmentProdutos.setHasFixedSize(true)

        orderViewModel.listProduto()
        orderViewModel.myResponse.observe(viewLifecycleOwner, {
            response ->
            response.body()?.let { adapter.setData(it) }
        })

        orderViewModel.myDeleteResponse.observe(viewLifecycleOwner, {
            orderViewModel.listProduto()
            Toast.makeText(
                context, "Tarefa deletada!",
                Toast.LENGTH_LONG
            ).show()
        })

        binding.floatingActionButton.setOnClickListener {
            orderViewModel.produtoSelecionado = null
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }
        return binding.root
    }

    override fun onTaskClicked(produto: Produto) {
        orderViewModel.produtoSelecionado = produto
        findNavController().navigate(R.id.action_listFragment_to_formFragment)
    }

}