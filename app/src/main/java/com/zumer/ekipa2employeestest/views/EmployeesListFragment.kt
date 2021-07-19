package com.zumer.ekipa2employeestest.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zumer.ekipa2employeestest.R
import com.zumer.ekipa2employeestest.databinding.FragmentEmployeesListBinding
import com.zumer.ekipa2employeestest.view_model.EmployeesViewModel
import com.zumer.ekipa2employeestest.views.adapters.EmployeesListAdapter

class EmployeesListFragment : Fragment(R.layout.fragment_employees_list) {
    private var _binding: FragmentEmployeesListBinding? = null

    private val viewModel: EmployeesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEmployeesListBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        val adapter = EmployeesListAdapter()
        _binding?.recyclerview?.adapter = adapter
        _binding?.recyclerview?.layoutManager = LinearLayoutManager(activity)

        viewModel.allEmployees.observe(viewLifecycleOwner, Observer { employees ->
            employees?.let { adapter.submitList(it) }
        })
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}