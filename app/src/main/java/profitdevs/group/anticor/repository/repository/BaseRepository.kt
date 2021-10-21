package profitdev.group.eantikor.repository

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseRepository {

   // val api = Client.getInstance(App.app).create(Api::class.java)

    val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable): Disposable {
        compositeDisposable.add(disposable)
        return disposable
    }
}