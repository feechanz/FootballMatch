package com.feechan.footballmatch.presenter.prevmatch

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.feechan.footballmatch.R
import com.feechan.footballmatch.base.BaseFragment
import com.feechan.footballmatch.component.adapter.PrevMatchAdapter
import com.feechan.footballmatch.component.anko.PrevMatchFragmentUI
import com.feechan.footballmatch.data.network.response.Event
import com.feechan.footballmatch.data.repository.ApiRepository
import com.feechan.footballmatch.presenter.DetailMatchActivity
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

    class PrevMatchFragment : BaseFragment(), PrevMatchContract.View{
    private lateinit var presenter: PrevMatchPresenter

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var adapter: PrevMatchAdapter
    private lateinit var matchRecylerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    companion object Factory{
        fun getInstance(): PrevMatchFragment {
            return PrevMatchFragment()
        }
    }

    override fun getContentView(container: ViewGroup): View {
        val view = PrevMatchFragmentUI().createView(AnkoContext.Companion.create(ctx,container))
        matchRecylerView = view.find(R.id.matchPrevRecyclerView)
        swipeRefreshLayout = view.find(R.id.swipeRefreshLayout)
        adapter = PrevMatchAdapter(events){ event ->
            this.openEventDetail(event)
        }
        matchRecylerView.adapter = adapter

        swipeRefreshLayout.onRefresh {
            presenter.loadData()
        }
        return view
    }

    private fun openEventDetail(event: Event){
        ctx.startActivity<DetailMatchActivity>("eventid" to event.idEvent)
    }

    override fun setup() {
        initPresenter()
        presenter.loadData()
    }

    private fun initPresenter(){
        val gson = Gson()
        val request = ApiRepository()
        presenter = PrevMatchPresenter(this, request, gson)
    }

    override fun loadMatchData(data: List<Event>) {
        swipeRefreshLayout.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}