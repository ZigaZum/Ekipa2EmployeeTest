package com.zumer.ekipa2employeestest.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zumer.ekipa2employeestest.R
import com.zumer.ekipa2employeestest.model.OrganicResult

class EmployeesSearchHitsAdapter : ListAdapter<OrganicResult, EmployeesSearchHitsAdapter.EmployeeSearchHitsViewHolder>(SearchHitsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeSearchHitsViewHolder {
        return EmployeeSearchHitsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EmployeeSearchHitsViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.title)
    }

    class EmployeeSearchHitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)

        fun bind(text: String?) {
            tvName.text = text
        }

        companion object {
            fun create(parent: ViewGroup): EmployeeSearchHitsViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return EmployeeSearchHitsViewHolder(view)
            }
        }
    }

    class SearchHitsComparator : DiffUtil.ItemCallback<OrganicResult>() {
        override fun areItemsTheSame(oldItem: OrganicResult, newItem: OrganicResult): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: OrganicResult, newItem: OrganicResult): Boolean {
            return oldItem.title == newItem.title
        }

    }
}