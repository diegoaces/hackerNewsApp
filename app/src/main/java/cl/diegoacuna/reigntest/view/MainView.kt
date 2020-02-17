package cl.diegoacuna.reigntest.view

import cl.diegoacuna.reigntest.model.local.Hit
import io.realm.OrderedRealmCollection

interface MainView {
    fun showNews(news: OrderedRealmCollection<Hit>)
    fun showLoading(loading:Boolean)
    fun showNew(storyUrl: String)
    fun errorLoading()
}