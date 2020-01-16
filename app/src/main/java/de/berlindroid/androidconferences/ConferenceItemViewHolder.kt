package de.berlindroid.androidconferences

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


data class ConferenceUi(
    val title: String,
    val link: String,
    val place: String,
    val date: String,
    val cfpLink: String?
)

class ConferenceItemViewHolder(root: View) : RecyclerView.ViewHolder(root) {
    private val nameText: TextView = root.findViewById(R.id.name)
    private val placeText: TextView = root.findViewById(R.id.place)
    private val dateText: TextView = root.findViewById(R.id.date)
    private val cfp: TextView = root.findViewById(R.id.callForParticipation)

    fun update(model: ConferenceUi) {
        nameText.text = model.title
        placeText.text = model.place
        dateText.text = model.date

        cfp.visibility = if (model.cfpLink.isNullOrBlank()) View.GONE else View.VISIBLE

        itemView.setOnClickListener {
            if (model.cfpLink.isNullOrBlank()) {
                openLink(itemView.context, model.link)
            } else {
                askForCFPOrConferenceLink(itemView.context, model)
            }
        }
    }

    private fun askForCFPOrConferenceLink(context: Context, model: ConferenceUi) {
        AlertDialog
            .Builder(context)
            .setMessage(R.string.cfp_or_conference_dialog_message)
            .setPositiveButton(R.string.cfp_or_conference_dialog_cfp_button) { _, _ ->
                if (model.cfpLink != null) {
                    openLink(
                        context,
                        model.cfpLink
                    )
                }
            }.setNegativeButton(R.string.cfp_or_conference_dialog_conference_button) { _, _ ->
                openLink(context, model.link)
            }
            .show()
    }

    private fun openLink(context: Context, link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(context, intent, null)
    }
}