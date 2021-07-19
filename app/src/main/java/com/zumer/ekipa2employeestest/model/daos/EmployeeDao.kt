package com.zumer.ekipa2employeestest.model.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zumer.ekipa2employeestest.model.entities.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Insert
    fun insertEmployee(vararg employee: Employee)

    @Query("SELECT * FROM employee")
    fun getAllEmployees(): Flow<List<Employee>>

    @Query("SELECT avg(subquery.Age) FROM  (SELECT strftime('%Y', 'now') - strftime('%Y', birth_date)  as Age FROM employee) subquery")
    fun getAverageAge(): Flow<Double>

    @Query("SELECT avg(subquery.Age) FROM (SELECT strftime('%Y', 'now') - strftime('%Y', birth_date) as Age FROM employee ORDER BY Age LIMIT 2 - (SELECT count(*) FROM employee) % 2 OFFSET (SELECT (count(*) - 1) / 2 FROM employee)) subquery")
    fun getAgeMedian(): Flow<Double>

    @Query("SELECT max(salary) FROM employee")
    fun getMaxSalary(): Flow<Double>

    @Query("SELECT distinct(100.0 * (SELECT count(gender) as Female FROM employee WHERE gender = 'F' ) / (SELECT count(*) FROM employee)) as percentage FROM employee")
    fun getFemalePercentage(): Flow<Double>

    @Query("SELECT count(gender) as Female FROM employee WHERE gender = 'F'")
    fun getNoFemaleWorkers(): Flow<Int>

    @Query("SELECT count(gender) as Female FROM employee WHERE gender = 'M'")
    fun getNoMaleWorkers(): Flow<Int>

    @Query("SELECT distinct((SELECT count(gender) FROM employee WHERE gender = 'M') || '/' || (SELECT count(gender) FROM employee WHERE gender = 'F')) as meleFemaleRatio FROM employee")
    fun getMaleFemaleRatio(): Flow<String>
}