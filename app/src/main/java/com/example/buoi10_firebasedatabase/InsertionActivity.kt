package com.example.buoi10_firebasedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_insertion.*

class InsertionActivity : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")
        btnSave.setOnClickListener {
            saveEmployeeData()
        }
    }

    private fun saveEmployeeData() {
        val empName = edtEmpName.text.toString()
        val empAge = edtEmpAge.text.toString()
        val empSalary = edtEmpSalary.text.toString()

        val empId = dbRef.push().key!!
        val employee = EmployeeModel(empId, empName, empAge, empSalary)

        if(empName.isEmpty()){
            edtEmpName.error = "Please enter name"
            return
        }
        if(empAge.isEmpty()){
            edtEmpAge.error = "Please enter age"
            return
        }
        if(empSalary.isEmpty()){
            edtEmpSalary.error = "Please enter salary"
            return
        }

        dbRef.child(empId).setValue(employee).addOnCompleteListener {
            Toast.makeText(this, "Data insert thanh cong", Toast.LENGTH_SHORT).show()
            edtEmpName.setText("")
            edtEmpAge.setText("")
            edtEmpSalary.setText("")
        }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
            }
    }
}