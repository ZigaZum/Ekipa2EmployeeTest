package com.zumer.ekipa2employeestest.views

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.zumer.ekipa2employeestest.R
import com.zumer.ekipa2employeestest.databinding.FragmentAddEmployeeBinding
import com.zumer.ekipa2employeestest.model.entities.Employee
import com.zumer.ekipa2employeestest.utils.Gender
import com.zumer.ekipa2employeestest.view_model.EmployeesViewModel
import org.joda.time.LocalDate


class AddEmployeeFragment : Fragment(R.layout.fragment_add_employee), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentAddEmployeeBinding? = null

    private val viewModel: EmployeesViewModel by activityViewModels()

    private var gender: Gender = Gender.FEMALE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddEmployeeBinding.bind(view)
        initViews()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun initViews() {
        _binding?.spinGender?.adapter = ArrayAdapter<Gender>(requireContext(), android.R.layout.simple_spinner_item, Gender.values())
        _binding?.spinGender?.onItemSelectedListener = this
        _binding?.etBirthDate?.setOnClickListener {
            showDatePicker()
        }
        _binding?.btAddEmployee?.setOnClickListener {
            if (_binding?.etName?.text.toString().isBlank() || _binding?.etBirthDate?.text.toString().isBlank() || _binding?.etSalary?.text.toString().isBlank()) {
                val snackbar = Snackbar.make(
                    _binding?.etName!!,
                    "All fields are required",
                    Snackbar.LENGTH_LONG
                )
                val view = snackbar.view
                view.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.design_default_color_error
                    )
                )
                snackbar.show()
            } else {
                val newEmployee = Employee(
                    null,
                    _binding?.etName?.text.toString(),
                    _binding?.etBirthDate?.text.toString(),
                    gender.code,
                    _binding?.etSalary?.text.toString().toDouble()
                )
                viewModel.insert(newEmployee)
            }
        }
    }

    private fun showDatePicker() {
        val startingDate = LocalDate.now()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth -> _binding?.etBirthDate?.setText(LocalDate(year, monthOfYear + 1, dayOfMonth).toString()) },
            startingDate.year,
            startingDate.monthOfYear - 1,
            startingDate.dayOfMonth
        )
        datePickerDialog.show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        gender = parent?.getItemAtPosition(position) as Gender
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        gender = Gender.FEMALE
    }
}