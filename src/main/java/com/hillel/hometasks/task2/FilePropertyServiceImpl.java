package com.hillel.hometasks.task2;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class FilePropertyServiceImpl implements FilePropertyService {
    @Override
    public int CountMaxStringLengthWay1(String path) {
        if (IsFileExist(path)) {
            try {
                Stream<String> stringStream = Files.lines(Paths.get(path));
                final int result = stringStream.filter(s -> !s.isEmpty()).map(String::length).max(Comparator.comparing(Integer::intValue)).get();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int CountMaxStringLengthWay2(String path) {
        if (IsFileExist(path)) {
            try {
                File file = new File(path);
                List<Integer> stringList = new ArrayList<>();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String tempString = scanner.nextLine();
                    if (!tempString.isEmpty()) {
                        stringList.add(tempString.length());
                    }
                }
                return Collections.max(stringList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private boolean IsFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }
}
