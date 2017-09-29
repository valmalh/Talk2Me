package com.vm.talk2me

import android.content.Intent
import android.graphics.Typeface
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem

import com.vm.talk2me.adapter.RCAdapter
import com.vm.talk2me.api.ApiClient
import com.vm.talk2me.api.ApiService
import com.vm.talk2me.config.Config
import com.vm.talk2me.model.Message
import com.vm.talk2me.model.MessageResponse
import kotlinx.android.synthetic.main.mess_layout.*

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar

import kotlinx.android.synthetic.main.toolbar_pri.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var messages: MutableList<Message>
    private var manager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private lateinit var custom_font: Typeface
    private var apiKey: String? = null
    private var lang: String? = null
    private var helloText: String? = null
    private var errText: String? = null
    private var service: ApiService? = null
    private var currentTheme: Int = 0
    private var dateFormat: DateFormat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentTheme = Config.getCurrTheme(this)
        setTheme(currentTheme)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_pri)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        tv_toolbar_title!!.setText(R.string.toolbar_title_chat)
        init()
        send()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_chat, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            R.id.item_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        apiKey = resources.getString(R.string.api_key)
        lang = resources.getString(R.string.lang)
        helloText = resources.getString(R.string.start_text)
        errText = resources.getString(R.string.err_text)

        messages = mutableListOf()
        dateFormat = SimpleDateFormat("HH:mm:ss")
        messages!!.add(Message(Constants.MESS_IN_TAG, helloText!!, dateFormat!!.format(Calendar.getInstance().time)))
        custom_font = Typeface.createFromAsset(assets, Constants.CUSTOM_FONT_PATH)
        manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = RCAdapter(messages, custom_font)
        edt_mess!!.typeface = custom_font
        recycler_view!!.layoutManager = manager
        recycler_view!!.adapter = adapter
        recycler_view!!.scrollToPosition(messages!!.size - 1)
        service = ApiClient.client.create(ApiService::class.java)
    }

    fun send() {
        btn_send.setOnClickListener {
            val text = edt_mess!!.text.toString().trim { it <= ' ' }
            if (text != null && text != "") {
                messages!!.add(Message(Constants.MESS_OUT_TAG, text, dateFormat!!.format(Calendar.getInstance().time)))
                edt_mess!!.setText("")
                adapter!!.notifyDataSetChanged()
                recycler_view!!.scrollToPosition(messages!!.size - 1)

                val responseCall = service!!.getMesage(apiKey!!, lang!!, 1.0, text)
                responseCall.enqueue(object : Callback<MessageResponse> {
                    override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                        val messageResponse = response.body()
                        if (messageResponse != null) {
                            if (messageResponse.result === Constants.API_OK_CODE) {
                                messages!!.add(Message(Constants.MESS_IN_TAG, messageResponse.response!!, dateFormat!!.format(Calendar.getInstance().time)))
                                adapter!!.notifyDataSetChanged()
                                recycler_view!!.scrollToPosition(messages!!.size - 1)
                            }
                        }
                    }

                    override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                        messages!!.add(Message(Constants.MESS_IN_TAG, errText!!, dateFormat!!.format(Calendar.getInstance().time)))
                        adapter!!.notifyDataSetChanged()
                        recycler_view!!.scrollToPosition(messages!!.size - 1)
                    }
                })
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putParcelableArrayList(Constants.MESS_LIST_KEY, messages as ArrayList<out Parcelable>?)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        messages!!.clear()
        val mess = savedInstanceState.getParcelableArrayList<Message>(Constants.MESS_LIST_KEY)
        messages!!.addAll(mess!!)
        adapter!!.notifyDataSetChanged()
        recycler_view!!.scrollToPosition(messages!!.size - 1)
    }
}
