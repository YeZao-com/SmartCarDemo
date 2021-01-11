package com.oo.smartcardemo

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest

/**
 *
 * 设置界面  负责选择输入源
 * */
class SettingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun byBluetooth(v: View?) {
        startActivity(Intent(this@SettingActivity, BluetoothDemoActivity::class.java))
    }

    fun byMedia(v: View?) {
        startActivity(Intent(this@SettingActivity, MyMediaDemoActivity::class.java))
    }

    fun byCamera(v: View?) {
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),1)
        }else{
            startActivity(Intent(this@SettingActivity, MyCameraDemoActivity::class.java))
        }

    }
}