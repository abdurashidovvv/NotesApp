package com.abdurashidov.notesapp.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.abdurashidov.notesapp.MainActivity
import com.abdurashidov.notesapp.R
import com.abdurashidov.notesapp.databinding.FragmentUpdateNoteBinding
import com.abdurashidov.notesapp.models.Note
import com.abdurashidov.notesapp.utils.MyData
import com.abdurashidov.notesapp.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar

class UpdateNoteFragment : Fragment() {

    lateinit var binding: FragmentUpdateNoteBinding
    lateinit var currentNote:Note
    lateinit var viewModel: NoteViewModel
    var readStatus=true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentUpdateNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentNote= MyData.note!!
        viewModel=(activity as MainActivity).viewModel
        initViews(view)
    }

    private fun initViews(view: View) {
        binding.apply {
            title.setText(currentNote.noteTitle)
            text.setText(currentNote.noteBody)

            save.setOnClickListener {
                updateNote(view)
            }

            back.setOnClickListener { findNavController().popBackStack() }
            read.setOnClickListener {setReadStatus(view)}
            delete.setOnClickListener { deleteNote(view) }
        }
    }

    private fun deleteNote(view: View) {
        viewModel.deleteNote(currentNote)
        Snackbar.make(view, "Note is deleted", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    private fun updateNote(view: View) {
        binding.apply {
            val noteTitle=title.text.toString().trim()
            val noteBody=text.text.toString().trim()
            if (noteTitle.isNotEmpty() && noteBody.isNotEmpty()){
                val note = Note(id = currentNote.id, noteTitle=noteTitle, noteBody = noteBody)
                viewModel.updateNote(note)
                Snackbar.make(view, "Note updated", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }else{
                Snackbar.make(view, "Please enter note", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setReadStatus(view: View) {
        binding.apply {
            if (readStatus) {
                title.isEnabled = true
                text.isEnabled = true
                readStatus = false
                Snackbar.make(view, "Reading mode is inactive", Toast.LENGTH_SHORT).show()
            } else {
                title.isEnabled = false
                text.isEnabled = false
                readStatus = true
                read.setBackgroundColor(Color.GRAY)
                Snackbar.make(view, "Reading mode is active", Toast.LENGTH_SHORT).show()
            }
        }
    }
}