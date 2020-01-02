package de.berlindroid.androidconferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)

        recycler.adapter = ConferenceItemAdapter(
            listOf(
                ConferenceUi("Conference A"),
                ConferenceUi("Conference B"),
                ConferenceUi("Conference C")
            )
        )
    }
}


