package com.example.apps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apps.models.Hostel

class HostelAdapter(private var hostelList: List<Hostel>) :
    RecyclerView.Adapter<HostelAdapter.HostelViewHolder>() {

    class HostelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvHostelName)
        val location: TextView = view.findViewById(R.id.tvHostelLocation)
        val price: TextView = view.findViewById(R.id.tvHostelPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hostel, parent, false)
        return HostelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HostelViewHolder, position: Int) {
        val hostel = hostelList[position]
        holder.name.text = hostel.name
        holder.location.text = hostel.location
        holder.price.text = "Price: KES ${hostel.price}"
    }

    override fun getItemCount(): Int {
        return hostelList.size
    }

    // Update list when search or data changes
    fun updateList(newList: List<com.example.apps.models.Hostel>) {
        hostelList = newList
        notifyDataSetChanged()
    }
}