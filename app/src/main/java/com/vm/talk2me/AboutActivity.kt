package com.vm.talk2me

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import com.vm.talk2me.config.Config
import kotlinx.android.synthetic.main.toolbar.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Config.getCurrTheme(this))
        setContentView(R.layout.activity_about)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setTitle(R.string.toolbar_title_about)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
