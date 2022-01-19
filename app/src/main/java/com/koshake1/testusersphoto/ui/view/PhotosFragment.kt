package com.koshake1.testusersphoto.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.koshake1.testusersphoto.USER_ID
import com.koshake1.testusersphoto.databinding.FragmentPhotosBinding
import com.koshake1.testusersphoto.model.data.photo.UserPhotos
import com.koshake1.testusersphoto.model.data.viewstate.BaseState
import com.koshake1.testusersphoto.ui.adapter.PhotosAdapter
import com.koshake1.testusersphoto.viewmodel.PhotosViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PhotosFragment : BaseFragment<FragmentPhotosBinding, UserPhotos, PhotosViewModel>() {

    override var bindingNullable: FragmentPhotosBinding? = null

    private var adapter : PhotosAdapter? = null

    override val viewModel: PhotosViewModel by viewModel()

    private val userId by lazy(LazyThreadSafetyMode.NONE) { arguments?.getInt(USER_ID) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNullable = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as MainActivity).setSupportActionBar(binding.toolbarPhoto)
        (activity as MainActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        initAdapter()

        userId?.let { viewModel.getPhotos(it) }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingNullable = null
    }

    private fun initAdapter() {
        if (adapter == null) {
            adapter = PhotosAdapter()
        }
        binding.photosRv.adapter = adapter
    }

    override fun renderData(state: BaseState<UserPhotos>) {
        hideLoading()
        super.renderData(state)
    }

    override fun renderSuccess(data: UserPhotos) {
        hideLoading()
        adapter?.let {
            it.clear()
            it.fillList(data.photos)
        }
    }

    override fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    private fun showLoading() {
        binding.progressBarPhotos.show()
    }

    private fun hideLoading() {
        if (binding.progressBarPhotos.isShown) {
            binding.progressBarPhotos.hide()
        }
    }

    companion object {
        fun newInstance(id : Int? = null) = PhotosFragment().also {
            id?.let { userId ->
                val arguments = Bundle()
                arguments.putInt(USER_ID, userId)
                it.arguments = arguments
            }
        }
    }
}