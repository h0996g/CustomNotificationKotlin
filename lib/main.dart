import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  // ignore: library_private_types_in_public_api
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const platform =
      MethodChannel('com.example.costum_notification/custom_notification');

  Future<void> _showCustomNotification(String content) async {
    try {
      await platform
          .invokeMethod('showCustomNotification', {'content': content});
    } on PlatformException catch (e) {
      print("Failed to show notification: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Custom Notification Demo'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () => _showCustomNotification("Your custom message here"),
          child: const Text('Show Custom Notification'),
        ),
      ),
    );
  }
}
