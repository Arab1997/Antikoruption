package anticordev.group.anticoruption.screen.main.category

import android.R.attr.data
import android.content.Intent
import android.view.View
import android.widget.Toast
import anticordev.group.anticoruption.R
import anticordev.group.anticoruption.base.BaseFragment
import anticordev.group.anticoruption.base.startActivity
import anticordev.group.anticoruption.screen.main.oneId.OneActivity
import anticordev.group.anticoruption.screen.main.send.detail.SendActivity
import anticordev.group.anticoruption.util.utils.Constants
import anticordev.group.anticoruption.util.utils.Prefs
import kotlinx.android.synthetic.main.fragment_category.*


class CategoryFragment : BaseFragment(), View.OnClickListener {
    override fun getLayout(): Int = R.layout.fragment_category

    override fun setupViews() {
        val intent = Intent(requireContext(), SendActivity::class.java)
        val intent2 = Intent(requireContext(), OneActivity::class.java)

        if (Prefs.getToken().isEmpty()) {
            btn_register.visibility = View.VISIBLE
            btn_register.setOnClickListener {
                getBaseActivity {
                    it.startActivity<OneActivity>(Constants.EXTRA_DATA, Constants.EXTRA_DATA_2)
                }
            }


            category_card1.setOnClickListener {
                intent.putExtra(Constants.EXTRA_DATA, Constants.BTN_TYPE_0.toString())
                startActivity(intent2)
            }
            category_card2.setOnClickListener {
                intent.putExtra(Constants.EXTRA_DATA, Constants.BTN_TYPE_1.toString())
                startActivity(intent2)
            }
            category_card3.setOnClickListener {
                intent.putExtra(Constants.EXTRA_DATA, Constants.BTN_TYPE_2.toString())
                startActivity(intent2)
            }
            category_card4.setOnClickListener {
                intent.putExtra(Constants.EXTRA_DATA, Constants.BTN_TYPE_3.toString())
                startActivity(intent2)
            }

        } else {
            btn_register.visibility = View.GONE

            category_card1.setOnClickListener {
                intent.putExtra(Constants.EXTRA_DATA, Constants.BTN_TYPE_0.toString())
                startActivity(intent)
            }
            category_card2.setOnClickListener {
                intent.putExtra(Constants.EXTRA_DATA, Constants.BTN_TYPE_1.toString())
                startActivity(intent)
            }
            category_card3.setOnClickListener {
                intent.putExtra(Constants.EXTRA_DATA, Constants.BTN_TYPE_2.toString())
                startActivity(intent)
            }
            category_card4.setOnClickListener {
                intent.putExtra(Constants.EXTRA_DATA, Constants.BTN_TYPE_3.toString())
                startActivity(intent)
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









