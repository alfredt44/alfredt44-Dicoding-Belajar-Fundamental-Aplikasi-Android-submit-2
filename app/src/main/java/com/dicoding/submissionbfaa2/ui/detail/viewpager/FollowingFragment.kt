package com.dicoding.submissionbfaa2.ui.detail.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submissionbfaa2.R
import com.dicoding.submissionbfaa2.databinding.FragmentFollowerBinding
import com.dicoding.submissionbfaa2.databinding.FragmentFollowingBinding
import com.dicoding.submissionbfaa2.ui.adapter.UserAdapter
import com.dicoding.submissionbfaa2.ui.detail.DetailViewModel
import com.dicoding.submissionbfaa2.util.Resource

class FollowingFragment : Fragment() {
    private val binding : FragmentFollowingBinding by viewBinding()
    private val viewModel : DetailViewModel by activityViewModels()
    private var adapter = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recycleView.layoutManager = LinearLayoutManager(context)
            recycleView.adapter = this@FollowingFragment.adapter
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getListFollowing().observe(viewLifecycleOwner,{
                when (it) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.setList(it.data!!)
                    }
                    is Resource.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

}