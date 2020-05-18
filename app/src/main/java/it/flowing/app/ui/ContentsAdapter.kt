package it.flowing.app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import it.flowing.app.databinding.ContentListItemBinding
import it.flowing.app.models.Content

class ContentsAdapter(): ListAdapter<Content, ContentsAdapter.ContentViewHolder>(DiffCallback) {
    companion object DiffCallback: DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean = oldItem === newItem
        override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean = oldItem.id == newItem.id
    }

    class ContentViewHolder(private var binding: ContentListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(content: Content) {
            binding.content = content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContentViewHolder(ContentListItemBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}