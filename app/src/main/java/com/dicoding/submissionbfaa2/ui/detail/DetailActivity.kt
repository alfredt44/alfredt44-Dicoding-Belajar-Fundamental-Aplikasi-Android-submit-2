package com.dicoding.submissionbfaa2.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.dicoding.submissionbfaa2.R
import com.dicoding.submissionbfaa2.databinding.ActivityDetailBinding
import com.dicoding.submissionbfaa2.ui.credit.CreditActivity
import com.dicoding.submissionbfaa2.ui.detail.viewpager.ViewPagerAdapter
import com.dicoding.submissionbfaa2.ui.detail.viewpager.ViewPagerAdapter.Companion.FOLLOWERS
import com.dicoding.submissionbfaa2.ui.detail.viewpager.ViewPagerAdapter.Companion.FOLLOWING
import com.dicoding.submissionbfaa2.ui.main.MainViewModel
import com.dicoding.submissionbfaa2.util.Resource
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel : DetailViewModel by viewModels()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent.getStringExtra("username").let { username ->
            title = username
            viewModel.username = username
            viewModel.getDetail(username).observe(this,{
               when(it){
                   is Resource.Success -> {
                      val data = it.data!!
                       with(binding){
                           progressBar.visibility = View.GONE
                           Glide.with(this@DetailActivity).load(data.avatarUrl).into(udDetailAvatar)
                           udDetailName.text = data.name
                           udDetailUsername.text = data.login
                           udDetailRepository.text = "${data.publicRepos} Repository"
                           if (!data.location.isNullOrBlank()){
                               detailLocation.visibility = View.VISIBLE
                               detailLocation.text = "Location : ${data.location}"
                           }else{
                               detailLocation.visibility = View.GONE
                           }
                       }
                   }
                   is Resource.Failure -> {
                       binding.progressBar.visibility = View.GONE
                       Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                   }
               }
            })
        }
        setupViewPager()
    }
    private fun setupViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(this)
        val viewPager2 = binding.udViewPager
        viewPager2.adapter = viewPagerAdapter
        val tabLayout: TabLayout = binding.udTabs
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                FOLLOWERS -> tab.text = getString(R.string.followers)
                FOLLOWING -> tab.text = getString(R.string.following)
            }
        }.attach()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
            R.id.menu_change_language -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }
            R.id.Credit -> {

                startActivity(Intent(this, CreditActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}