package com.example.inlist.currentList.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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

    private var listItem: ListItem? = null

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

        binding.addItemBtn.setOnClickListener {
            showEditText()
            showKeyboard()
        }

        binding.addItemEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val name = binding.addItemEt.text.toString()
                addNewItem(name)
                hideKeyboard()
            }
            true
        }
    }

    private fun addNewItem(name: String) {
        if (name.isNotEmpty()) {
            listItem = ListItem(name)
            currentListViewModel!!.addToList(listItem!!)
            listItem = null
        }
    }

    private fun showKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as? InputMethodManager
        //Показываем клавиатуру
        inputMethodManager?.showSoftInput(binding.addItemEt, 0)
        //Когда клавиатуру видно, кнопка должна быть скрыта
        binding.addItemBtn.isVisible = false
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as? InputMethodManager
        //Скрываем клавиатуру
        inputMethodManager?.hideSoftInputFromWindow(
            binding.addItemEt.windowToken, 0
        )
        //Показываем кнопку Добавить
        binding.addItemBtn.isVisible = true
        hideEditText()
    }

    private fun showEditText() {
        binding.addItemEt.isVisible = true
        binding.addItemEt.requestFocus()
    }

    private fun hideEditText() {
        binding.addItemEt.clearFocus()
        binding.addItemEt.isCursorVisible = false
        binding.addItemEt.isVisible = false
        binding.addItemEt.setText("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}