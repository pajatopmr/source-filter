package sample

import java.io.File

actual fun filterSources(path: String, ext: String, excludes: List<String>): List<String> {
    fun filterSources(file: File): List<String> {
        val result = mutableListOf<String>()
        when {
            file.isFile && file.name.endsWith(ext) && !excludes.contains(file.name) -> result.add(file.path)
            file.isDirectory && !excludes.contains(file.name) -> {
                for (subFile in file.listFiles())
                    result.addAll(filterSources(subFile))
            }
        }
        return result
    }

    return filterSources(File(path))
}