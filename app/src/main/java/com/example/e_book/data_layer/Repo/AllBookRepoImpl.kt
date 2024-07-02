package com.example.e_book.data_layer.Repo

import com.example.e_book.BookModel
import com.example.e_book.ResultState
import com.example.e_book.domain_layer.AllBookRepo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AllBookRepoImpl @Inject constructor(private var firebaseDatabase: FirebaseDatabase) :
    AllBookRepo {
    override fun getAllBook(): Flow<ResultState<List<BookModel>>> = callbackFlow {
        trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items: List<BookModel> = emptyList()
                items = snapshot.children.map { value ->
                    value.getValue<BookModel>()!!
                }
                trySend(ResultState.Success(items))
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }

        firebaseDatabase.reference.child("Books").addValueEventListener(
            valueEvent
        )
        awaitClose {
            firebaseDatabase.reference.removeEventListener(valueEvent)
            close()
        }
    }
}