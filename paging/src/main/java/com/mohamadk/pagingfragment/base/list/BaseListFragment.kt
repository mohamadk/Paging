package com.mohamadk.pagingfragment.base.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohamadk.middleman.AdapterProvider
import com.mohamadk.middleman.intractors.RetryListener
import com.mohamadk.middleman.networkstate.NetworkState
import com.mohamadk.pagingfragment.R
import com.mohamadk.pagingfragment.base.BaseFragmentOpenerFragment

abstract class BaseListFragment<LIST_MODEL, VIEW_MODEL : BaseListFragmentViewModel<LIST_MODEL>>
    : BaseFragmentOpenerFragment(), RetryListener {
    lateinit var adapterProvider: AdapterProvider<LIST_MODEL>

    abstract val viewModel: VIEW_MODEL
    private var lastLoadArgs: Array<Any> = arrayOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayout(), container, false)

        getRecycler(view).apply {
            layoutManager = LinearLayoutManager(context)
        }

        return view
    }

    open fun getRecycler(baseView: View): RecyclerView {
        return baseView as RecyclerView
    }

    open fun getLayout(): Int {
        return R.layout.fragment_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    fun loadItems(vararg args: Any) {
        lastLoadArgs = arrayOf(*args)
        viewModel.loadItems(*args)
    }


    private fun initAdapter() {
        adapterProvider = provideAdapter()

        getRecycler(view!!).apply {
            adapter = adapterProvider.getAdapter()
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.apply {
            items.observe(this@BaseListFragment, Observer {
                submitList(it)
            })

            refreshState.observe(this@BaseListFragment, Observer {
                setRefreshState(it)
            })

        }
    }

    open fun setNetworkState(it: NetworkState?) {
        adapterProvider.setNetworkState(it)
    }

    open fun setRefreshState(refreshState: NetworkState) {
        adapterProvider.setNetworkState(refreshState)
    }

    abstract fun submitList(it: LIST_MODEL?)

    abstract fun provideAdapter(): AdapterProvider<LIST_MODEL>

    override fun retry() {
        viewModel.retry(*lastLoadArgs)
    }


}