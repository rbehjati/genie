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
            try {
                NativeUtils.loadLibraryFromJar("/" + System.mapLibraryName("jenny"));
            } catch (IOException e1) {
                logger.error(e1.getMessage(), e1);
            }
        }
    }

    public native String[] generator(String[] argv);
}
