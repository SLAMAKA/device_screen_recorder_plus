import 'package:pigeon/pigeon.dart';

@ConfigurePigeon(PigeonOptions(
  dartOut: 'lib/recorder.dart',
  dartOptions: DartOptions(),
  kotlinOut: 'android/src/main/kotlin/ru/kovardin/device_screen_recorder/pigeons/Recorder.kt',
  kotlinOptions: KotlinOptions(),
  dartPackageName: 'device_screen_recorder',
))
@HostApi()
abstract class Recorder {
  @async
  bool start(String name, bool recordAudio);

  @async
  String stop();
}
