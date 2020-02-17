package cl.diegoacuna.reigntest.presenter

interface MainPresenter {

    fun getAllNews()
    fun deleteNew(id: Int?)
    fun showItemSelected(storyUrl: String?)
}