package com.example.android.databinding.basicsample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.ProfileLiveDataViewModel
import com.example.android.databinding.basicsample.data.ProfileObservableViewModel
import com.example.android.databinding.basicsample.databinding.LivedataViewmodelProfileBinding
import com.example.android.databinding.basicsample.databinding.ObservableViewmodelProfileBinding

class ObservableViewModelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // An alternative ViewModel using Observable fields and @Bindable properties can be used:
        val viewModel = ViewModelProviders.of(this).get(ProfileObservableViewModel::class.java)

        // Obtain binding
        val binding: ObservableViewmodelProfileBinding = DataBindingUtil.setContentView(this, R.layout.observable_viewmodel_profile)

        // Bind layout with ViewModel
        binding.viewmodel = viewModel

        // LiveData needs the lifecycle owner
        binding.lifecycleOwner = this
    }
}