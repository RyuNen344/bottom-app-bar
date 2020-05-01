package dependencies

private object Versions {
    const val androidTargetSdkVersion = 29
    const val androidCompileSdkVersion = 29
    const val androidBuildToolsVersion = "29.0.2"
    const val androidMinSdkVersion = 24
    private val versionMajor = 1
    private val versionMinor = 0
    private val versionPatch = 0
    private val versionOffset = 0
    val androidVersionCode =
        (versionMajor * 10000 + versionMinor * 100 + versionPatch) * 100 + versionOffset

    val androidVersionName = "$versionMajor.$versionMinor.$versionPatch"
}
