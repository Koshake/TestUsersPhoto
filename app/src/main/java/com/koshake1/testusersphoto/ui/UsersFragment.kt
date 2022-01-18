package com.koshake1.testusersphoto.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.koshake1.testusersphoto.R
import com.koshake1.testusersphoto.databinding.FragmentUsersBinding
import com.koshake1.testusersphoto.model.data.user.UserResponse
import com.koshake1.testusersphoto.model.data.viewstate.BaseState
import com.koshake1.testusersphoto.ui.adapter.OnListItemClickListener
import com.koshake1.testusersphoto.ui.adapter.UsersAdapter
import com.koshake1.testusersphoto.viewmodel.UsersViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class UsersFragment : Fragment(R.layout.fragment_users) {

    private var bindingNullable: FragmentUsersBinding? = null

    private val binding get() = bindingNullable!!

    private val usersViewModel: UsersViewModel by viewModel()

    private var adapter: UsersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNullable = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        usersViewModel.getUsers()
        usersViewModel.stateLiveData.observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingNullable = null
    }

    private fun initAdapter() {
        if (adapter == null) {
            adapter = UsersAdapter(clickListener = object : OnListItemClickListener {
                override fun onItemClick(id: Int) {
                }
            })
        }
        binding.mainRecycler.adapter = adapter
    }

    private fun renderData(state: BaseState<UserResponse>?) {
        when (state) {
            is BaseState.Success -> renderSuccess(state.data)
            is BaseState.Error -> renderError(state.error)
            is BaseState.Loading -> setLoading(true)
        }
    }

    private fun renderSuccess(userResponse: UserResponse) {
        hideLoading()
        adapter?.let {
            it.clear()
            it.fillList(userResponse.users)
        }
    }

    private fun renderError(error: Throwable) {
        error.message?.let { showMessage(it) }
    }

    private fun setLoading(loading: Boolean) {
        if (loading) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    private fun showLoading() {
        binding.progressBarUsers.show()
    }

    private fun hideLoading() {
        if (binding.progressBarUsers.isShown) {
            binding.progressBarUsers.hide()
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        fun newInstance() = UsersFragment()
    }
}