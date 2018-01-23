package io.rbehjati.genie;

public class Jenny {

    static {
        System.loadLibrary("jenny");
    }

    public native String[] generator(String[] argv);
}
