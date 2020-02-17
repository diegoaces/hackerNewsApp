package cl.diegoacuna.reigntest.model.local

import android.util.Log
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.deleteFromRealm
import io.realm.kotlin.where

class NewsDataHelper {
    val realm: Realm = Realm.getDefaultInstance()


    fun create(hit: cl.diegoacuna.reigntest.model.remote.Hit) {
        realm.executeTransactionAsync({
            val hitLocal = Hit()
            hitLocal.objectID = hit.objectID
            hitLocal.author = hit.author
            hitLocal.storyUrl = hit.storyUrl
            hitLocal.storyTitle = hit.storyTitle
            hitLocal.storyText = hit.storyText
            hitLocal.createdAt = hit.createdAt
            hitLocal.createdAtI = hit.createdAtI

            it.insertOrUpdate(hitLocal)
        }, {

        }, {
            Log.e("ERROR", it.toString())
        })
        realm.close()
    }

    fun getAll(): OrderedRealmCollection<Hit> {
        val news = realm.where<Hit>()
            .sort(Hit.CREATED_AT, Sort.DESCENDING).findAll()

        realm.close()

        return news

    }

    fun deleteById(id: Int?) {

        realm.executeTransactionAsync {
            val hit = it.where<Hit>().equalTo(Hit.ID, id).findFirst()
            hit?.deleteFromRealm()
        }
        realm.close()

    }
}