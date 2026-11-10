package com.example.studentappfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studentappfragment.databinding.ActivityStudentDetailFragmentBinding
import java.util.Locale

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

        hienThiThongTinSinhVien()
        xuLySuKien()
    }

    // Nhận dữ liệu sinh viên từ StudentListFragment
    private fun hienThiThongTinSinhVien() {

        val id = arguments?.getInt("id") ?: 0

        val name = arguments?.getString("name") ?: ""

        val className = arguments?.getString("className") ?: ""

        val gpa = arguments?.getDouble("gpa") ?: 0.0

        // Hiển thị thông tin lên giao diện
        binding.tvStudentId.text = "Mã sinh viên: $id"

        binding.tvStudentName.text = "Họ và tên: $name"

        binding.tvStudentClass.text = "Lớp: $className"

        binding.tvStudentGpa.text = String.format(
            Locale.getDefault(),
            "GPA: %.2f",
            gpa
        )
    }

    // Xử lý nút quay lại
    private fun xuLySuKien() {

        binding.btnBack.setOnClickListener {

            parentFragmentManager.popBackStack()
        }
    }

    companion object {

        fun newInstance(student: Student): StudentDetailFragment {

            val fragment = StudentDetailFragment()

            val bundle = Bundle().apply {

                putInt("id", student.id)

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