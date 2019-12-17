package com.hillel.hometasks.task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipCompression implements Compression {
    @Override
    public boolean Compress(String pathToCompress) {
        try {
            File file = new File(pathToCompress);
            byte[] bytes = Files.readAllBytes(Paths.get(pathToCompress));
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file.getAbsolutePath().concat(".zip")));
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            zipOutputStream.write(bytes, 0, bytes.length);
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean Extract(String pathToExtract) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(pathToExtract));
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                //String filePath = pathToExtract + File.separator + zipEntry.getName();
                String filePath = pathToExtract.substring(0, pathToExtract.length() - 4);
                extractFile(zipInputStream, filePath);
                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void extractFile(ZipInputStream zipInputStream, String path) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipInputStream.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}
