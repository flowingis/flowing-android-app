package it.flowing.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.flowing.app.R
import it.flowing.app.network.Category
import it.flowing.app.network.Category.BLOG
import it.flowing.app.network.FlowingApi
import it.flowing.app.network.FlowingApiService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}
