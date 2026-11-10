package com.example.studentappfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.studentappfragment.databinding.ActivityStudentListFragmentBinding

class StudentListFragment : Fragment() {

    private var _binding: ActivityStudentListFragmentBinding? = null
    private val binding get() = _binding!!

    private val studentList = listOf(
        Student("2026101", "Nguyễn Văn An", "K22A", 3.85),
        Student("2026102", "Trần Thị Bích", "K22B", 3.60),
        Student("2026103", "Lê Hoàng Cường", "K22A", 3.45),
        Student("2026104", "Phạm Minh Dũng", "K22C", 3.90)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityStudentListFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hienThiDanhSach()
    }

    private fun hienThiDanhSach() {

        val danhSachHienThi = studentList.map {
            "${it.id} - ${it.name}\nLớp: ${it.className} | GPA: ${it.gpa}"
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            danhSachHienThi
        )

        binding.lvStudents.adapter = adapter

        binding.lvStudents.setOnItemClickListener { _, _, position, _ ->

            val student = studentList[position]

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    StudentDetailFragment.newInstance(student)
                )
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}