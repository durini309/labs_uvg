package com.durini.roomdemo.presentation.user_list

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.durini.roomdemo.R
import com.durini.roomdemo.data.local_source.Database
import com.durini.roomdemo.domain.model.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserListFragment : Fragment(R.layout.fragment_user_list), UserAdapter.UserClickListeners {

    private lateinit var fabCreateUser: FloatingActionButton
    private lateinit var recyclerUsers: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var database: Database
    private lateinit var userList: List<User>
    private lateinit var userAdapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerUsers = view.findViewById(R.id.recycler_users)
        progressBar = view.findViewById(R.id.progress_users)
        fabCreateUser = view.findViewById(R.id.fab_mainActivity_createUser)
        database = Room.databaseBuilder(
            requireContext(),
            Database::class.java,
            "dbname"
        ).build()

        getUsers()
        setListeners()
    }

    private fun setListeners() {
        fabCreateUser.setOnClickListener {
            requireView().findNavController().navigate(
                UserListFragmentDirections.actionUserListFragmentToUserFragment()
            )
        }
    }

    private fun getUsers() {
        progressBar.visibility = View.VISIBLE
        recyclerUsers.visibility = View.GONE
        CoroutineScope(Dispatchers.IO).launch {
            val users = database.userDao().getUsers()
            userList = users
            CoroutineScope(Dispatchers.Main).launch {
                progressBar.visibility = View.GONE
                setupRecycler()
            }
        }
    }

    private fun setupRecycler() {
        userAdapter = UserAdapter(userList, this)
        recyclerUsers.apply {
            visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    override fun onEditClicked(user: User) {
        requireView().findNavController().navigate(
            UserListFragmentDirections.actionUserListFragmentToUserFragment(
                id = user.id ?: -1
            )
        )
    }

    override fun onDeleteClicked(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            val rowsDeleted = database.userDao().delete(user)
            CoroutineScope(Dispatchers.Main).launch {
                if (rowsDeleted > 0) {
                    Toast.makeText(
                        requireContext(),
                        "Usuario eliminado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    getUsers()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No se elimino usuario con ID ${user.id}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

}