package com.example.e_book.data_layer.Network

import com.example.e_book.BookModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import javax.inject.Inject

class GetAllCategories @Inject constructor(var firebaseDatabase: FirebaseDatabase) {
    fun getAllCategory() {
        val categoriesListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val category = snapshot.getValue<BookModel>()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        firebaseDatabase.reference.child("BookCategory").addValueEventListener(categoriesListener)
    }
}