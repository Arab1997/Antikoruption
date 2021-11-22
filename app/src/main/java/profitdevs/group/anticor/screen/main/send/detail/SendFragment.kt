package profitdevs.group.anticor.screen.main.send.detail

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_category_details.*
import kotlinx.android.synthetic.main.fragment_send.*
import kotlinx.android.synthetic.main.fragment_send.shaxs
import kotlinx.android.synthetic.main.fragment_send.spinner_hudud
import kotlinx.android.synthetic.main.fragment_send.spinner_shahar
import kotlinx.android.synthetic.main.fragment_send.spinner_tashkilot
import kotlinx.android.synthetic.main.fragment_send.spinner_tuman
import profitdevs.group.anticor.R
import profitdevs.group.anticor.model.send_models.Complain
import profitdevs.group.anticor.repository.SendRepository
import profitdevs.group.anticor.screen.viewmodels.SendViewModel
import profitdevs.group.anticor.screen.viewmodels.SendViewModelProviderFactory

interface ProductDetailListener {
    fun onHideDialog()
}

class SendFragment() : Fragment(R.layout.fragment_send) {

    private lateinit var viewModel: SendViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as SendActivity).viewModel

    }

    private fun isUiEnable(isEnabled: Boolean) {
        pora_hajmi.isEnabled = isEnabled
        murojaat_mazmuni.isEnabled = isEnabled
    }

    private fun setUpListeners() {
        btnSend.setOnClickListener {
        }

        pora_hajmi.addTextChangedListener {
            it?.let {
                if (it.length > 6)
                    validate()
                else {
                    disableRequest()
                }
            }
        }

        murojaat_mazmuni.addTextChangedListener {
            it?.let {
                if (it.length > 6)
                    validate()
                else {
                    disableRequest()
                }
            }
        }
    }

    private fun disableRequest() {
        btnSend.isEnabled = true
    }

    private fun validate() {
        btnSend.isEnabled =
            murojaat_mazmuni.text!!.length > 2 &&
                    pora_hajmi.text!!.length > 2 &&
                    murojaat_matni.text!!.length >= 6
    }
}
