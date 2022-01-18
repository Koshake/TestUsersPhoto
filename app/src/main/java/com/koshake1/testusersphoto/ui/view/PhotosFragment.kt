package com.koshake1.testusersphoto.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.koshake1.testusersphoto.R
import com.koshake1.testusersphoto.USER_ID
import com.koshake1.testusersphoto.databinding.FragmentPhotosBinding
import com.koshake1.testusersphoto.model.data.photo.UserPhotos
import com.koshake1.testusersphoto.model.data.viewstate.BaseState
import com.koshake1.testusersphoto.model.image.ImageLoader
import com.koshake1.testusersphoto.ui.adapter.PhotosAdapter
import com.koshake1.testusersphoto.viewmodel.PhotosViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PhotosFragment : Fragment(R.layout.fragment_photos) {

    private var bindingNullable: FragmentPhotosBinding? = null

    private val binding get() = bindingNullable!!

    private var adapter : PhotosAdapter? = null

    private val imageLoader : ImageLoader<ImageView> by inject()

    private val photosViewModel : PhotosViewModel by viewModel()

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

        userId?.let { photosViewModel.getPhotos(it) }

        photosViewModel.stateLiveData.observe(viewLifecycleOwner) {
            renderData(it)
        }
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
            adapter = PhotosAdapter(imageLoader = imageLoader)
        }
        binding.photosRv.adapter = adapter
    }

    private fun renderData(state: BaseState<UserPhotos>?) {
        when (state) {
            is BaseState.Success -> renderSuccess(state.data)
            is BaseState.Error -> renderError(state.error)
            is BaseState.Loading -> setLoading(state.isLoading)
        }
    }

    private fun renderSuccess(photos: UserPhotos) {
        hideLoading()
        adapter?.let {
            it.clear()
            it.fillList(photos.photos)
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

    }

    private fun hideLoading() {

    }

    private fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
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