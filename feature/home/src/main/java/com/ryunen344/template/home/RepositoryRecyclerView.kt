package com.ryunen344.template.home

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ryunen344.template.model.Repository
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class RepositoryRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    var selectionTracker: SelectionTracker<Long>

    private val groupAdapter: GroupAdapter<GroupieViewHolder> by lazy { GroupAdapter<GroupieViewHolder>() }

    init {
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        groupAdapter.setHasStableIds(true)
        adapter = groupAdapter

        selectionTracker = SelectionTracker.Builder<Long>(
            "repository-selection-id",
            this,
            RepositoryIdKeyProvider(this),
            RepositoryLookup(this),
            StorageStrategy.createLongStorage()
        ).build()
    }

    var repositories: List<Repository>? = null
        set(value) {
            field = value
            setContent()
        }

    private fun setContent() {
        val repositories = repositories ?: return

        groupAdapter.clear()
        groupAdapter.addAll(repositories.map { RepositoryItem(selectionTracker, it) })
    }

    inner class RepositoryIdKeyProvider(private val repositoryRecyclerView: RepositoryRecyclerView) :
        ItemKeyProvider<Long>(SCOPE_MAPPED) {

        override fun getKey(position: Int): Long? = repositoryRecyclerView.groupAdapter.getItem(position).id

        override fun getPosition(key: Long): Int =
            repositoryRecyclerView.findViewHolderForItemId(key)?.adapterPosition ?: 0
    }

    inner class RepositoryLookup(private val repositoryRecyclerView: RepositoryRecyclerView) :
        ItemDetailsLookup<Long>() {
        override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? =
            repositoryRecyclerView.findChildViewUnder(e.x, e.y)?.let {
                val selectionKey = repositoryRecyclerView.getChildItemId(it)
                val position = repositoryRecyclerView.getChildLayoutPosition(it)

                return@let object : ItemDetailsLookup.ItemDetails<Long>() {
                    override fun getSelectionKey(): Long? = selectionKey

                    override fun getPosition(): Int = position
                }
            }
    }
}
