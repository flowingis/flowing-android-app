package it.flowing.app.ui

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import it.flowing.app.databinding.ContentListItemBinding
import it.flowing.app.models.Content

class ContentsAdapter(private val onSelect: (selection: Content, extras: FragmentNavigator.Extras) -> Unit): ListAdapter<Content, ContentsAdapter.ContentViewHolder>(DiffCallback) {
    companion object DiffCallback: DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean = oldItem === newItem
        override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean = oldItem.id == newItem.id
    }

    class ContentViewHolder(private var binding: ContentListItemBinding, val onSelect: (selection: Content, extras: FragmentNavigator.Extras) -> Unit): RecyclerView.ViewHolder(binding.root) {
        fun bind(content: Content) = binding.apply {
            this.content = content
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.transitionName = "featureImage_${content.id}"
            }
            cardView.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    imageView to "featureImage_${content.id}"
                )
                onSelect(content, extras)
            }
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContentViewHolder(ContentListItemBinding.inflate(inflater), onSelect)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}