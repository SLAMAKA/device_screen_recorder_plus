package ru.kovardin.device_screen_recorder

import Recorder
import android.content.Context
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding

/** ScreenRecorderPlugin */
class DeviceScreenRecorderPlugin : FlutterPlugin, ActivityAware {
    private lateinit var context: Context
    private lateinit var client: DeviceScreenRecorderClient

    override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        context = binding.applicationContext

        client = DeviceScreenRecorderClient(context)
        Recorder.setUp(binding.binaryMessenger, client)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        client.onActivityAttach(binding.activity)
        binding.addActivityResultListener(client);
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }

    // ActivityAware
    override fun onDetachedFromActivity() {
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    }

    override fun onDetachedFromActivityForConfigChanges() {
    }
}
