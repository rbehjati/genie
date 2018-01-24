package io.rbehjati.genie;

import cz.adamh.utils.NativeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Jenny {

    private static final Logger logger = LoggerFactory.getLogger(Jenny.class);

    static {
        try {
            System.loadLibrary("jenny");
        } catch (UnsatisfiedLinkError e) {
            String fileName = "/" + System.mapLibraryName("jenny").replace("dylib", "jnilib");
            try {
                NativeUtils.loadLibraryFromJar(fileName);
            } catch (IOException e1) {
                logger.error("Could not find " + fileName + " in jar file.", e1);
                throw new RuntimeException(e1);
            }
        }
    }

    public native String[] generator(String[] argv);
}
