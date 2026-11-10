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

    // Danh sách sinh viên mẫu
    private val studentList = listOf(
        Student(
            id = 2026101,
            name = "Nguyễn Văn An",
            className = "K22CNTT1",
            gpa = 3.8
        ),
        Student(
            id = 2026102,
            name = "Trần Thị Bích",
            className = "K22CNTT1",
            gpa = 3.6
        ),
        Student(
            id = 2026103,
            name = "Lê Hoàng Cường",
            className = "K22CNTT2",
            gpa = 3.5
        ),
        Student(
            id = 2026104,
            name = "Phạm Minh Dũng",
            className = "K22CNTT2",
            gpa = 3.9
        )
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

        hienThiDanhSachSinhVien()
    }

    private fun hienThiDanhSachSinhVien() {

        // Tạo dữ liệu hiển thị cho ListView
        val danhSachHienThi = studentList.map { student ->
            "${student.id} - ${student.name}"
        }

        // Tạo Adapter kết nối dữ liệu với ListView
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            danhSachHienThi
        )

        binding.lvStudents.adapter = adapter

        // Xử lý sự kiện khi chọn một sinh viên
        binding.lvStudents.setOnItemClickListener { _, _, position, _ ->

            val student = studentList[position]

            val detailFragment = StudentDetailFragment.newInstance(student)

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    detailFragment
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