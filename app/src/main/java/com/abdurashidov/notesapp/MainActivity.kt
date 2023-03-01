package com.abdurashidov.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.abdurashidov.notesapp.database.NoteDatabase
import com.abdurashidov.notesapp.databinding.ActivityMainBinding
import com.abdurashidov.notesapp.repository.NoteRepository
import com.abdurashidov.notesapp.viewmodel.NoteViewModel
import com.abdurashidov.notesapp.viewmodel.NoteViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))

        val viewModelProviderFactory = NoteViewModelProviderFactory(
            application,
            noteRepository
        )

        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]

    }
}