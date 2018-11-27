package sample

import java.io.File

actual fun filterSources(path: String, ext: String, excludes: List<String>): List<String> {
    fun filterSubFiles(file: File): List<String> {
        val result = mutableListOf<String>()
        for (subFile in file.listFiles())
            result.addAll(filterSources(subFile.path, ext, excludes))
        return result
    }

    val file = File(path)
    return when {
        file.isFile && file.name.endsWith(ext) && !excludes.contains(file.name) -> listOf(file.path)
        file.isDirectory && !excludes.contains(file.name) -> filterSubFiles(file)
        else -> listOf()
    }
}

