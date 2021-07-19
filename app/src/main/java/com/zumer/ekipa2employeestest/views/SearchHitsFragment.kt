package com.zumer.ekipa2employeestest.views

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.zumer.ekipa2employeestest.R
import com.zumer.ekipa2employeestest.databinding.FragmentSearchHitsBinding
import com.zumer.ekipa2employeestest.model.entities.Employee
import com.zumer.ekipa2employeestest.view_model.EmployeesViewModel
import com.zumer.ekipa2employeestest.views.adapters.EmployeesSearchHitsAdapter

class SearchHitsFragment : Fragment(R.layout.fragment_search_hits), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentSearchHitsBinding? = null

    private val viewModel: EmployeesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchHitsBinding.bind(view)
        initViews()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun initViews() {
        val adapter = EmployeesSearchHitsAdapter()
        _binding?.recyclerview?.adapter = adapter
        _binding?.recyclerview?.layoutManager = LinearLayoutManager(activity)

        viewModel.hitsByName.observe(viewLifecycleOwner, { hits ->
            hits?.let {
                adapter.submitList(it)
            }
        })

        viewModel.hitsByNameTwo.observe(viewLifecycleOwner, { hit ->
            hit?.let {
                _binding?.tvSecondSearch?.text = it
            }
        })

        viewModel.allEmployees.observe(viewLifecycleOwner, { employees ->
            employees?.let {
                _binding?.spinEmployees?.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it)
            }
        })
        _binding?.spinEmployees?.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)?.let {
            viewModel.getSearchHitsByName((it as Employee).name)
            viewModel.getSearchHitsByNameTwo(it.name)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}