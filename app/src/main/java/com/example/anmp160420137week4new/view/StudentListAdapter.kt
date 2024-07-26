package com.example.anmp160420137week4new.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp160420137week4new.R
import com.example.anmp160420137week4new.databinding.StudentListItemBinding
import com.example.anmp160420137week4new.model.Student
import com.squareup.picasso.Picasso

class StudentListAdapter(val studentList: ArrayList<Student>)
    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtID: TextView = view.findViewById(R.id.txtID)
        val txtName: TextView = view.findViewById(R.id.txtName)
        val btnDetail: Button = view.findViewById(R.id.btnDetail)
        val imgPhoto: ImageView = view.findViewById(R.id.imgPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.txtID.text = studentList[position].id
        holder.txtName.text = studentList[position].name
        holder.btnDetail.setOnClickListener {
            studentList[position].id?.let { id ->
                val action = StudentListFragmentDirections.studentListtoStudentDetailFragment(id)
                Navigation.findNavController(it).navigate(action)
            }
        }

        val picasso = Picasso.Builder(holder.itemView.context).listener { _, _, exception ->
            exception.printStackTrace()
        }.build()
        picasso.load(studentList[position].photoUrl).into(holder.imgPhoto)
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}
