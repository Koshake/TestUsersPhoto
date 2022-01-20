package com.koshake1.testusersphoto.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.koshake1.testusersphoto.model.data.viewstate.AppStateEntity
import com.koshake1.testusersphoto.model.data.viewstate.BaseState
import com.koshake1.testusersphoto.viewmodel.BaseViewModel

abstract class BaseFragment<VB : ViewBinding, D : AppStateEntity, VM : BaseViewModel<D>> :
    Fragment() {

    protected abstract var bindingNullable: VB?
    protected val binding get() = bindingNullable!!
    abstract val viewModel: VM

    protected open fun renderData(state: BaseState<D>) {
        when (state) {
            is BaseState.Success -> renderSuccess(state.data)
            is BaseState.Error -> renderError(state.error)
            is BaseState.Loading -> setLoading(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
    }

    protected open fun renderSuccess(data: D) {
        setLoading(false)
    }

    protected open fun renderError(error: Throwable) {
        setLoading(false)
    }

    protected open fun renderMessage(message: String) {
        setLoading(false)
    }

    protected open fun setLoading(isLoading: Boolean) {

    }
}