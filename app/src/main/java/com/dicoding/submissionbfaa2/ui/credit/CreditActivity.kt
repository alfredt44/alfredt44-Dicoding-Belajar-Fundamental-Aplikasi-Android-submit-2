package com.dicoding.submissionbfaa2.ui.credit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.viewbinding.library.activity.viewBinding
import com.dicoding.submissionbfaa2.R
import com.dicoding.submissionbfaa2.databinding.ActivityCreditBinding

class CreditActivity : AppCompatActivity() {
    private val binding : ActivityCreditBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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