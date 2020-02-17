package cl.diegoacuna.reigntest

import android.app.Application
import cl.diegoacuna.reigntest.service.NewApiService
import cl.diegoacuna.reigntest.utils.Constants
import io.realm.Realm
import io.realm.RealmConfiguration

class NewsApplication : Application() {

    var realm: Realm? = null
    private var newsApiService: NewApiService? = null

    override fun onCreate() {
        super.onCreate()
        initRealmDb()
    }

    private fun initRealmDb() {
        val schemaVersion: Long = 1
        val filename = "news.realm"

        Realm.init(this)

        val realmConfiguration =
            RealmConfiguration.Builder()
                .schemaVersion(schemaVersion)
                .compactOnLaunch()
                .deleteRealmIfMigrationNeeded()
                .name(filename)
                .build()

        Realm.setDefaultConfiguration(realmConfiguration)

        realm = Realm.getDefaultInstance()
    }

    fun getNewApiService(): NewApiService {

        if (newsApiService == null) {
            newsApiService =
                NewApiService.Factory.create(Constants.URL)
        }
        return newsApiService!!
    }


}