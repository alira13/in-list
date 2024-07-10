package com.example.inlist.currentList.presentation

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.inlist.currentList.domain.models.ListItem
import com.example.inlist.databinding.FragmentCurrentListBinding

class CurrentListFragment : Fragment() {

    private var _binding: FragmentCurrentListBinding? = null

    private var listAdapter = ListAdapter()

    private var currentListViewModel: CurrentListViewModel? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var listItem = ListItem("")

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

        currentListViewModel!!.state.observe(viewLifecycleOwner) {
            listAdapter.listItems = it.activeItems.toMutableList()
        }

        binding.addItemEt.doAfterTextChanged { text: Editable? ->
            val inputMethodManager =
                activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as? InputMethodManager
            if (text.isNullOrEmpty()) {
                inputMethodManager?.hideSoftInputFromWindow(
                    binding.addItemEt.windowToken, 0
                )
            } else {
                inputMethodManager?.showSoftInput(binding.addItemEt, 0)
                listItem = ListItem(binding.addItemEt.text.toString())
            }
            binding.addItemEt.clearFocus()
            binding.addItemEt.isCursorVisible = false
        }

        binding.addItemBtn.setOnClickListener {
            if (listItem.name.isNotEmpty())
                currentListViewModel!!.addToList(listItem)
            binding.addItemEt.setText("")
            listItem = ListItem("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}