package io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Args {
    private String parse;
    private File directory;
    private List<String> excludes;
    private File output;
    String[] args;

    Args(String[] args) {
        this.args = args;
    }

    public File getSourceDirectory() {
        return this.directory;
    }

    public List<String> getExcludes() {
        return excludes;
    }

    public File getOutputZip() {
        return output;
    }

    public boolean validateCommandLine() {
        parse = String.join(" ", args);
        boolean result = false;
        if (!chunkLine("(-d\\s+)([a-zA-Z.*:\\\\]+)\\s+" +
                        "(-e\\s+)(\\*.\\w+\\b\\s+)*" +
                        "(-o\\s+)([a-zA-Z.*:\\\\]+\\b)",
                0).isEmpty()) {
            result = true;
        }
        return result;
    }

    public void parseCommandLine() {
        if (validateCommandLine()) {
            extractDirectory();
            extractExcludeList();
            extractOutputZip();
        }
    }

    private void extractDirectory() {
        directory = new File(chunkLine("(-d\\s+)([a-zA-Z.*:\\\\]+\\b)", 2));
    }

    private void extractExcludeList() {
        String excludeExtensions = chunkLine("(-e\\s+)((\\*.\\w+\\b\\s+)+)", 2);
        excludes = Arrays.stream(excludeExtensions.strip()
                .split("\\s*\\*.")).collect(Collectors.toList());
        excludes.remove(0);
    }

    private void extractOutputZip() {
        output = new File(chunkLine("(-o\\s+)([a-zA-Z.*:\\\\]+\\b)", 2));
        try {
            output.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String chunkLine(String regexp, int groupNumber) {
        String result = "";
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(parse);
        if (matcher.find()) {
            result = matcher.group(groupNumber);
        }
        return result;
    }
}
