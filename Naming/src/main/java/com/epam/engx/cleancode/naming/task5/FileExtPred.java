package com.epam.engx.cleancode.naming.task5;


import com.epam.engx.cleancode.naming.task5.thirdpartyjar.Predicate;

public class FileExtPred implements Predicate<String> {

    private final String[] extns;

    FileExtPred(String[] extns) {
        this.extns = extns;
    }

    @Override
    public boolean isExtension(String fileName) {
        for (String extension : extns) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
