package cl.diegoacuna.reigntest.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import cl.diegoacuna.reigntest.R
import cl.diegoacuna.reigntest.utils.Constants.EXTRA_LABEL
import kotlinx.android.synthetic.main.activity_item_view.*
import org.jetbrains.anko.design.snackbar

class ItemViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_item_view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.back_title)

        val webview = findViewById<WebView>(R.id.webView)

        val bundle: Bundle? = intent.extras

        if (bundle?.containsKey(EXTRA_LABEL) == true) {

            val url = bundle.getString(EXTRA_LABEL)

            if (url != "")
                webview.loadUrl(url)
            else
                webView.snackbar(getString(R.string.url_not_found)).show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
