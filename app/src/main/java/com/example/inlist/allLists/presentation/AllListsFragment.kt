package com.example.inlist.allLists.presentation

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
import com.example.inlist.currentList.domain.models.CurrentList
import com.example.inlist.currentList.presentation.InListViewModelFactory
import com.example.inlist.databinding.FragmentAllListsBinding
import javax.inject.Inject

class AllListsFragment : Fragment() {

    private var _binding: FragmentAllListsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var activeCurrentListAdapter: AllListsAdapter

    @Inject
    lateinit var viewModelFactory: InListViewModelFactory

    lateinit var viewModel: AllListsViewModel

    val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAllListsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        this.viewModel =
            ViewModelProvider(this, viewModelFactory).get(AllListsViewModel::class.java)
        Log.d("MY_LOG", "${this.viewModel}")

        return root
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
                viewModel.addItem(name)
                hideKeyboard()
            }
            true
        }
    }

    private fun setupRecyclerView() {
        activeCurrentListAdapter = AllListsAdapter()

        binding.activeItemsRv.adapter = activeCurrentListAdapter

        viewModel.state.observe(viewLifecycleOwner) {
            activeCurrentListAdapter.submitList(it)
            activeCurrentListAdapter.onClick = {
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

    fun onClick(listItem: CurrentList) {
        if (activeCurrentListAdapter.currentList.contains(listItem))
            viewModel.deleteItem(listItem.id)
        else viewModel.restoreItem(listItem.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}