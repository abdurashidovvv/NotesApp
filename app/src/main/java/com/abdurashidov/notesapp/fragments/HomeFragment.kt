package com.abdurashidov.notesapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.abdurashidov.notesapp.MainActivity
import com.abdurashidov.notesapp.R
import com.abdurashidov.notesapp.adapters.NoteAdapter
import com.abdurashidov.notesapp.databinding.DialogItemBinding
import com.abdurashidov.notesapp.databinding.FragmentHomeBinding
import com.abdurashidov.notesapp.models.Note
import com.abdurashidov.notesapp.utils.MyData
import com.abdurashidov.notesapp.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar

@Suppress("CAST_NEVER_SUCCEEDS")
class HomeFragment : Fragment(), NoteAdapter.ItemViewClick {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: NoteViewModel
    private  val TAG = "HomeFragment"
    lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
        initViews(view)
//        initSearchView()
    }


    private fun initViews(view: View) {
        noteAdapter= NoteAdapter(this)
        setupRv()
        setupViewModel()

        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment)
        }
        binding.info.setOnClickListener {
            val dialogItemBinding=DialogItemBinding.inflate(layoutInflater)
            val dialog=AlertDialog.Builder(binding.root.context).create()
            dialog.setView(dialogItemBinding.root)
            dialogItemBinding.closeBtn.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    private fun setupRv() = binding.recyclerView.apply{
        layoutManager=StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        setHasFixedSize(true)
        adapter=noteAdapter
    }

    private fun setupViewModel() {
        activity?.let {
            viewModel.getAllNote().observe(viewLifecycleOwner){list->
                noteAdapter.submitList(list)

                binding.apply {
                    recyclerView.isVisible=list.isNotEmpty()
                    imgIsEmpty.isVisible=list.isEmpty()
                }
            }
        }
    }


    override fun onClick(note: Note) {
        MyData.note=note
        findNavController().navigate(R.id.updateNoteFragment)
    }



}