package com.android.example.moods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.moods.data.Note
import com.android.example.moods.data.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_note_feed.*
import kotlinx.android.synthetic.main.fragment_note_feed.view.*

class NoteFeedFragment : Fragment() {

    private var noteList = mutableListOf<Note>()
    private lateinit var myNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addMoodButton: FloatingActionButton = view.findViewById(R.id.addMood)

        val noteAdapter = NoteListAdapter(noteList)
        val recyclerView = view.notes_recycler_view
        notes_recycler_view.adapter = noteAdapter

        val emptyState: ConstraintLayout = view.findViewById(R.id.empty_state)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        myNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        myNoteViewModel.getAllNotes.observe(viewLifecycleOwner, Observer {
            note -> noteAdapter.setData(note, emptyState)
        })


        addMoodButton.setOnClickListener{
            findNavController().navigate(R.id.action_noteFeedFragment_to_createNoteFragment)
        }
    }
}