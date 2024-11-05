#import "DeviceScreenRecorderPlugin.h"
#if __has_include(<device_screen_recorder/screen_recorder-Swift.h>)
#import <device_screen_recorder/screen_recorder-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "device_screen_recorder-Swift.h"
#endif

@implementation DeviceScreenRecorderPlugin
+ (void)registerWithRegistrar:(NSObject<DeviceScreenRecorderPlugin>*)registrar {
  [SwiftDeviceScreenRecorderPlugin registerWithRegistrar:registrar];
}
@end
