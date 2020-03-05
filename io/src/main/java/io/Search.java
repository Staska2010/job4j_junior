package io;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class Search {

    public List<File> files(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        Queue<File> content = new LinkedList<>();
        File file = new File(parent);
        if (file.exists()) {
            content.offer(file);
            while (!content.isEmpty()) {
                File tmp = content.poll();
                if (!tmp.isDirectory()) {
                    for (String extension : exts) {
                        if (tmp.getName().endsWith(extension)) {
                            result.add(tmp);
                        }
                    }
                } else {
                    content.addAll(Arrays.asList(Objects.requireNonNull(tmp.listFiles())));
                }
            }
        }
        return result;
    }

    public List<File> files(String parent, Predicate<File> condition) {
        Queue<File> content = new LinkedList<>();
        List<File> result = new ArrayList<>();
        File file = new File(parent);
        if (file.exists()) {
            content.offer(file);
            while (!content.isEmpty()) {
                File tmp = content.poll();
                if (condition.test(tmp)) {
                    result.add(tmp);
                }
                if (tmp.isDirectory()) {
                    content.addAll(Arrays.asList(Objects.requireNonNull(tmp.listFiles())));
                }
            }
        }
        return result;
    }
}
