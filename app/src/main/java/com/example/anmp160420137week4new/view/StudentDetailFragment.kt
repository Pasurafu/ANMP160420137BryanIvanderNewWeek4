package com.example.anmp160420137week4new.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.anmp160420137week4new.databinding.FragmentStudentDetailBinding
import com.example.anmp160420137week4new.viewmodel.DetailViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Handle arguments if necessary
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer { student ->
            student?.let {
                binding.textInputEditTextStudentId.setText(student.id)
                binding.textInputEditTextStudentName.setText(student.name)
                binding.textInputEditTextBirthDate.setText(student.dob)
                binding.textInputEditTextPhone.setText(student.phone)
            }
        })
    }

    companion object {
        // Optional: Define constants or factory methods if needed
    }
}
