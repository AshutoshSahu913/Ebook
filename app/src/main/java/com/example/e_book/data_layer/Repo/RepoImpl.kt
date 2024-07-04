package com.example.e_book.data_layer.Repo

import android.util.Log
import com.example.e_book.BookCategory
import com.example.e_book.BookModel
import com.example.e_book.ResultState
import com.example.e_book.domain_layer.Repo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RepoImpl @Inject constructor(private var firebaseDatabase: FirebaseDatabase) :
    Repo {
    override fun getAllBook(): Flow<ResultState<List<BookModel>>> = callbackFlow {
        trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items: List<BookModel> = emptyList()
                items = snapshot.children.map { value ->
                    value.getValue<BookModel>()!!
                }
                Log.d("BOOK", "RepoImpl:------------------${items} ")
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

    override fun getBooksByCategory(category: String): Flow<ResultState<List<BookModel>>> =
        callbackFlow {
            trySend(ResultState.Loading)
            val valueEvent = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var items: List<BookModel> = emptyList()
                    items = snapshot.children.filter {
                        it.getValue<BookModel>()!!.bookCategory == category
                    }.map { value ->
                        value.getValue<BookModel>()!!
                    }
                    Log.d("BOOK", "RepoImpl:------------------${items} ")
                    trySend(ResultState.Success(items))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(ResultState.Error(error.toException()))
                }
            }

            firebaseDatabase.reference.child("Books")
                .addValueEventListener(
                    valueEvent
                )
            awaitClose {
                firebaseDatabase.reference.removeEventListener(valueEvent)
                close()
            }
        }

    override fun getAllCategory(): Flow<ResultState<List<BookCategory>>> = callbackFlow {
        trySend(ResultState.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var categories: List<BookCategory> = emptyList()
                categories = snapshot.children.map { value ->
                    value.getValue<BookCategory>()!!
                }

                trySend(ResultState.Success(categories))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }
        }
        firebaseDatabase.reference.child("BookCategories").addValueEventListener(
            valueEvent
        )
        awaitClose {
            firebaseDatabase.reference.removeEventListener(valueEvent)
            close()
        }
    }
}