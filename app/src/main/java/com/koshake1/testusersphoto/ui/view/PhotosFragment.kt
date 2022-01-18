package com.koshake1.testusersphoto.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.koshake1.testusersphoto.R
import com.koshake1.testusersphoto.databinding.FragmentPhotosBinding

class PhotosFragment : Fragment(R.layout.fragment_photos) {

    private var bindingNullable: FragmentPhotosBinding? = null

    private val binding get() = bindingNullable!!

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

    companion object {
        fun newInstance() = PhotosFragment()
    }
}