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
import com.android.example.moods.data.Note
import com.android.example.moods.data.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_create_note.*

class CreateNoteFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    // this will hold which reaction image the user has chosen after clicking confirm
    // default: happy
    private var reactionImageChosen: String = "happy"
    private lateinit var myNoteViewModel: NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // text from EditText
        val title: EditText = requireView().findViewById(R.id.mood_title)
        val noteText: EditText = requireView().findViewById(R.id.note_text)

        val confirmCreateMood: FloatingActionButton = view.findViewById(R.id.confirm)

        myNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        confirmCreateMood.setOnClickListener{
            when {
                title.text.isBlank() -> {
                    Toast.makeText(context,"Title cannot be left empty!",Toast.LENGTH_SHORT).show()
                }
                noteText.text.isBlank() -> {
                    Toast.makeText(context,"Description cannot be left empty!",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    insertNoteIntoDB()
                }
            }
        }

        val cancelCreateMood: FloatingActionButton = view.findViewById(R.id.cancel)
        cancelCreateMood.setOnClickListener{
            findNavController().navigate(R.id.action_createNoteFragment_to_noteFeedFragment)
        }

        val happyButton: Button = requireView().findViewById(R.id.happy_button)
        val sadButton: Button = requireView().findViewById(R.id.sad_button)
        val contentButton: Button = requireView().findViewById(R.id.content_button)
        val anxiousButton: Button = requireView().findViewById(R.id.anxious_button)
        val madButton: Button = requireView().findViewById(R.id.mad_button)
        val neutralButton: Button = requireView().findViewById(R.id.neutral_button)

        happyButton.setOnClickListener(this)
        sadButton.setOnClickListener(this)
        contentButton.setOnClickListener(this)
        anxiousButton.setOnClickListener(this)
        madButton.setOnClickListener(this)
        neutralButton.setOnClickListener(this)

    }

    private fun insertNoteIntoDB() {
        // chosen reaction stored in reactionImageChosen, title and description stored in corresponding EditTexts in fragment_create_note.xml
        val title = mood_title.text.toString()
        val text = note_text.text.toString()

        val note = Note(0, title, text, reactionImageChosen)
        myNoteViewModel.addNote(note)
        Toast.makeText(requireContext(), "Successfully Added Note!", Toast.LENGTH_LONG).show()

        findNavController().navigate(R.id.action_createNoteFragment_to_noteFeedFragment)
    }


    override fun onClick(p0: View?) {

        val happyButton: Button = requireView().findViewById(R.id.happy_button)
        val sadButton: Button = requireView().findViewById(R.id.sad_button)
        val contentButton: Button = requireView().findViewById(R.id.content_button)
        val anxiousButton: Button = requireView().findViewById(R.id.anxious_button)
        val madButton: Button = requireView().findViewById(R.id.mad_button)
        val neutralButton: Button = requireView().findViewById(R.id.neutral_button)

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

