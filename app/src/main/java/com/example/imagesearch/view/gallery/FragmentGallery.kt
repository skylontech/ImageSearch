package com.example.imagesearch.view.gallery

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearch.R
import com.example.imagesearch.data.repo.ResourceResult
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FragmentGallery : DaggerFragment(R.layout.fragment_gallery) {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private lateinit var recyclerView: RecyclerView

    private lateinit var loadingView: ProgressBar

    private lateinit var errorView: TextView

    private val photoAdapter = PhotoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = photoAdapter

        loadingView = view.findViewById(R.id.progress_bar)

        errorView = view.findViewById(R.id.text_view_empty)

        val viewModel = ViewModelProvider(this, viewModelProviderFactory)[GalleryViewModel::class.java]
        viewModel.photosLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ResourceResult.Loading -> {
                    recyclerView.visibility = View.GONE
                    loadingView.visibility = View.VISIBLE
                    errorView.visibility = View.GONE
                }
                is ResourceResult.Success -> {
                    recyclerView.visibility = View.VISIBLE
                    loadingView.visibility = View.GONE
                    errorView.visibility = View.GONE
                    photoAdapter.submitList(response.result.results)
                }
                else -> {
                    recyclerView.visibility = View.GONE
                    loadingView.visibility = View.GONE
                    errorView.visibility = View.VISIBLE
                }
            }
        }
    }

}