package sample

actual object Platform {
    actual val name: String = "iOS"
}

actual fun filterSources(path: String, ext: String, excludes: List<String>): List<String> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}