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
import com.abdurashidov.notesapp.databinding.FragmentAddNoteBinding
import com.abdurashidov.notesapp.models.Note
import com.abdurashidov.notesapp.utils.MyData
import com.abdurashidov.notesapp.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar

class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding
    lateinit var viewModel: NoteViewModel
    var readStatus = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(layoutInflater)

        viewModel = (activity as MainActivity).viewModel

        binding.apply {
            back.setOnClickListener { findNavController().popBackStack() }
            read.setOnClickListener { setReadStatus() }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        initViews(view)
    }


    private fun initViews(view: View) {
        binding.apply {
            save.setOnClickListener {
               saveNote(view)
            }
        }
    }

    private fun setReadStatus() {
        binding.apply {
            if (readStatus) {
                title.isEnabled = true
                text.isEnabled = true
                readStatus = false
            } else {
                title.isEnabled = false
                text.isEnabled = false
                readStatus = true
            }
        }
    }

    private fun saveNote(view: View){
        binding.apply {
            val title = title.text.toString()
            val text = text.text.toString()
            if (title != "" && text != "") {
                val note = Note(id = 0, noteTitle = title, noteBody = text)
                viewModel.addNote(note)
                Snackbar.make(view, "Note saved successfully", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Snackbar.make(view, "Please enter note!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}