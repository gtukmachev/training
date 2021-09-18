package tga.files_Scanner

import java.io.File
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {

    val root = File(args[0])

    val extToCategoryIndex = HashMap<String, Category>();
    for (category in Category.values()) {
        for (ext in category.exts) {
            extToCategoryIndex[ext] = category
        }
    }

    val doNotScanDirs = setOf(".git", "target", ".idea", "node_modules", "dist", ".gradle", ".yarn")
    val doNotScanExt  = setOf("zip", "jar")

    //val linesOfCodeMap = TreeMap<String, Int>()
    //val filesMap       = HashMap<String, Int>()
    var linesOfCodeTotal = 0
    var filesTotal       = 0

    var i = 0

    fun printMaps() {
        val extSt   =             "--- total ---".padStart(20, '-')
        val filesSt =       filesTotal.toString().padStart(5)
        val linesSt = linesOfCodeTotal.toString().padStart(7)
        println("$extSt: $filesSt files,  $linesSt lines of code")

        for (category in Category.values()) {
            val extSt   =             " total by ${category.name}".padStart(20, '-')
            val filesSt =           category.totalFiles.toString().padStart(5)
            val linesSt =           category.totalLines.toString().padStart(7)
            println("$extSt: $filesSt files,  $linesSt lines of code")

            for ((ext, lines) in category.lines) {
                val extSt   =                            ext.padStart(15)
                val filesSt = category.files[ext].toString().padStart(5)
                val linesSt =               lines.toString().padStart(7)
                println("$extSt: $filesSt files,  $linesSt lines of code")
            }

            println("")
        }
    }



    root.parentFirstScan { file: File ->
        i++
        if (file.isDirectory && file.name in doNotScanDirs) return@parentFirstScan false

        val ext = when (val e = file.extension) {
            "Jenkinsfile" -> "groove"
            else -> e
        }

        if (file.isFile && (file.extension.isNullOrEmpty() || ext in doNotScanExt)) return@parentFirstScan false

        if (file.isFile) {
            var linesOfCode = 0
            file.forEachLine { linesOfCode++ }
            val category = extToCategoryIndex[file.extension] ?: Category.other

            category.files.merge(ext, 1, Int::plus);
            category.lines.merge(ext, linesOfCode, Int::plus);

            category.totalFiles += 1
            category.totalLines += linesOfCode

            filesTotal += 1
            linesOfCodeTotal += linesOfCode
        }

        // if (i % 1000 == 0) printMaps()

        true
    }

    printMaps()

}



private fun File.parentFirstScan(visit: (File) -> Boolean) {
    val root: File = this
    val goDeeply: Boolean = visit(root)
    if (goDeeply && root.isDirectory)
        root.listFiles()?.forEach{ it.parentFirstScan(visit) }
}

enum class Category(
    val exts: Set<String>,
    val files: TreeMap<String, Int> = TreeMap<String, Int>(),
    val lines: HashMap<String, Int> = HashMap<String, Int>(),
    var totalFiles: Int = 0,
    var totalLines: Int = 0
) {
    code(setOf("java", "kt", "json", "groove", "TXT", "bat", "css", "scss", "cfg", "cmd", "conf", "config", "feature", "go", "groove", "groovy", "htm", "html", "http", "jsp", "js", "properties", "py", "scala", "sql", "svg", "tf", "tf-old", "tfvars", "tfvars", "ts", "txt", "xml", "yaml", "yml","gitattributes", "gitignore", "gitkeep",)),
    images(setOf("gif", "jpeg", "jpg", "png",)),
    other(setOf())
}

