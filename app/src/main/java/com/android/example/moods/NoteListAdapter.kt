package com.android.example.moods

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.example.moods.data.Note


class NoteListAdapter(private var mNoteList: List<Note>): RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val entryView: LinearLayout = itemView.findViewById(R.id.note_container)
        val moodView: TextView = itemView.findViewById(R.id.mood)
        val contentView: TextView = itemView.findViewById(R.id.content)
        var reactionView: ImageView = itemView.findViewById(R.id.reaction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.notelist_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val currentItem = mNoteList[position]

        holder.moodView.text = currentItem.title
        holder.contentView.text = currentItem.content
        currentItem.reaction?.let { setMoodImage(holder.reactionView, it) }

        holder.entryView.setOnClickListener{
            val action = NoteFeedFragmentDirections.actionNoteFeedFragmentToViewNoteFragment(currentItem)
            it.findNavController().navigate(action)
        }

    }

    // Update the recycler view list with new data passed in
    fun setData(newNotes: List<Note>, emptyState: ConstraintLayout) {
        this.mNoteList = newNotes
        if (mNoteList.isNotEmpty()) {
            emptyState.visibility = View.GONE
        }
        notifyDataSetChanged()
    }

    // This method may be useful for setting mood images in the ViewHolder
    private fun setMoodImage(view: ImageView, mood: String) {
        when (mood) {
            "anxious" -> view.setImageResource(R.drawable.ic_anxious_outline_false)
            "content" -> view.setImageResource(R.drawable.ic_content_outline_false)
            "happy" -> view.setImageResource(R.drawable.ic_happy_outline_false)
            "mad" -> view.setImageResource(R.drawable.ic_mad_outline_false)
            "neutral" -> view.setImageResource(R.drawable.ic_neutral_outline_false)
            "sad" -> view.setImageResource(R.drawable.ic_sad_outline_false)
            else -> { // Note the block
                view.setImageResource(R.drawable.ic_empty_state)
            }
        }
    }

    override fun getItemCount(): Int = mNoteList.size

}
