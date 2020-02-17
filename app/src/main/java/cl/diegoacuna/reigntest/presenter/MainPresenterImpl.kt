package cl.diegoacuna.reigntest.presenter

import cl.diegoacuna.reigntest.model.repository.NewsRepo
import cl.diegoacuna.reigntest.view.MainView

class MainPresenterImpl(private val mainView: MainView) : MainPresenter {

    init {
        getAllNews()
    }

    override fun getAllNews() {
        mainView.showLoading(true)

        val newsRepo = NewsRepo()
        newsRepo.getAll({
            mainView.showLoading(false)
        }, {
            mainView.showLoading(false)
            mainView.errorLoading()
        }, { news ->
            mainView.showNews(news)
        })


    }

    override fun deleteNew(id: Int?) {
        val newsRepo = NewsRepo()
        newsRepo.delete(id)
    }

    override fun showItemSelected(storyUrl: String?) {
        mainView.showNew(storyUrl.toString())
    }

}
