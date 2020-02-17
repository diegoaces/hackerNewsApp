package cl.diegoacuna.reigntest.model.repository

import cl.diegoacuna.reigntest.NewsApplication
import cl.diegoacuna.reigntest.model.local.Hit
import cl.diegoacuna.reigntest.model.local.NewsDataHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.OrderedRealmCollection

class NewsRepo {

    fun getAll(
        onSucces: () -> Unit,
        onError: () -> Unit,
        onFinished: (news: OrderedRealmCollection<Hit>) -> Unit
    ) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            NewsApplication().getNewApiService()
                .getAllNews("android")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->

                    result?.let { data ->
                        data.hits?.forEach { hit ->
                            NewsDataHelper().create(hit)
                        }
                    }
                    onSucces()

                }, {
                    onError()
                })
        )
        onFinished(NewsDataHelper().getAll())
    }

    fun delete(id: Int?) {
        NewsDataHelper().deleteById(id)
    }
}