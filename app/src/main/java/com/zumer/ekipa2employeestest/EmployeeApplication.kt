package com.zumer.ekipa2employeestest

import android.app.Application
import com.zumer.ekipa2employeestest.model.EmployeeDatabase
import com.zumer.ekipa2employeestest.repository.EmployeeRepository

class EmployeeApplication : Application() {
    val database by lazy { EmployeeDatabase.getDbInstance(this) }
    val repository by lazy { EmployeeRepository(database.employeeDao()) }
}