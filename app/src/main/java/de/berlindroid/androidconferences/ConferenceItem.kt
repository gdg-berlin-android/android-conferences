package de.berlindroid.androidconferences

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Conference(var title:String)

val conference = listOf<Conference>(Conference("Conference A"), Conference("Conference B"), Conference("Conference C"))

class ConferenceItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView = itemView.findViewById<TextView>(R.id.textView)
}