package io;

import java.io.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipManager {
    private Args cmdLineParser;
    private Search fileSearcher;
    File targetZipFile;
    File sourceDir;

    ZipManager(String[] args) {
        cmdLineParser = new Args(args);
        fileSearcher = new Search();
    }

   public void pack() {
        getSources();
        List<File> filesToPack = getFilteredFilesToPack(sourceDir);
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(targetZipFile)))) {
            for (File next : filesToPack) {
                String path = normalizePath(next, sourceDir);
                if (path.length() > 0) {
                    zip.putNextEntry(new ZipEntry(path));
                    if (next.isFile()) {
                        try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(next))) {
                            zip.write(out.readAllBytes());
                        }
                    }
                }
                zip.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unPack(File zipToUnpack, File destination) {
        byte[] buffer = new byte[1024];
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipToUnpack))) {
            ZipEntry nextEntry = zis.getNextEntry();
            while (nextEntry != null) {
                File extractedFile = new File(destination, nextEntry.getName());
                if (nextEntry.getName().endsWith(File.separator)) {
                    extractedFile.mkdir();
                } else {
                    try(FileOutputStream fos = new FileOutputStream(extractedFile)) {
                        while (zis.read(buffer) > 0) {
                            fos.write(buffer);
                        }
                    } catch(IOException exc) {
                        System.out.println(exc.getMessage());
                    }
                }
                nextEntry = zis.getNextEntry();
            }
            zis.closeEntry();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    private void getSources() {
        cmdLineParser.parseCommandLine();
        targetZipFile = cmdLineParser.getOutputZip();
        sourceDir = cmdLineParser.getSourceDirectory();
    }

    public List<File> getFilteredFilesToPack(File target) {
        Predicate<File> condition;
        if (cmdLineParser.validateCommandLine()) {
            condition = x -> {
                return x.isDirectory()
                        || cmdLineParser.getExcludes().stream()
                        .noneMatch(ext -> x.getName().endsWith(ext));
            };
        } else {
            condition = File::exists;
        }
        return fileSearcher.files(target.getAbsolutePath(), condition);
    }

    private String normalizePath(File fileToNormalizePath, File source) {
        String path = fileToNormalizePath.getPath().substring(source.getPath().length());
        if (path.startsWith(File.separator)) {
            path = path.substring(1);
        }
        if (fileToNormalizePath.isDirectory() && path.length() > 0) {
            path = path + File.separator;
        }
        return path;
    }

    public static void main(String[] args) {
        ZipManager zip = new ZipManager(args);
        zip.pack();
        File tmpDir = new File("c:\\temp\\dir1");
        tmpDir.mkdir();
        zip.unPack(zip.targetZipFile, tmpDir);
    }
}
