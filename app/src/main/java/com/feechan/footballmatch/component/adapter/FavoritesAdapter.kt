package com.feechan.footballmatch.component.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.feechan.footballmatch.component.anko.EventUI
import com.feechan.footballmatch.data.model.EventHolder
import com.feechan.footballmatch.data.network.response.Event
import org.jetbrains.anko.AnkoContext

class FavoritesAdapter (private val events: List<Event>, private val listener: (Event) -> Unit)
    : RecyclerView.Adapter<EventHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        return EventHolder(EventUI().createView(AnkoContext.create(parent.context,parent)))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }
}