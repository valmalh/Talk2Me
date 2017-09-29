package com.vm.talk2me

import android.content.Intent
import android.graphics.Rect
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.nightonke.boommenu.BoomButtons.HamButton
import com.vm.talk2me.config.Config
import com.vm.talk2me.network.NetworkState

import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.toolbar_main_menu.*

class MainMenuActivity : AppCompatActivity() {

    private var currentTheme: Int = 0
    private var menuText: Array<String>? = null
    private var menuHightLightedText: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Config.getCurrTheme(this))
        setContentView(R.layout.activity_main_menu)

        setSupportActionBar(toolbar_main_menu)
        ripple_bg_panel!!.startRippleAnimation()
        menuText = resources.getStringArray(R.array.menu_main_text)
        menuHightLightedText = resources.getStringArray(R.array.menu_hightlighted_text)
        initBoomMenu()
        checkConnection()

        imgbtn_start_chat.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
    }

    private fun checkConnection() {
        val isInternet = NetworkState.isNetworkAvailable(this)
        if (!isInternet) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.dialog_title_err)
            builder.setCancelable(false)
            builder.setMessage(R.string.dialog_mess_err)
            builder.setPositiveButton(R.string.possitive_btn_dialog_title) { dialog, which -> finish() }
            builder.setNegativeButton(R.string.negative_btn_dialog_title) { dialog, which ->
                val intent = Intent()
                intent.action = Settings.ACTION_SETTINGS
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            builder.show()
        }

    }

    private fun initBoomMenu() {
        val buttonNumber = boom_menu_btn!!.buttonPlaceEnum.buttonNumber()
        val typedArray = resources.obtainTypedArray(R.array.menu_main_icon)
        for (i in 0..buttonNumber - 1) {
            val builder = HamButton.Builder()
                    .normalImageDrawable(typedArray.getDrawable(i))
                    .normalText(menuText!![i])
                    .imagePadding(Rect(20, 20, 20, 20))
                    .highlightedText(menuHightLightedText!![i])
                    .subNormalText(menuHightLightedText!![i])
                    .rippleEffect(true)
                    .listener { index ->
                        when (index) {
                            0 -> {
                                //Random random = new Random();
                                val themes = Constants.APPTHEME_LIST
                                //int theme = themes[random.nextInt(themes.length)];
                                //while (currentTheme == theme) {
                                //theme = themes[random.nextInt(themes.length)];
                                //}

//                                val colorPickerDialog = ColorPickerDialog()
//                                colorPickerDialog.initialize(R.string.color_picker_default_title, Constants.COLOR_LIST, Constants.COLOR_LIST[0], 3, 3)
//                                colorPickerDialog.show(fragmentManager, "Test")
//                                colorPickerDialog.setOnColorSelectedListener(object : ColorPickerSwatch.OnColorSelectedListener() {
//                                    fun onColorSelected(color: Int) {
//                                        Toast.makeText(this@MainMenuActivity, color.toString() + "", Toast.LENGTH_SHORT).show()
//                                    }
//                                })
                            }
                            1 -> {
                                val intent = Intent(this@MainMenuActivity, AboutActivity::class.java)
                                startActivity(intent)
                            }
                            2 -> showExitDialog()
                        }//  Config.setCurrTheme(MainMenuActivity.this, theme);
                        //recreate();
                    }
            boom_menu_btn!!.addBuilder(builder)
            boom_menu_btn.setOrientationAdaptable(true)
        }
    }

    private fun showExitDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.dialog_exit_title)
        builder.setMessage(R.string.dialog_exit_mess)
        builder.setNegativeButton(R.string.dialog_exit_yes) { dialog, which -> finish() }
        builder.setPositiveButton(R.string.dialog_exit_no) { dialog, which -> dialog.dismiss() }
        builder.show()
    }
}
