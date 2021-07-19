package com.zumer.ekipa2employeestest.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val birth_date: String,
    val gender: String,
    val salary: Double
) {
    override fun toString(): String = name
}
