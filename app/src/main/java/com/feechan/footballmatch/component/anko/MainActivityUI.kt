package com.feechan.footballmatch.component.anko

import android.graphics.Color
import android.support.design.widget.BottomNavigationView
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.feechan.footballmatch.R
import com.feechan.footballmatch.base.BaseActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView

class MainActivityUI : AnkoComponent<BaseActivity> {
    lateinit var frameLayout: FrameLayout
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var progressBar: ProgressBar

    override fun createView(ui: AnkoContext<BaseActivity>) = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = matchParent)
            backgroundColor = Color.LTGRAY

            frameLayout = frameLayout {
                lparams(width = matchParent, height = matchParent)

                id = R.id.main_container
            }

            bottomNavigationView = bottomNavigationView {
                id = R.id.bottomNavigationView
                backgroundColor = Color.WHITE
            }.lparams {
                width = matchParent
                height = wrapContent
                alignParentBottom()
                centerHorizontally()
            }

            progressBar = progressBar {
            }.lparams {
                centerHorizontally()
            }
        }

    }
}