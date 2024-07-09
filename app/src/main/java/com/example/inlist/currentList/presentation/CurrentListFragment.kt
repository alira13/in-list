package com.example.inlist.currentList.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.inlist.databinding.FragmentCurrentListBinding

class CurrentListFragment : Fragment() {

    private var _binding: FragmentCurrentListBinding? = null

    private var listAdapter = ListAdapter()

    private var currentListViewModel: CurrentListViewModel? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentListBinding.inflate(inflater, container, false)
        currentListViewModel = ViewModelProvider(this).get(CurrentListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.activeItemsRv.adapter = listAdapter

        currentListViewModel!!.currentList.observe(viewLifecycleOwner) {
            listAdapter.listItems = it.activeItems.toMutableList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}