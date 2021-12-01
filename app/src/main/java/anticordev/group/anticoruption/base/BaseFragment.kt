package anticordev.group.anticoruption.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import anticordev.group.anticoruption.util.utils.Prefs

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(),container, false)
    }

    fun checkUser(): Boolean{
        return !Prefs.getToken().isEmpty()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        loadData()
        setData()
    }

    fun getBaseActivity(run : (BaseActivity) -> (Unit)) {
        (activity as? BaseActivity).let {
            it?.let { it1 ->
                run(it1)
            }
        }
    }

    abstract fun getLayout() : Int
    abstract fun setupViews()
    abstract fun loadData()
    abstract fun setData()
}