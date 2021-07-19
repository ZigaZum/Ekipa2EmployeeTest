package com.zumer.ekipa2employeestest.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zumer.ekipa2employeestest.R
import com.zumer.ekipa2employeestest.model.entities.Employee

class EmployeesListAdapter : ListAdapter<Employee, EmployeesListAdapter.EmployeeViewHolder>(EmployeeComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)

        fun bind(text: String?) {
            tvName.text = text
        }

        companion object {
            fun create(parent: ViewGroup): EmployeeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return EmployeeViewHolder(view)
            }
        }
    }

    class EmployeeComparator : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.name == newItem.name
        }
    }
}