package it.flowing.app.ui
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import it.flowing.app.models.Content

@BindingAdapter("srcUrl")
fun bindSrcUrl (imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .into(imageView)
    }
}

@BindingAdapter("loadingStatus")
fun bindVisibilityOnLoading (view: View, status: OverviewViewModel.ContentApiStatus?) {
    view.visibility = when(status) {
        OverviewViewModel.ContentApiStatus.LOADING -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("contentsList")
fun bindingContentsList (recyclerView: RecyclerView, data: List<Content>?) {
    val adapter = recyclerView.adapter as ContentsAdapter
    adapter.submitList(data)
}

@BindingAdapter("htmlText")
fun bindingHtmlText (textView: TextView, html: String?) {
    textView.text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        else Html.fromHtml(html)
}