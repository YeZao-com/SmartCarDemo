package com.oo.smartcardemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.TextureView
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import org.opencv.android.JavaCameraView

/**
 *
 * 用本地相机进行 预览数据模拟
 * */
class MyCameraDemoActivity : AppCompatActivity(), Camera.PreviewCallback,
    TextureView.SurfaceTextureListener {
    val TAG="MyCameraDemoActivity"


    var mCamera: Camera?=null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_camera_demo)

        mCamera = getCameraInstance()
//        mCamera?.setPreviewDisplay(findViewById<S>())




        val textureView = findViewById<TextureView>(R.id.texture_view)
        val surfaceTexture = SurfaceTexture(false)
        surfaceTexture.setDefaultBufferSize(480,640)
        textureView.setSurfaceTexture(surfaceTexture)
        textureView.surfaceTextureListener=this
        mCamera?.setPreviewTexture(surfaceTexture)
        mCamera?.setPreviewCallback(this)
    }


    override fun onResume() {
        super.onResume()

        mCamera?.startPreview()
    }

    override fun onPause() {
        super.onPause()
        mCamera?.stopPreview()
    }

    override fun onDestroy() {
        mCamera?.release()
        super.onDestroy()
    }

    /** A safe way to get an instance of the Camera object. */
    fun getCameraInstance(): Camera? {
        return try {
            Camera.open() // attempt to get a Camera instance
        } catch (e: Exception) {
            // Camera is not available (in use or does not exist)
            null // returns null if camera is unavailable
        }
    }

    override fun onPreviewFrame(data: ByteArray?, camera: Camera?) {
        Log.i(TAG, "onPreviewFrame: ")
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
        mCamera?.stopPreview()
        return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
        mCamera?.startPreview()
    }
}