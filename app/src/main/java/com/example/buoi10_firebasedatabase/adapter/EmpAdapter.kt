package com.example.buoi10_firebasedatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buoi10_firebasedatabase.EmployeeModel
import com.example.buoi10_firebasedatabase.R
import kotlinx.android.synthetic.main.emp_list_item.view.*

class EmpAdapter (private val ds:ArrayList<EmployeeModel>) : RecyclerView.Adapter<EmpAdapter.ViewHolder>() {
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener) {
        mListener = clickListener
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.emp_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            tvEmpName.text = ds[position].empName
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
}