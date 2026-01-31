# Add project specific ProGuard rules here.
-keep class com.notifier.app.MainActivity { *; }

# Retrofit
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Gson
-keep class com.notifier.app.WeatherResponse { *; }
-keep class com.notifier.app.HourlyData { *; }
