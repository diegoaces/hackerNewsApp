package cl.diegoacuna.reigntest.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cl.diegoacuna.reigntest.R
import cl.diegoacuna.reigntest.model.local.Hit
import cl.diegoacuna.reigntest.presenter.MainPresenter
import cl.diegoacuna.reigntest.presenter.MainPresenterImpl
import cl.diegoacuna.reigntest.ui.adapter.NewsAdapter
import cl.diegoacuna.reigntest.ui.adapter.NewsAdapter.ItemListener
import cl.diegoacuna.reigntest.utils.Constants
import cl.diegoacuna.reigntest.view.MainView
import io.realm.OrderedRealmCollection
import org.jetbrains.anko.design.snackbar

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenter
    private lateinit var swiperefreshNews: SwipeRefreshLayout
    private lateinit var recyclerViewNews: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swiperefreshNews = findViewById(R.id.swiperefresh_news)
        recyclerViewNews = findViewById(R.id.recycler_view_news)

        presenter = MainPresenterImpl(this)

        swiperefreshNews.setOnRefreshListener {
            presenter.getAllNews()
        }

    }

    override fun showNews(news: OrderedRealmCollection<Hit>) {
        recyclerViewNews.apply {
            setHasFixedSize(true)
            adapter = NewsAdapter(
                news,
                object :
                    ItemListener {
                    override fun onDeleteItem(objectID: Int?) {
                        presenter.deleteNew(objectID)
                    }

                    override fun onItemSelected(storyUrl: String?) {
                        presenter.showItemSelected(storyUrl)
                    }
                })
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    override fun showLoading(loading: Boolean) {
        swiperefreshNews.isRefreshing = loading
    }

    override fun showNew(storyUrl: String) {
        val intent = Intent(this, ItemViewActivity::class.java)
        intent.putExtra(Constants.EXTRA_LABEL, storyUrl)
        startActivity(intent)
    }

    override fun errorLoading() {
        recyclerViewNews.snackbar(R.string.loading_error)
    }

}
