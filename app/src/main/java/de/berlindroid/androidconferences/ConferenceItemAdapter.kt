package de.berlindroid.androidconferences

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.TextView

class ConferenceItemAdapter(val conference: List<Conference>) : RecyclerView.Adapter<ConferenceItem>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConferenceItem {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.conference_item, parent, false) as TextView
        return ConferenceItem(v)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ConferenceItem, position: Int) {
        val item = conference[position]
        holder.textView.text = item.title

    }
}