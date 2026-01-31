# Notifier

An Android app built with Kotlin.

## Requirements

- **JDK 17** or higher
- **Android SDK** (via [Android Studio](https://developer.android.com/studio) or command-line tools)
- Set `ANDROID_HOME` to your Android SDK path, or create `local.properties` in the project root:
  ```properties
  sdk.dir=C\:\\path\\to\\Android\\sdk
  ```

## Building Locally

```bash
# Debug APK (installable for testing)
./gradlew assembleDebug

# Release APK (requires signing configuration)
./gradlew assembleRelease
```

The output APK will be in:
- Debug: `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/`

## Publishing via GitHub

The project includes a [GitHub Actions workflow](.github/workflows/build-android.yml) that:

1. **Builds the APK** on push or manual trigger
2. **Publishes to GitHub Releases** when you push a tag

### How to publish

1. **Push a tag** to trigger a release:
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```

2. Or **trigger manually**: Go to your repo → Actions → "Build and Release Android APK" → Run workflow

The APK will appear in the workflow artifacts and, on tag push, in [GitHub Releases](https://docs.github.com/en/repositories/releasing-projects-on-github/managing-releases-in-a-repository).

## Project Structure

```
notifier/
├── app/
│   ├── src/main/
│   │   ├── kotlin/com/notifier/app/
│   │   │   └── MainActivity.kt
│   │   ├── res/
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── .github/workflows/
    └── build-android.yml
```

## License

See [LICENSE](LICENSE).
