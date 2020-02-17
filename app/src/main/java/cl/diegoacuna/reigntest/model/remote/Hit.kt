package cl.diegoacuna.reigntest.model.remote

import com.google.gson.annotations.SerializedName
import java.util.*

open class Hit {
    @SerializedName("created_at")
    var createdAt: Date? = null
    var author: String? = null
    @SerializedName("story_text")
    var storyText: String? = null
    @SerializedName("story_title")
    var storyTitle: String? = null
    @SerializedName("story_url")
    var storyUrl: String? = null
    @SerializedName("created_at_i")
    var createdAtI: Long? = null
    var objectID: Int? = null
}
