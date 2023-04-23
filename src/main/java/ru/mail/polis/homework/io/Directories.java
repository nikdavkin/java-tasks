package ru.mail.polis.homework.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

public class Directories {


    /**
     * Реализовать рекурсивное удаление всех файлов и директорий из директории по заданному пути.
     * Метод должен возвращать количество удаленных файлов и директорий.
     * Если директории по существующему пути нет, то возвращаем 0.
     * Написать двумя способами. С использованием File
     * 2 тугрика
     */
    public static int removeWithFile(String path) {
        File source = new File(path);
        if (!source.exists()) {
            return 0;
        }
        if (source.isFile()) {
            source.delete();
            return 1;
        }
        int count = 0;
        for (File file : Objects.requireNonNull(source.listFiles())) {
            if (file.isDirectory()) {
                count += removeWithFile(String.valueOf(file));
            } else {
                file.delete();
                count++;
            }
        }

        source.delete();
        return ++count;
    }

    /**
     * С использованием Path
     * 2 тугрика
     */
    public static int removeWithPath(String path) throws IOException {
        Path source = Paths.get(path);
        if (Files.notExists(source)) {
            return 0;
        }
        if (!Files.isDirectory(source)) {
            Files.delete(source);
            return 1;
        }
        final int[] count = {0};
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                count[0]++;
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                count[0]++;
                return FileVisitResult.CONTINUE;
            }
        });
        return count[0];
    }
}
