package profitdevs.group.anticor.screen.main.savollar

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import kotlinx.android.synthetic.main.activity_savollar.*
import profitdevs.group.anticor.R
import profitdevs.group.anticor.base.BaseActivity

class SavollarActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_savollar

    override fun initViews() {
        imgBackk.setOnClickListener { finish() }

        expandBtn1.setOnClickListener {
            if (expandableLayout1.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(cardView1, AutoTransition())
                expandableLayout1.visibility = View.VISIBLE
                expandBtn1.text = "Закрить"
            } else {
                TransitionManager.beginDelayedTransition(cardView1, AutoTransition())
                expandableLayout1.visibility = View.GONE
                expandBtn1.text = "Просмотреть"
            }
        }


        expandBtn2.setOnClickListener {
            if (expandableLayout2.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(cardView2, AutoTransition())
                expandableLayout2.visibility = View.VISIBLE
                expandBtn1.text = "Закрить"
            } else {
                TransitionManager.beginDelayedTransition(cardView2, AutoTransition())
                expandableLayout2.visibility = View.GONE
                expandBtn2.text = "Просмотреть"
            }
        }

        expandBtn3.setOnClickListener {
            if (expandableLayout3.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(cardView3, AutoTransition())
                expandableLayout3.visibility = View.VISIBLE
                expandBtn3.text = "Просмотреть"
            } else {
                TransitionManager.beginDelayedTransition(cardView3, AutoTransition())
                expandableLayout3.visibility = View.GONE
                expandBtn3.text = "Закрить"
            }
        }

        expandBtn4.setOnClickListener {
            if (expandableLayout4.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(cardView4, AutoTransition())
                expandableLayout4.visibility = View.VISIBLE
                expandBtn4.text = "Просмотреть"
            } else {
                TransitionManager.beginDelayedTransition(cardView4, AutoTransition())
                expandableLayout4.visibility = View.GONE
                expandBtn4.text = "Закрить"
            }
        }
    }
    override fun loadData() {
    }

    override fun initData() {
    }

    override fun updateData() {
    }
}