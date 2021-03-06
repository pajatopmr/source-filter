# source-filter
Generate a list of source files for a given path.

This Kotlin multi-platform sample generates a list of source files
matching a given extension from a given file path using a list of
exclusion names.

The exercise arose from a question posed by Sean O (Learning Swift
Boston meet-up group co-organizer):

"does anyone have suggestions on how I could reduce the amount of code I have in this solution? this function is responsible for discovering swift source code files, while also excluding files that are part of a blacklist."


    func sourceFilesContained(in path: String) -> [String] {
        let subpaths = FileManager.default.subpaths(atPath: path) ?? []
        return subpaths
            .filter {
                let blackList = ["Build", "muter_tmp", "Tests.swift"]

                for item in blackList where $0.contains(item) {
                    return false
                }

                return $0.contains(".swift")
            }
            .map { "\(path)/\($0)"}
            .sorted()
    }

The solution provided by this project tweaks Sean's example so that
the code can be made even more readable and clean (in the Uncle Bob
sense), albeit in a Kotlin/JVM form:

    fun filterSources(path: String, ext: String = ".kt", excludes: List<String>): List<String> {
        fun filterSubFiles(file: File): List<String> {
            val result = mutableListOf<String>()
            for (subFile in file.listFiles())
                result.addAll(filterSources(subFile.path, ext, excludes))
            return result
        }

        return when {
            file.isFile && file.name.endsWith(ext) && !excludes.contains(file.name) -> listOf(file.path)
            file.isDirectory && !excludes.contains(file.name) -> filterSubFiles(file)
            else -> listOf()
        }
    }

To achieve better parity with Sean's original code, one would call filterSources() like so:

    ...
    val filteredSources = filterSources(path, ".swift", listOf("Build", "muter.tmp", "Tests.swift").apply {sort()}
    ...
