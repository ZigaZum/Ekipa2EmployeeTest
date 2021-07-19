package com.zumer.ekipa2employeestest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.zumer.ekipa2employeestest.databinding.ActivityMainBinding
import com.zumer.ekipa2employeestest.view_model.EmployeeViewModelFactory
import com.zumer.ekipa2employeestest.view_model.EmployeesViewModel
import com.zumer.ekipa2employeestest.views.AddEmployeeFragment
import com.zumer.ekipa2employeestest.views.EmployeesAnalyticsFragment
import com.zumer.ekipa2employeestest.views.EmployeesListFragment
import com.zumer.ekipa2employeestest.views.SearchHitsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val model: EmployeesViewModel by viewModels {
        EmployeeViewModelFactory((application as EmployeeApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        model.run {
            val employeesListFragment = EmployeesListFragment()
            val addEmployeeFragment = AddEmployeeFragment()
            val employeesAnalyticsFragment = EmployeesAnalyticsFragment()
            val searchHitsFragment = SearchHitsFragment()

            setCurrentFragment(employeesListFragment)

            binding.bottomNavigationView.setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.employees_list -> setCurrentFragment(employeesListFragment)
                    R.id.add_employee -> setCurrentFragment(addEmployeeFragment)
                    R.id.employees_analytics -> setCurrentFragment(employeesAnalyticsFragment)
                    R.id.employee_search_hits -> setCurrentFragment(searchHitsFragment)
                }
                true
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}