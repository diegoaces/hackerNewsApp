package cl.diegoacuna.reigntest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.diegoacuna.reigntest.R
import cl.diegoacuna.reigntest.model.local.Hit
import com.daimajia.swipe.SwipeLayout
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class NewsAdapter(news: OrderedRealmCollection<Hit>, val listener: ItemListener) :
    RealmRecyclerViewAdapter<Hit, NewsAdapter.NewViewHolder>(news, true) {

    private var itemListener: ItemListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {

        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)

        itemListener = listener

        return NewViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        val item = data?.get(position)
        item?.let {
            holder.bind(item)
        }
    }

    inner class NewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var itemAuthor = view.findViewById<TextView>(R.id.tv_author)
        private var itemStoryTitle = view.findViewById<TextView>(R.id.tv_story_title)
        private var itemCreateAt = view.findViewById<TextView>(R.id.tv_create_at)
        private var itemDelete = view.findViewById<LinearLayout>(R.id.btn_delete)
        private var swipeLayout = view.findViewById<SwipeLayout>(R.id.swipe_layout)

        fun bind(hit: Hit) {
            itemAuthor.text = hit.author.toString()
            itemStoryTitle.text = hit.storyTitle.toString()
            itemCreateAt.text = hit.getTime()
            itemDelete.setOnClickListener {
                listener.onDeleteItem(hit.objectID)
            }
            swipeLayout.surfaceView.setOnClickListener {
                listener.onItemSelected(hit.storyUrl)
            }

        }
    }

    interface ItemListener {
        fun onDeleteItem(objectID: Int?)
        fun onItemSelected(storyUrl: String?)

    }

}
