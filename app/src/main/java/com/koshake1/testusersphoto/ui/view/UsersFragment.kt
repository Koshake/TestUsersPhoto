package com.koshake1.testusersphoto.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.koshake1.testusersphoto.databinding.FragmentUsersBinding
import com.koshake1.testusersphoto.model.data.user.UserResponse
import com.koshake1.testusersphoto.model.data.viewstate.BaseState
import com.koshake1.testusersphoto.ui.adapter.OnListItemClickListener
import com.koshake1.testusersphoto.ui.adapter.UsersAdapter
import com.koshake1.testusersphoto.viewmodel.UsersViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment<FragmentUsersBinding, UserResponse, UsersViewModel>() {

    override var bindingNullable: FragmentUsersBinding? = null

    override val viewModel: UsersViewModel by viewModel()

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

        viewModel.getUsers()
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingNullable = null
    }

    private fun initAdapter() {
        if (adapter == null) {
            adapter = UsersAdapter(clickListener = object : OnListItemClickListener {
                override fun onItemClick(id: Int) {
                    (activity as MainActivity).navigateTo(PhotosFragment.newInstance(id))
                }
            })
        }
        binding.mainRecycler.adapter = adapter
    }

    override fun renderSuccess(data: UserResponse) {
        adapter?.let {
            it.clear()
            it.fillList(data.users)
        }
        super.renderSuccess(data)
    }

    override fun renderError(error: Throwable) {
        error.message?.let { showMessage(it) }
        super.renderError(error)
    }

    override fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoading() {
        binding.progressBarUsers.show()
    }

    private fun hideLoading() {
        if (binding.progressBarUsers.isShown) {
            binding.progressBarUsers.hide()
        }
    }

    companion object {
        fun newInstance() = UsersFragment()
    }
}