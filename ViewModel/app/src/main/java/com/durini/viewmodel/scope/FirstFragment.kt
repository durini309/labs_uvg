package com.durini.viewmodel.scope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.durini.viewmodel.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val scopeViewModel: ScopeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() {
        binding.buttonFirstFragmentSubmit.setOnClickListener {
            scopeViewModel.updateUsername(
                binding.inputFirstFragmentName.editText!!.text.toString()
            )
            binding.inputFirstFragmentName.editText!!.text.clear()
            Toast.makeText(requireContext(), "Nombre actualizado", Toast.LENGTH_SHORT).show()
        }

        binding.buttonFirstFragmentNext.setOnClickListener {
            requireView().findNavController().navigate(
                FirstFragmentDirections.actionFirstFragmentToSecondFragment()
            )
        }
    }
}