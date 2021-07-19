package com.zumer.ekipa2employeestest.view_model

import androidx.lifecycle.*
import com.zumer.ekipa2employeestest.model.OrganicResult
import com.zumer.ekipa2employeestest.model.entities.Employee
import com.zumer.ekipa2employeestest.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeesViewModel(private val repository: EmployeeRepository) : ViewModel() {
    val allEmployees: LiveData<List<Employee>> = repository.allEmployees.asLiveData()

    val averageAge: LiveData<Double> = repository.averageAge.asLiveData()

    val medianAge: LiveData<Double> = repository.medianAge.asLiveData()

    val maxSalary: LiveData<Double> = repository.maxSalary.asLiveData()

    val femalePercentage: LiveData<Double> = repository.femalePercentage.asLiveData()

    val femaleWorkers: LiveData<Int> = repository.femaleWorkers.asLiveData()

    val maleWorkers: LiveData<Int> = repository.maleWorkers.asLiveData()

    val maleFemaleRatio: LiveData<String> = repository.maleFemaleRatio.asLiveData()

    fun insert(employee: Employee) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(employee)
    }

    var hitsByName: LiveData<List<OrganicResult>> = repository.searchHits

    fun getSearchHitsByName(name: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.getSearchHits(name)
    }

    var hitsByNameTwo: LiveData<String> = repository.searchHitsTwo

    fun getSearchHitsByNameTwo(name: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.getSearchHitsTwo(name)
    }
}

class EmployeeViewModelFactory(private val repository: EmployeeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmployeesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}