package profitdevs.group.anticor.screen.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import profitdevs.group.anticor.repository.SendRepository
import profitdevs.group.anticor.screen.main.oneId.MainViewModel

/**
 * @author Zokirjon
 * @date 11/17/2021
 */
class MainViewModelProviderFactory(
    val sendRepository: SendRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(sendRepository) as T
    }
}