package io.farafonova.lotrquotebook

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: CharacterRepository): ViewModel() {
    val character: MutableLiveData<Character> =  MutableLiveData()

    fun findCharacter(characterName: String) = viewModelScope.launch {
        character.value = repository.getCharacterByName(characterName)
    }

}

class CharacterViewModelFactory(private val repository: CharacterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}