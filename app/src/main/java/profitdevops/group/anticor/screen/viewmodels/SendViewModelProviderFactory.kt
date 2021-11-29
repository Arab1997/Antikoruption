package profitdevops.group.anticor.screen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import profitdevops.group.anticor.repository.SendRepository

class SendViewModelProviderFactory(
    val sendRepository: SendRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SendViewModel(sendRepository) as T
    }
}