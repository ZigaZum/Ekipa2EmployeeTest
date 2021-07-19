package com.zumer.ekipa2employeestest.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.zumer.ekipa2employeestest.R
import com.zumer.ekipa2employeestest.databinding.FragmentEmployeesAnalyticsBinding
import com.zumer.ekipa2employeestest.view_model.EmployeesViewModel

class EmployeesAnalyticsFragment : Fragment(R.layout.fragment_employees_analytics) {
    private var _binding: FragmentEmployeesAnalyticsBinding? = null

    private val viewModel: EmployeesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEmployeesAnalyticsBinding.bind(view)
        initViews()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun initViews() {
        viewModel.averageAge.observe(viewLifecycleOwner, { averageAge ->
            averageAge?.let { _binding?.etAverageAge?.setText(it.toString()) }
        })
        viewModel.medianAge.observe(viewLifecycleOwner, { medianAge ->
            medianAge?.let { _binding?.etMedianAge?.setText(it.toString()) }
        })
        viewModel.maxSalary.observe(viewLifecycleOwner, { maxSalary ->
            maxSalary?.let { _binding?.etMaxSalary?.setText(it.toString()) }
        })
        viewModel.maleFemaleRatio.observe(viewLifecycleOwner, { maleFemaleRatio ->
            maleFemaleRatio?.let { _binding?.etMfRatio?.setText(it) }
        })
    }
}