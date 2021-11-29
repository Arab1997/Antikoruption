package profitdevops.group.anticor.screen.main.category

import android.view.View
import kotlinx.android.synthetic.main.fragment_category.*
import profitdevops.group.anticor.base.BaseFragment
import profitdevops.group.anticor.base.startActivity
import profitdevops.group.anticor.screen.main.oneId.OneActivity
import profitdevops.group.anticor.util.utils.Constants
import profitdevops.group.anticor.screen.main.send.detail.SendActivity
import profitdevops.group.anticor.R

class CategoryFragment : BaseFragment(), View.OnClickListener {
    override fun getLayout(): Int = R.layout.fragment_category

    override fun setupViews() {
        btn_register.setOnClickListener {
            getBaseActivity {
                it.startActivity<OneActivity>(Constants.EXTRA_DATA,  Constants.EXTRA_DATA_2)
            }

        }
        category_card1.setOnClickListener {
            getBaseActivity {
                it.startActivity<SendActivity>(Constants.EXTRA_DATA,  Constants.EXTRA_DATA_2)
            }

        }
        category_card2.setOnClickListener {
            getBaseActivity {
                it.startActivity<SendActivity>(Constants.EXTRA_DATA,  Constants.EXTRA_DATA_2)
            }

        }
        category_card3.setOnClickListener {
            getBaseActivity {
                it.startActivity<SendActivity>(Constants.EXTRA_DATA,  Constants.EXTRA_DATA_2)
            }

        }
        category_card4.setOnClickListener {
            getBaseActivity {
                it.startActivity<SendActivity>(Constants.EXTRA_DATA,  Constants.EXTRA_DATA_2)
            }

        }
        }
    override fun loadData() {
    }
    override fun setData() {
    }
    override fun onClick(v: View?) {
    }
    }









