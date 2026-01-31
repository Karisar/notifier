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
3. **Sends an email notification** when a new APK is published (on tag push)

### Email notification

When you push a tag, the workflow sends an email with the release link and API URL. Add these [secrets](https://docs.github.com/en/actions/security-guides/encrypted-secrets) to your repository:

| Secret | Description |
|--------|-------------|
| `MAIL_FROM` | Sender email address |
| `MAIL_TO` | Recipient email address |
| `SMTP_HOST` | SMTP server (e.g. `smtp.gmail.com`) |
| `SMTP_PORT` | SMTP port (e.g. `587` for TLS) |
| `SMTP_USERNAME` | SMTP username |
| `SMTP_PASSWORD` | SMTP password (use an [App Password](https://support.google.com/accounts/answer/185833) for Gmail) |

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
