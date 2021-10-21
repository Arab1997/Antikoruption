package profitdevs.group.anticor.screen.main.send.detail

import android.app.Dialog
import android.graphics.Region
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_send.*
import profitdevs.group.anticor.screen.selectregion.SelectRegionViewModel
import profitdevs.group.anticor.screen.main.MainViewModel
import profitdevs.group.anticor.R

/**
 * A simple [Fragment] subclass.
 */
interface ProductDetailListener{
    fun onHideDialog()
}

class SendFragment() : BottomSheetDialogFragment() {
    lateinit var viewModel: MainViewModel
    lateinit var viewModel11: SelectRegionViewModel
    var id: String = ""
    var cartCount = 1


    private lateinit var selectedRegion: Region
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            val dialog = it as BottomSheetDialog
            val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            BottomSheetBehavior.from(bottomSheet!!).state = BottomSheetBehavior.STATE_EXPANDED
        }
        return bottomSheetDialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.SheetDialog)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel.getProductDetail(id)


        validate()
        setUpListeners()
       // viewModel11.getRegions()

        imgBack.setOnClickListener {

        }

        /*cardViewAdd.setOnClickListener {
            val product = viewModel.productDetailData.value
            if (product != null){
                var cart = BasketModel(product.id!!, cartCount)
                Prefs.add2Cart(cart)
                if (cartCount > 0){
                    activity?.showSuccess(getString(R.string.cart_updated))
                }else{
                    activity?.showSuccess(getString(R.string.product_removed))
                }
                listener.onHideDialog()
                dismiss()
            }
        }*/

    }


    private fun getRegions() {
       // viewModel11.getRegions()
    }

    private fun isUiEnable(isEnabled: Boolean) {
        pora_hajmi.isEnabled = isEnabled
        murojaat_mazmuni.isEnabled = isEnabled
    }

    private fun setUpListeners() {
        btn_register.setOnClickListener {
            if (::selectedRegion.isInitialized) {
               /* viewModel11.send(
                    RegisterRequest(
                        pora_hajmi.text.toString(),
                        murojaat_mazmuni.text.toString(),
                        murojaat_matni.text.toString(),
                        selectedRegion.provinceId.toLong(),
                        selectedRegion.id
                    )
                )*/
            }
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
        btn_register.isEnabled = false
    }

    private fun validate() {
        btn_register.isEnabled =
            murojaat_mazmuni.text!!.length > 2 &&
                    pora_hajmi.text!!.length > 2 &&
                    murojaat_matni.text!!.length >= 6
    }

    /*fun setProduct(){
        //lyCart.visibility = View.VISIBLE
        val product = viewModel.productDetailData.value

        Prefs.getCartList().forEach {
            if (it.id == product?.id){
                cartCount = it.count
            }
        }

        imgProduct.loadImage(App.imageBaseUrl + product?.image)
        tvTitle.text = product?.name
        tvDescription.text = product?.information
        if (product?.information.isNullOrEmpty()){
            tvDescription.visibility = View.VISIBLE
        }
        tvPrice.text = product?.price?.formattedAmount()
        product?.favourite = Prefs.isFavourite(product?.id!!)

        if (product?.favourite == true){
            imgFavourite.setImageResource(R.drawable.ic_fill_heart)
        }else{
            imgFavourite.setImageResource(R.drawable.ic_heart)
        }
        updateCartAmounts()
    }*/

  /*  fun updateCartAmounts(){
        val product = viewModel.productDetailData.value
        tvTotalAmount.text = ((product?.price ?: 0.0) * cartCount).formattedAmount()
    }*/


}
