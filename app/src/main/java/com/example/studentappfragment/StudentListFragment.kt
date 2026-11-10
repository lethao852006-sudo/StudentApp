package com.example.studentappfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.core.os.bundleOf
import com.example.studentappfragment.R
import com.example.studentappfragment.databinding.ActivityStudentListFragmentBinding


class StudentListFragment : Fragment() {

    private var _binding: ActivityStudentListFragmentBinding? = null
    private val binding get() = _binding!!

    private val studentList = listOf(
        Student(2026101, "Nguyễn Văn An", "K22 - CNTT", 3.85),
        Student(2026102, "Trần Thị Bích", "K22 - KTPM", 3.60),
        Student(2026103, "Lê Hoàng Cường", "K22 - HTTT", 3.92),
        Student(2026104, "Phạm Minh Dũng", "K22 - MMT", 3.45)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityStudentListFragmentBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val displayItems = studentList.map {
            "${it.id} - ${it.name} (${it.className})"
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            displayItems
        )

        binding.lvStudents.adapter = adapter

        binding.lvStudents.setOnItemClickListener { _, _, position, _ ->

            val selectedStudent = studentList[position]

            parentFragmentManager.setFragmentResult(
                "STUDENT_KEY",
                bundleOf(
                    "ID" to selectedStudent.id,
                    "NAME" to selectedStudent.name,
                    "CLASS" to selectedStudent.className,
                    "GPA" to selectedStudent.gpa
                )
            )

            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container,
                    StudentDetailFragment()
                )
                addToBackStack(null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}