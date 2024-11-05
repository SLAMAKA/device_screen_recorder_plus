package ru.kovardin.device_screen_recorder

import Recorder
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Environment
import android.util.Log
import com.hbisoft.hbrecorder.HBRecorder
import com.hbisoft.hbrecorder.HBRecorderListener
import io.flutter.plugin.common.PluginRegistry
import java.io.File

class DeviceScreenRecorderClient(private val context: Context) : Recorder, HBRecorderListener, PluginRegistry.ActivityResultListener {

    private var recorder: HBRecorder? = null
    private var activity: Activity? = null
    private var startCallback: ((Result<Boolean>) -> Unit)? = null
    private var name: String = ""
    private var recordAudio: Boolean = false;

    fun onActivityAttach(activity: Activity) {
        this.activity = activity
        this.recorder = HBRecorder(activity, this)
    }

    override fun start(name: String, recordAudio: Boolean, callback: (Result<Boolean>) -> Unit) {
        this.recordAudio = recordAudio
        val manager = context.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager?
        val intent = manager?.createScreenCaptureIntent()
        activity?.startActivityForResult(intent, SCREEN_RECORD_REQUEST_CODE)
        startCallback = callback
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (requestCode == SCREEN_RECORD_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                //Start screen recording
                recorder?.let {
                    it.setOutputPath(destination())
                    if (name.isNotBlank()) {
                        it.fileName = name
                    }
                    it.enableCustomSettings()

                    it.setAudioSource("DEFAULT")
                    it.isAudioEnabled(recordAudio)
                    it.recordHDVideo(true);
                    it.startScreenRecording(data, resultCode)
                }

                startCallback?.let { it(Result.success(true)) }
            } else {
                startCallback?.let { it(Result.success(false)) }
            }
        }
        return true;
    }

    override fun stop(callback: (Result<String>) -> Unit) {
        recorder?.stopScreenRecording()

        callback(Result.success(recorder?.filePath.orEmpty()))
    }

    private fun destination(): String {
        val downloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        val destination = File(downloads, "records")
        if (!destination.exists()) {
            destination.mkdir()
        }
        return destination.absolutePath
    }

    override fun HBRecorderOnStart() {
        Log.i(TAG, "HBRecorderOnStart")
    }

    override fun HBRecorderOnComplete() {
        Log.i(TAG, "HBRecorderOnComplete")
    }

    override fun HBRecorderOnError(errorCode: Int, reason: String?) {
        Log.i(TAG, "HBRecorderOnError ${reason}")
    }

    override fun HBRecorderOnPause() {
        Log.i(TAG, "HBRecorderOnPause")
    }

    override fun HBRecorderOnResume() {
        Log.i(TAG, "HBRecorderOnPause")
    }

    private companion object {
        const val SCREEN_RECORD_REQUEST_CODE = 333;
        const val TAG = "DeviceScreenRecorder"
    }
}