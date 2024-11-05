#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html.
# Run `pod lib lint device_screen_recorder.podspec` to validate before publishing.
#
Pod::Spec.new do |s|
  s.name             = 'device_screen_recorder'
  s.version          = '0.0.1'
  s.summary          = 'A Flutter plugin for record the screen. This plug-in requires Android SDK 21+ and iOS 10+.'
  s.description      = <<-DESC
A new Flutter project.
                       DESC
  s.homepage         = 'https://gitflic.ru/project/kovardin/flutter-screen-recorder'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Your Company' => 'artem-kovardin@yandex.ru' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.dependency 'Flutter'
  s.platform = :ios, '8.0'

  # Flutter.framework does not contain a i386 slice.
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'i386' }
  s.swift_version = '5.0'
end
