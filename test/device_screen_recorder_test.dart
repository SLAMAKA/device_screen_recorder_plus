import 'package:device_screen_recorder/device_screen_recorder.dart';
import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  const MethodChannel channel = MethodChannel('device_screen_recorder');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      if (methodCall.method == 'startRecordScreen') {
        return true;
      } else if (methodCall.method == 'stopRecordScreen') {
        return 'path';
      }
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('startRecordScreen', () async {
    expect(await DeviceScreenRecorder.startRecordScreen(), true);
  });

  test('stopRecordScreen', () async {
    expect(await DeviceScreenRecorder.stopRecordScreen(), 'path');
  });
}
