package com.lab3.service;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Archiver {
    public static void zipFile(String sourceFile, String zipFile) {
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(sourceFile)) {
            zos.putNextEntry(new ZipEntry(new File(sourceFile).getName()));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) zos.write(buffer, 0, length);
            zos.closeEntry();
            System.out.println("Архив создан: " + zipFile);
        } catch (IOException e) { System.out.println("Ошибка архивации: " + e.getMessage()); }
    }
}