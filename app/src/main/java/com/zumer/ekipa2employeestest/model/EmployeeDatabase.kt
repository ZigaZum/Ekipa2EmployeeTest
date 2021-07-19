package com.zumer.ekipa2employeestest.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zumer.ekipa2employeestest.model.daos.EmployeeDao
import com.zumer.ekipa2employeestest.model.entities.Employee

@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase(){
    abstract fun employeeDao(): EmployeeDao

    companion object {
        private var INSTANCE: EmployeeDatabase? = null

        fun getDbInstance(context: Context): EmployeeDatabase {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, EmployeeDatabase::class.java, "EMPLOYEE_DATABASE").build()
            }
            return INSTANCE!!
        }
    }
}