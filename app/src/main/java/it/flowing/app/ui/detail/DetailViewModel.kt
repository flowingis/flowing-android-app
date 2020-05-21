package it.flowing.app.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.flowing.app.models.Content

class DetailViewModel : ViewModel() {
    val content: MutableLiveData<Content> = MutableLiveData()
}
