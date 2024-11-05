import 'dart:async';

import 'package:device_screen_recorder_plus/recorder.dart';

class DeviceScreenRecorder {
  static Recorder _client = Recorder();

  static Future<bool?> startRecordScreen({String? name, bool recordAudio = false}) async {
    return _client.start(name ?? "", recordAudio);
  }

  static Future<String?> stopRecordScreen() async {
    return _client.stop();
  }
}
