package ltd.matrixstudios.framework.util

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream


object FileUtils {

    @Throws(IOException::class)
    fun unzipFolder(source: Path, target: Path?) {
        ZipInputStream(FileInputStream(source.toFile())).use { zis ->

            // list files in zip
            var zipEntry = zis.nextEntry
            while (zipEntry != null) {
                var isDirectory = false
                // example 1.1
                // some zip stored files and folders separately
                // e.g data/
                //     data/folder/
                //     data/folder/file.txt
                if (zipEntry.name.endsWith(File.separator)) {
                    isDirectory = true
                }
                val newPath: Path = zipSlipProtect(zipEntry, target!!)!!
                if (isDirectory) {
                    Files.createDirectories(newPath)
                } else {

                    // example 1.2
                    // some zip stored file path only, need create parent directories
                    // e.g data/folder/file.txt
                    if (newPath.getParent() != null) {
                        if (Files.notExists(newPath.getParent())) {
                            Files.createDirectories(newPath.getParent())
                        }
                    }

                    // copy files, nio
                    Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING)

                    // copy files, classic
                    /*try (FileOutputStream fos = new FileOutputStream(newPath.toFile())) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }*/
                }
                zipEntry = zis.nextEntry
            }
            zis.closeEntry()
        }
    }

    @Throws(IOException::class)
    fun zipSlipProtect(zipEntry: ZipEntry, targetDir: Path): Path? {

        // test zip slip vulnerability
        // Path targetDirResolved = targetDir.resolve("../../" + zipEntry.getName());
        val targetDirResolved = targetDir.resolve(zipEntry.name)

        // make sure normalized file still has targetDir as its prefix
        // else throws exception
        val normalizePath = targetDirResolved.normalize()
        if (!normalizePath.startsWith(targetDir)) {
            throw IOException("Bad zip entry: " + zipEntry.name)
        }
        return normalizePath
    }
}