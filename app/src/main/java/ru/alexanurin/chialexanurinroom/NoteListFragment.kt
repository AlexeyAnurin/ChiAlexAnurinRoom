package ru.alexanurin.chialexanurinroom

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_note_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.alexanurin.chialexanurinroom.database.Notes
import ru.alexanurin.chialexanurinroom.databinding.FragmentNoteListBinding
import ru.alexanurin.chialexanurinroom.rest.Channel


@KoinApiExtension
class NoteListFragment : Fragment(), KoinComponent {

    //  Dependency injections.
    private val noteViewModel: NoteViewModel by viewModel()
    private val sharedPref: SharedPreferences = get()
    //  ViewBinding.
    private lateinit var binding: FragmentNoteListBinding
    //  Экземпляр адаптера c реализацией listener, переданного в качестве аргумента.
    private val noteAdapter = NoteAdapter { noteViewModel.deleteNote(it) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
        //  Присвоить адаптер ресайклу.
        binding.rvNotes.adapter = noteAdapter
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  Присвоить значение счётчикам при новом запуске приложения.
        binding.tvCounterAddedNotes.text = sharedPref.getInt(SAVED_NOTES, 0).toString()
        binding.tvCounterDeletedNotes.text = sharedPref.getInt(DELETED_NOTES, 0).toString()

        //  Навигация через navGraph.
        binding.addNoteFab.setOnClickListener {
            navigateToAddNote()
        }

        binding.btnChannels.setOnClickListener {
            navigateToChannelsList()
        }

        //  Livedata. Добавление заметок в список.
        noteViewModel.noteAddEvent.observe(viewLifecycleOwner, Observer {
            noteAdapter.setItems(it)
        })

        //  LiveData. Обновление счётчика удалённых заметок.
        noteViewModel.decrementEvent.observe(viewLifecycleOwner, Observer {
            tvCounterDeletedNotes.text = it.toString()
        })
    }

    private fun navigateToAddNote() {
        findNavController().navigate(R.id.addNoteFragment)
    }

    private fun navigateToChannelsList(){
        findNavController().navigate(R.id.channelFragment)
    }

    companion object {
        const val SAVED_NOTES = "saved_notes"
        const val DELETED_NOTES = "deleted_notes"
    }
}