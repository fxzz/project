package com.example.backend.common.util;

import java.util.Arrays;
import java.util.List;

public class FileExtensionUtils {
    private FileExtensionUtils() {
    }

    public static boolean isAllowedExtension(String fileExtension) {
        List<String> allowedExtensions = Arrays.asList("jpg", "png");
        return allowedExtensions.contains(fileExtension.toLowerCase());
    }
}
