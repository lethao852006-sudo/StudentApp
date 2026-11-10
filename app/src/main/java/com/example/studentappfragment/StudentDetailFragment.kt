package com.example.studentappfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studentappfragment.databinding.ActivityStudentDetailFragmentBinding

class StudentDetailFragment : Fragment() {

    private var _binding: ActivityStudentDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivityStudentDetailFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hienThiThongTin()

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun hienThiThongTin() {

        val maSV = arguments?.getString("id") ?: ""
        val hoTen = arguments?.getString("name") ?: ""
        val lop = arguments?.getString("className") ?: ""
        val gpa = arguments?.getDouble("gpa") ?: 0.0

        binding.tvStudentId.text = "Mã sinh viên: $maSV"
        binding.tvStudentName.text = "Họ và tên: $hoTen"
        binding.tvStudentClass.text = "Lớp: $lop"
        binding.tvStudentGpa.text = "GPA: $gpa"
    }

    companion object {

        fun newInstance(student: Student): StudentDetailFragment {

            val fragment = StudentDetailFragment()

            val bundle = Bundle().apply {
                putString("id", student.id)
                putString("name", student.name)
                putString("className", student.className)
                putDouble("gpa", student.gpa)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}