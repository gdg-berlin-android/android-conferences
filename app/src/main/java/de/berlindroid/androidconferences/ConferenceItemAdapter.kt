package de.berlindroid.androidconferences

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ConferenceItemAdapter(
    private val conferenceUis: List<ConferenceUi>
) : RecyclerView.Adapter<ConferenceItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConferenceItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.conference_item, parent, false)

        return ConferenceItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return conferenceUis.size
    }

    override fun onBindViewHolder(holder: ConferenceItemViewHolder, position: Int) {
        val item = conferenceUis[position]
        holder.update(item)
    }
}