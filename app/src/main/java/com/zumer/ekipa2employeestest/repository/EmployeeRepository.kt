package com.zumer.ekipa2employeestest.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.zumer.ekipa2employeestest.model.OrganicResult
import com.zumer.ekipa2employeestest.model.SearchResponse
import com.zumer.ekipa2employeestest.model.daos.EmployeeDao
import com.zumer.ekipa2employeestest.model.entities.Employee
import kotlinx.coroutines.flow.Flow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeRepository(private val employeeDao: EmployeeDao) {
    val allEmployees: Flow<List<Employee>> = employeeDao.getAllEmployees()

    val averageAge: Flow<Double> = employeeDao.getAverageAge()

    val medianAge: Flow<Double> = employeeDao.getAgeMedian()

    val maxSalary: Flow<Double> = employeeDao.getMaxSalary()

    val femalePercentage: Flow<Double> = employeeDao.getFemalePercentage()

    val femaleWorkers: Flow<Int> = employeeDao.getNoFemaleWorkers()

    val maleWorkers: Flow<Int> = employeeDao.getNoMaleWorkers()

    val maleFemaleRatio: Flow<String> = employeeDao.getMaleFemaleRatio()

    val searchHits: MutableLiveData<List<OrganicResult>> = MutableLiveData()

    fun getSearchHits(name: String) {

        val apiInterface = ApiClient.apiService.getHitsResults(name, 6, "bac9e8bb0a1fbec75094a3d25f765a1b1533b8e96e2a079c1b7d86c47003151f", "mobile")
        apiInterface.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if(response.isSuccessful){
                    searchHits.value = response.body()?.organicResults
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                var temp = t
            }

        })
    }

    val searchHitsTwo: MutableLiveData<String> = MutableLiveData()

    fun getSearchHitsTwo(name: String) {
        val stringBuilder = StringBuilder()
        val doc: Document = Jsoup.connect("https://www.google.com/search?q=$name").get()
        val headers: Elements = doc.select("h3")
        for (i in 0..4) {
            stringBuilder.append("\n").append(headers[i].text())
        }
        searchHitsTwo.postValue(stringBuilder.toString())
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(employee: Employee) {
        employeeDao.insertEmployee(employee)
    }
}