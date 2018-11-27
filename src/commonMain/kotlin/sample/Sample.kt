package sample

expect fun filterSources(path: String, ext: String = ".kt", excludes: List<String> = listOf()): List<String>
