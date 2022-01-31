package io.farafonova.lotrquotebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private val viewModel: CharacterViewModel by viewModels {
        CharacterViewModelFactory((application as QuotebookApplication).repository)
    }
    private lateinit var searchBox: EditText
    private lateinit var characterLayout: LinearLayout
    private lateinit var characterNameTextView: TextView
    private lateinit var characterGenderTextView: TextView
    private lateinit var characterRaceTextView: TextView
    private lateinit var characterRealmTextView: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBox = findViewById(R.id.et_search_box)
        characterLayout = findViewById(R.id.layout_character_info)
        characterNameTextView = findViewById(R.id.text_character_name)
        characterGenderTextView = findViewById(R.id.text_character_gender)
        characterRaceTextView = findViewById(R.id.text_character_race)
        characterRealmTextView = findViewById(R.id.text_character_realm)
        progressBar = findViewById(R.id.pb_loading_indicator)

        viewModel.character.observe(
            this,
            {
                progressBar.visibility = View.GONE
                if (it != null) {
                    if (characterLayout.visibility != View.VISIBLE) {
                        characterLayout.visibility = View.VISIBLE
                    }

                    characterNameTextView.text =
                        String.format(getString(R.string.label_character_name), it.name)

                    val gender =
                        if (it.gender.isBlank()) getString(R.string.value_unknown) else it.gender
                    characterGenderTextView.text =
                        String.format(getString(R.string.label_character_gender), gender)

                    val race =
                        if (it.race.isBlank()) getString(R.string.value_unknown) else it.race
                    characterRaceTextView.text =
                        String.format(getString(R.string.label_character_race), race)

                    val realm =
                        if (it.realm.isBlank()) getString(R.string.value_unknown) else it.realm
                    characterRealmTextView.text =
                        String.format(getString(R.string.label_character_realm), realm)
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuItemThatWasSelected: Int = item.itemId
        if (menuItemThatWasSelected == R.id.action_search_character) {
            viewModel.findCharacter(searchBox.text.toString())
            characterLayout.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
        return super.onOptionsItemSelected(item)
    }
}