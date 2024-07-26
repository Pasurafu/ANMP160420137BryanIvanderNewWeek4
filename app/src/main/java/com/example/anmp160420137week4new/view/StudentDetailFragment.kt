package com.example.anmp160420137week4new.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.anmp160420137week4new.R
import com.example.anmp160420137week4new.databinding.FragmentStudentDetailBinding
import com.example.anmp160420137week4new.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding
    private var studentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            studentId = StudentDetailFragmentArgs.fromBundle(it).studentID
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
        studentId?.let {
            viewModel.fetch(it)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer { student ->
            student?.let {
                binding.textInputEditTextStudentId.setText(it.id)
                binding.textInputEditTextStudentName.setText(it.name)
                binding.textInputEditTextBirthDate.setText(it.bod)
                binding.textInputEditTextPhone.setText(it.phone)

                Picasso.get().load(it.photoUrl).into(binding.imgPhoto)

                val btnUpdate = view?.findViewById<Button>(R.id.buttonUpdate)
                btnUpdate?.setOnClickListener {
                    Observable.timer(5, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            Log.d("Messages", "five seconds")
                            MainActivity.showNotification(
                                student.name.toString(),
                                "A new notification created",
                                R.drawable.baseline_person_add_24
                            )
                        }
                }
            }
        })
    }

    companion object {
        // Optional: Define constants or factory methods if needed
    }
}
