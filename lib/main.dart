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
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const platform =
      MethodChannel('com.example.costum_notification/custom_notification');

  @override
  void initState() {
    super.initState();
    _startCustomNotificationService();
  }

  Future<void> _startCustomNotificationService() async {
    try {
      await platform.invokeMethod('startCustomNotificationService');
    } on PlatformException catch (e) {
      print("Failed to start custom notification service: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Custom Notification Demo'),
      ),
      body: const Center(
        child: Text('Custom notifications will appear every 1 minutes.'),
      ),
    );
  }
}
