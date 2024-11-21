package com.example.inlist.currentList.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.inlist.app.App
import com.example.inlist.currentList.domain.models.ListItem
import com.example.inlist.databinding.FragmentCurrentListBinding
import javax.inject.Inject


class CurrentListFragment : Fragment() {

    private var _binding: FragmentCurrentListBinding? = null

    private lateinit var activeCurrentListAdapter:CurrentListAdapter
    private lateinit var deletedCurrentListAdapter:CurrentListAdapter

    @Inject
    lateinit var viewModelFactory: InListViewModelFactory

    lateinit var currentListViewModel: CurrentListViewModel

    private val component by lazy {
        (requireActivity().application as App).component
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentListBinding.inflate(inflater, container, false)
        currentListViewModel =
            ViewModelProvider(this, viewModelFactory).get(CurrentListViewModel::class.java)
        Log.d("MY_LOG", "$currentListViewModel")
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.addItemBtn.setOnClickListener {
            showEditText()
            showKeyboard()
        }

        binding.addItemEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val name = binding.addItemEt.text.toString()
                currentListViewModel.addItem(name)
                hideKeyboard()
            }
            true
        }
    }

    private fun setupRecyclerView() {
        activeCurrentListAdapter= CurrentListAdapter()
        deletedCurrentListAdapter = CurrentListAdapter()

        binding.activeItemsRv.adapter = activeCurrentListAdapter
        binding.deletedItemsRv.adapter = deletedCurrentListAdapter

        currentListViewModel.state.observe(viewLifecycleOwner) {
            activeCurrentListAdapter.submitList(it.items.filter { items -> items.isEnabled }
                .toMutableList())
            deletedCurrentListAdapter.submitList(it.items.filter { items -> !items.isEnabled }
                .toMutableList())

            activeCurrentListAdapter.onClick={
                onClick(it)
            }
            deletedCurrentListAdapter.onClick={
                onClick(it)
            }
        }
    }

    private fun showKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as? InputMethodManager
        //Показываем клавиатуру
        inputMethodManager?.showSoftInput(binding.addItemEt, 0)
        //Когда клавиатуру видно, кнопка должна быть скрыта
        binding.addItemBtn.isVisible = false
        binding.currentListBackgroundV.isVisible = true
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
        binding.currentListBackgroundV.isVisible = false
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

    fun onClick(listItem: ListItem) {
        if (activeCurrentListAdapter.currentList.contains(listItem))
            currentListViewModel.deleteItem(listItem.id)
        else currentListViewModel.restoreItem(listItem.id)
    }
}