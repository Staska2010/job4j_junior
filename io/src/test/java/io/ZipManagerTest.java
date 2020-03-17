package io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ZipManagerTest {
    Path tmpDirectory;
    Path subDirectoryFirstLevel;
    Path fileExtTxt1;
    Path fileExtTxt2;
    Path subDirectorySecondLevel;
    Path fileExtLog1;
    Path fileExtLog2;
    Path fileExtLog3;
    String tempDir;
    ZipManager zip;

    @Before
    public void createTempZip() throws IOException  {
        createTempFileTree();
        tempDir = System.getProperty("java.io.tmpdir");
        String[] args = ("-d " + tempDir + "/project " + "-e  *.txt -o "
                + tempDir + "/project.zip").split("\\s+");
        zip = new ZipManager(args);
        zip.pack();
    }

    @After
    public void onExit() throws IOException {
        Files.deleteIfExists(fileExtTxt1);
        Files.deleteIfExists(fileExtTxt2);
        Files.deleteIfExists(fileExtLog1);
        Files.deleteIfExists(fileExtLog2);
        Files.deleteIfExists(fileExtLog3);
        Files.deleteIfExists(subDirectorySecondLevel);
        Files.deleteIfExists(subDirectoryFirstLevel);
    }

    @Test
    public void whenUnzipZipFileItContainsTheSameDirectoryContent() {
        File unpackLocation = new File(tempDir + "/unpack");
        unpackLocation.mkdir();
        zip.unPack(new File(tempDir + File.separator + zip.targetZipFile.getName()),
               unpackLocation);
        Search search = new Search();
        List<String> result = search.files(unpackLocation.getAbsolutePath(), File::exists)
                .stream()
                .map(File::getName)
                .collect(Collectors.toList());
        List<String> expected = zip.getFilteredFilesToPack(zip.sourceDir).stream()
                .map(File::getName)
                .collect(Collectors.toList());
        assertThat(result.subList(1, result.size()), is(expected.subList(1, expected.size())));
    }

    private void createTempFileTree() throws IOException {
        Path tmpDirectory = Path.of(System.getProperty("java.io.tmpdir"));
        subDirectoryFirstLevel = Files.createDirectory(Path.of(tmpDirectory + "/project"));
        fileExtTxt1 = Files.createFile(Path.of(subDirectoryFirstLevel + "/file1.txt"));
        fileExtTxt2 = Files.createFile(Path.of(subDirectoryFirstLevel + "/file2.txt"));
        subDirectorySecondLevel = Files.createDirectory(Path.of(subDirectoryFirstLevel + "/subDirectorySecondLevel"));
        fileExtLog1 = Files.createFile(Path.of(subDirectoryFirstLevel + "/file3.log"));
        fileExtLog2 = Files.createFile(Path.of(subDirectorySecondLevel + "/file4.log"));
        fileExtLog3 = Files.createFile(Path.of(subDirectorySecondLevel + "/file5.log"));
    }
}
