package com.example.imagesearch.view.gallery

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
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

    private lateinit var edtSearchBox : EditText

    private val photoAdapter = PhotoAdapter()

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = photoAdapter

        loadingView = view.findViewById(R.id.progress_bar)

        errorView = view.findViewById(R.id.text_view_empty)

        edtSearchBox = view.findViewById(R.id.edt_search)

        edtSearchBox.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val query = edtSearchBox.text.toString()
                if(query.isNotEmpty()) {
                    galleryViewModel.searchPhotos(query)
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        galleryViewModel = ViewModelProvider(this, viewModelProviderFactory)[GalleryViewModel::class.java]
        galleryViewModel.photosLiveData.observe(viewLifecycleOwner) { response ->
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