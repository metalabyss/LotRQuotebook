package io.farafonova.lotrquotebook

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {
    val character: MutableLiveData<Character> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData("")

    fun findCharacter(characterName: String) = viewModelScope.launch {
        try {
            character.value = repository.getCharacterByName(characterName)
            if (character.value == null) errorMessage.value =
                "No character with name $characterName. Typo?"
        } catch (throwable: Throwable) {
            Log.e(
                CharacterViewModel::class.qualifiedName,
                "${throwable::class.simpleName}: ${throwable.message}"
            )
            errorMessage.value = throwable.message
        }
    }

}

class CharacterViewModelFactory(private val repository: CharacterRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}