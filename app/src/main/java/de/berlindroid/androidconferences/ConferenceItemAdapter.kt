package de.berlindroid.androidconferences

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

class ConferenceItemAdapter(
    private val conferences: List<Conference>
) : RecyclerView.Adapter<ConferenceItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConferenceItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.conference_item, parent, false) as LinearLayout

        return ConferenceItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return conferences.size
    }

    override fun onBindViewHolder(holder: ConferenceItemViewHolder, position: Int) {
        val item = conferences[position]
        holder.update(item)
    }
}