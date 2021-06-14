package org.cloud.client.info;


import lombok.Data;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
public class FileInfo {
    public enum FileType {
        FILE(),
        DIRECTORY();
    }

    private String filename;
    private FileType type;
    private long size;
    private LocalDateTime lastModified;

    @SneakyThrows
    public FileInfo(Path path) {
        this.filename = path.getFileName().toString();
        this.size = Files.size(path);
        this.type = Files.isDirectory(path) ? FileType.DIRECTORY : FileType.FILE;
        if (this.type.equals(FileType.DIRECTORY)) {
            this.size = -1L;
        }
        this.lastModified = LocalDateTime.ofInstant(Files.getLastModifiedTime(path).toInstant(), ZoneOffset.ofHours(0));
    }
}
