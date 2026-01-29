# CodeScanner

A feature-rich Android application for scanning and generating QR codes and barcodes. Built with modern Android technologies, CodeScanner provides an intuitive interface for quick code scanning and generation with customizable options.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Features

- **QR Code Scanning**: Quickly scan QR codes using your device's camera
- **Barcode Scanning**: Support for various barcode formats through the integrated ZXing library
- **QR Code Generation**: Create custom QR codes with user-defined content
- **Barcode Generation**: Generate barcodes with configurable dimensions
- **Dark Mode Support**: Full dark mode implementation for better usability in low-light environments
- **Clipboard Integration**: Instantly copy scanned results to clipboard
- **Customizable Output Dimensions**: Configure width and height for generated codes
- **Material Design UI**: Modern, intuitive navigation with fragment-based architecture
- **Settings Management**: Persistent preferences for user settings and configuration

## Requirements

- **Android SDK**: Minimum API level 28 (Android 9.0), Target API level 34
- **Java**: Java 8 or higher
- **Gradle**: 8.0 or compatible version

### Key Dependencies

- **AndroidX**: AppCompat, ConstraintLayout, Preference
- **ZXing**: Core QR/barcode encoding/decoding (v3.5.3)
- **ZXing Android Embedded**: Camera integration for scanning (v4.3.0)
- **Material Design**: Google Material Components (v1.11.0)

## Installation

### Prerequisites

- Android Studio 2022.1 or later
- Android SDK with API level 28 or higher installed

### Setup Steps

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd code-scanner
   ```

2. **Open in Android Studio**:
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned `code-scanner` directory

3. **Build the project**:
   ```bash
   ./gradlew build
   ```

4. **Run on a device or emulator**:
   ```bash
   ./gradlew installDebug
   ```
   Or use Android Studio's Run button (Shift + F10)

### Permissions

The app requires the following permissions (automatically requested):

- `CAMERA`: For scanning QR codes and barcodes
- `READ_MEDIA_IMAGES`: To access image gallery (Android 13+)
- `READ_EXTERNAL_STORAGE` / `WRITE_EXTERNAL_STORAGE`: For older Android versions (legacy support)
- `MANAGE_DOCUMENTS`: For document access

## Usage

### Scanning Codes

1. Open the app and navigate to the **Scanner** tab
2. Grant camera permissions if prompted
3. Point your device's camera at a QR code or barcode
4. The scanned result will appear on the screen
5. Tap "Copy to Clipboard" to quickly copy the scanned data

### Generating QR Codes

1. Navigate to the **Generator** tab
2. Select **QR code** from the menu
3. Enter the content/data for your QR code
4. Adjust dimensions if needed (default: 1024x1024)
5. Tap "Generate" to create the QR code
6. The generated code will be saved to your device gallery

### Generating Barcodes

1. Navigate to the **Generator** tab
2. Select **Bar code** from the menu
3. Enter the content/data for your barcode
4. Adjust dimensions if needed
5. Tap "Generate" to create the barcode
6. The generated code will be saved to your device gallery

### Customization

- **Dark Mode**: Toggle dark mode in the **Settings** tab
- **Code Dimensions**: Set default width and height for generated codes in **Settings**
- **Storage Location**: Codes are automatically saved to your device's Pictures directory

## Project Structure

```
code-scanner/
├── app/
│   ├── build.gradle                 # App module build configuration
│   ├── proguard-rules.pro           # ProGuard obfuscation rules
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml  # App manifest
│       │   ├── java/com/example/codescanner/
│       │   │   ├── MainActivity.java          # Main activity with navigation
│       │   │   ├── ScanFragment.java          # Scanner UI
│       │   │   ├── GenerateQRFragment.java    # QR code generation
│       │   │   ├── GenerateBarFragment.java   # Barcode generation
│       │   │   ├── SettingsFragment.java      # Settings/preferences
│       │   │   └── CaptureAct.java            # ZXing camera activity
│       │   └── res/                 # Resources (layouts, drawables, strings)
│       ├── androidTest/             # Instrumented tests
│       └── test/                    # Unit tests
├── gradle/                          # Gradle wrapper configuration
├── build.gradle                     # Root build configuration
├── settings.gradle                  # Project settings
└── README.md                        # This file
```

## Configuration

### Build Configuration

The app is configured with the following settings in [app/build.gradle](app/build.gradle):

- **Namespace**: `com.example.codescanner`
- **Compile SDK**: 34
- **Min SDK**: 28
- **Target SDK**: 28
- **Version Code**: 1
- **Version Name**: 1.0

### Gradle Build

Build the project with optimizations:

```bash
# Build release version with ProGuard
./gradlew assembleRelease

# Build debug version
./gradlew assembleDebug

# Run tests
./gradlew test
./gradlew connectedAndroidTest
```

---

**Happy Scanning!** 📱✨
