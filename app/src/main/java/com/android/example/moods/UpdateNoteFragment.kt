package com.android.example.moods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.example.moods.data.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_update_note.*

class UpdateNoteFragment : Fragment(), View.OnClickListener {

    private val args: UpdateNoteFragmentArgs by navArgs()
    private var reactionImageChosen: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_note, container, false)
    }

    private lateinit var myNoteViewModel: NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title: EditText = requireView().findViewById(R.id.mood_title_update)
        val noteText: EditText = requireView().findViewById(R.id.note_text_update)

        title.setText(args.selectedNote.title)
        noteText.setText(args.selectedNote.content)
        reactionImageChosen = args.selectedNote.reaction!!

        myNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        val confirmUpdate: FloatingActionButton = view.findViewById(R.id.confirm_update)

        confirmUpdate.setOnClickListener{
            when {
                title.text.isBlank() -> {
                    Toast.makeText(context,"Title cannot be left empty!",Toast.LENGTH_SHORT).show()
                }
                noteText.text.isBlank() -> {
                    Toast.makeText(context,"Description cannot be left empty!",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    updateNoteIntoDB()
                }
            }
        }

        val cancelUpdate: FloatingActionButton = view.findViewById(R.id.cancel_update)
        cancelUpdate.setOnClickListener{
            findNavController().navigate(R.id.action_updateNoteFragment_to_noteFeedFragment)
        }

        val happyButton: Button = requireView().findViewById(R.id.happy_button2)
        val sadButton: Button = requireView().findViewById(R.id.sad_button2)
        val contentButton: Button = requireView().findViewById(R.id.content_button2)
        val anxiousButton: Button = requireView().findViewById(R.id.anxious_button2)
        val madButton: Button = requireView().findViewById(R.id.mad_button2)
        val neutralButton: Button = requireView().findViewById(R.id.neutral_button2)

        when (reactionImageChosen) {
            "anxious" -> anxiousButton.setBackgroundResource(R.drawable.ic_anxious_outline_true)
            "content" -> contentButton.setBackgroundResource(R.drawable.ic_content_outline_true)
            "happy" -> happyButton.setBackgroundResource(R.drawable.ic_happy_outline_true)
            "mad" -> madButton.setBackgroundResource(R.drawable.ic_mad_outline_true)
            "neutral" -> neutralButton.setBackgroundResource(R.drawable.ic_neutral_outline_true)
            "sad" -> sadButton.setBackgroundResource(R.drawable.ic_sad_outline_true)
        }

        happyButton.setOnClickListener(this)
        sadButton.setOnClickListener(this)
        contentButton.setOnClickListener(this)
        anxiousButton.setOnClickListener(this)
        madButton.setOnClickListener(this)
        neutralButton.setOnClickListener(this)

    }

    private fun updateNoteIntoDB() {
        val title = mood_title_update.text.toString()
        val text = note_text_update.text.toString()

        args.selectedNote.title = title
        args.selectedNote.content = text
        args.selectedNote.reaction = reactionImageChosen

        myNoteViewModel.updateNote(args.selectedNote)
        Toast.makeText(requireContext(), "Successfully Updated Note!", Toast.LENGTH_LONG).show()

        findNavController().navigate(R.id.action_updateNoteFragment_to_noteFeedFragment)
    }

    // Feel free to use/update this function if you find parts of it useful for your implementation
    /*private fun setMoodImage(view: ImageView, mood: String) {
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
    }*/

    override fun onClick(p0: View?) {

        val happyButton: Button = requireView().findViewById(R.id.happy_button2)
        val sadButton: Button = requireView().findViewById(R.id.sad_button2)
        val contentButton: Button = requireView().findViewById(R.id.content_button2)
        val anxiousButton: Button = requireView().findViewById(R.id.anxious_button2)
        val madButton: Button = requireView().findViewById(R.id.mad_button2)
        val neutralButton: Button = requireView().findViewById(R.id.neutral_button2)

        fun resetReactionOutlines() {
            happyButton.setBackgroundResource(R.drawable.ic_happy_outline_false)
            sadButton.setBackgroundResource(R.drawable.ic_sad_outline_false)
            contentButton.setBackgroundResource(R.drawable.ic_content_outline_false)
            anxiousButton.setBackgroundResource(R.drawable.ic_anxious_outline_false)
            madButton.setBackgroundResource(R.drawable.ic_mad_outline_false)
            neutralButton.setBackgroundResource(R.drawable.ic_neutral_outline_false)

        }

        if (p0 != null) {
            when (p0) {
                happyButton -> {
                    resetReactionOutlines()
                    p0.setBackgroundResource(R.drawable.ic_happy_outline_true)
                    reactionImageChosen = "happy"
                }
                sadButton -> {
                    resetReactionOutlines()
                    p0.setBackgroundResource(R.drawable.ic_sad_outline_true)
                    reactionImageChosen = "sad"
                }
                contentButton -> {
                    resetReactionOutlines()
                    p0.setBackgroundResource(R.drawable.ic_content_outline_true)
                    reactionImageChosen = "content"
                }
                anxiousButton -> {
                    resetReactionOutlines()
                    p0.setBackgroundResource(R.drawable.ic_anxious_outline_true)
                    reactionImageChosen = "anxious"
                }
                madButton -> {
                    resetReactionOutlines()
                    p0.setBackgroundResource(R.drawable.ic_mad_outline_true)
                    reactionImageChosen = "mad"
                }
                neutralButton -> {
                    resetReactionOutlines()
                    p0.setBackgroundResource(R.drawable.ic_neutral_outline_true)
                    reactionImageChosen = "neutral"
                }
            }
        }
    }
}