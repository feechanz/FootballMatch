package com.feechan.footballmatch.presenter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import com.feechan.footballmatch.R
import com.feechan.footballmatch.base.BaseActivity
import com.feechan.footballmatch.component.anko.MainActivityUI
import com.feechan.footballmatch.presenter.nextmatch.NextMatchFragment
import com.feechan.footballmatch.presenter.prevmatch.PrevMatchFragment
import org.jetbrains.anko.setContentView
import com.feechan.footballmatch.presenter.favorites.FavoritesFragment

class MainActivity : BaseActivity(), MainContract.View {
    private lateinit var presenter: MainPresenter
    private lateinit var contentUI: MainActivityUI

    private var fragments: MutableList<Fragment> = mutableListOf()
    val FRAGMENT_PREVMATCH = 0
    val FRAGMENT_NEXTMATCH = 1
    val FRAGMENT_FAVORITES = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentUI = MainActivityUI()
        contentUI.setContentView(this)

        initBaseView()
    }

    override fun initBaseView() {
        contentUI.bottomNavigationView.menu.add(Menu.NONE, R.id.prev_match_menu, Menu.NONE, "Prev Match").setIcon(R.drawable.ic_prev_match)
        contentUI.bottomNavigationView.menu.add(Menu.NONE, R.id.next_match_menu, Menu.NONE, "Next Match").setIcon(R.drawable.ic_next_match)
        contentUI.bottomNavigationView.menu.add(Menu.NONE, R.id.favorites, Menu.NONE, "Favorites").setIcon(R.drawable.ic_favorites)

        presenter = MainPresenter(this)
        progressBar = contentUI.progressBar
        initFragments()
    }

    fun initFragments(){
        fragments.add(FRAGMENT_PREVMATCH, PrevMatchFragment.getInstance())
        fragments.add(FRAGMENT_NEXTMATCH, NextMatchFragment.getInstance())
        fragments.add(FRAGMENT_FAVORITES, FavoritesFragment.getInstance())


        val transaction = supportFragmentManager.beginTransaction()
        val fragment = fragments[0]
        transaction.replace(R.id.main_container, fragment).commit()

        contentUI.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val id = item.itemId
            var index = 0;
            if(id == R.id.prev_match_menu){
                index = 0
            }else if(id == R.id.next_match_menu){
                index = 1
            }else{
                index = 2
            }
            val fragment = fragments[index]
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_container, fragment).commit()

             true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}
