package it.flowing.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.flowing.app.models.Content
import it.flowing.app.models.Page
import it.flowing.app.network.Category
import it.flowing.app.network.FlowingApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

const val PAGE_SIZE = 8

class OverviewViewModel : ViewModel() {
    enum class ContentApiStatus { LOADING, DONE, ERROR }

    private val _status = MutableLiveData<ContentApiStatus>()
    val status: LiveData<ContentApiStatus>
        get() = _status

    private val _contents = MutableLiveData<List<Content>>()
    val contents: LiveData<List<Content>>
        get() = _contents

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getContents()
    }

    private fun getContents() = coroutineScope.launch {
        val getContentDeferred = FlowingApi.getContents(Category.BLOG, perPage = PAGE_SIZE, page = 1)
        try {
            _status.value = ContentApiStatus.LOADING
            val page = getContentDeferred.await()
            _contents.value = page.items
            _status.value = ContentApiStatus.DONE
        } catch (e: Exception) {
            _status.value = ContentApiStatus.ERROR
        }
    }
}
