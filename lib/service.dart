// import 'package:flutter/services.dart';

// class NotificationService {
//   static const platform =
//       MethodChannel('com.example.costum_notification/notifications');

//   void _showNotification() async {
//     try {
//       await platform.invokeMethod('showNotification', {
//         'title': 'Custom Notification',
//         'content': 'This is a custom notification',
//       });
//     } on PlatformException catch (e) {
//       print("Failed to show notification: '${e.message}'.");
//     }
//   }
// }
