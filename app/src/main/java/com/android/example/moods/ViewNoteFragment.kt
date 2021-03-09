package com.android.example.moods

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.example.moods.data.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ViewNoteFragment : Fragment() {

    private val args: ViewNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_note, container, false)
    }

    private lateinit var myNoteViewModel: NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title: TextView = view.findViewById(R.id.mood_title_view)
        val noteText: TextView = view.findViewById(R.id.note_text_view)
        val reactionImage: ImageView = view.findViewById(R.id.reaction_view)

        noteText.movementMethod = ScrollingMovementMethod()

        title.text = args.selectedNote.title
        noteText.text = args.selectedNote.content
        args.selectedNote.reaction?.let { setMoodImage(reactionImage, it) }

        val backButton: Button = view.findViewById(R.id.back)
        backButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_viewNoteFragment_to_noteFeedFragment)
        }

        val editButton: FloatingActionButton = view.findViewById(R.id.edit)
        editButton.setOnClickListener{
            val action = ViewNoteFragmentDirections.actionViewNoteFragmentToUpdateNoteFragment(args.selectedNote)
            it.findNavController().navigate(action)
        }

        myNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        val deleteButton: FloatingActionButton = view.findViewById(R.id.delete)
        deleteButton.setOnClickListener{
            deleteNoteFromDB()
        }
    }

    private fun deleteNoteFromDB() {
        myNoteViewModel.deleteNote(args.selectedNote)
        Toast.makeText(requireContext(), "Successfully Deleted Note!", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_viewNoteFragment_to_noteFeedFragment)
    }

    // Feel free to use/update this function if you find parts of it useful for your implementation
    private fun setMoodImage(view: ImageView, mood: String) {
        when (mood) {
            "anxious" -> view.setImageResource(R.drawable.ic_anxious_outline_false)
            "content" -> view.setImageResource(R.drawable.ic_content_outline_false)
            "happy" -> view.setImageResource(R.drawable.ic_happy_outline_false)
            "mad" -> view.setImageResource(R.drawable.ic_mad_outline_false)
            "neutral" -> view.setImageResource(R.drawable.ic_neutral_outline_false)
            "sad" -> view.setImageResource(R.drawable.ic_sad_outline_false)
            else -> {
                view.setImageResource(R.drawable.ic_empty_state)
            }
        }
    }
}