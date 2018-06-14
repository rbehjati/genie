package io.rbehjati.genie;

import cz.adamh.utils.NativeUtils;

import java.io.IOException;

public class Jenny {

    static {
        try {
            System.loadLibrary("jenny");
        } catch (UnsatisfiedLinkError e) {
            String fileName = "/" + System.mapLibraryName("jenny").replace("dylib", "jnilib");
            try {
                NativeUtils.loadLibraryFromJar(fileName);
            } catch (IOException e1) {
                throw new RuntimeException("Could not find " + fileName + " in jar file.", e1);
            }
        }
    }

    public native String[] generator(String[] argv);
}
