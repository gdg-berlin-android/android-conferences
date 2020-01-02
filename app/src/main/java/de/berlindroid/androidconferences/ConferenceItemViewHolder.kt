package de.berlindroid.androidconferences

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class ConferenceUi(
    var title: String
)

class ConferenceItemViewHolder(root: View) : RecyclerView.ViewHolder(root) {
    private val nameText: TextView = root.findViewById(R.id.name)
    private val placeText: TextView = root.findViewById(R.id.place)
    private val dateText: TextView = root.findViewById(R.id.date)
    private val cfpText: TextView = root.findViewById(R.id.callForParticipation)
    private val linkText: TextView = root.findViewById(R.id.link)

    fun update(model: ConferenceUi) {
        nameText.text = model.title
    }
}