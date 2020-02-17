package cl.diegoacuna.reigntest.model.local

import android.text.format.DateUtils
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class Hit : RealmModel {
    @PrimaryKey
    var objectID: Int? = null
    var createdAt: Date? = null
    var author: String? = null
    var storyText: String? = null
    var storyTitle: String? = null
    var storyUrl: String? = null
    var createdAtI: Long? = null

    fun getTime(): String {

        val now = Date().time
        val createAt = createdAtI?.times(1000) ?: now

        val timer = DateUtils.getRelativeTimeSpanString(
            createAt, now, 0L, DateUtils.FORMAT_ABBREV_ALL
        )

        return timer.toString()
    }

    companion object {
        const val CREATED_AT = "createdAtI"
        const val ID = "objectID"
    }
}
