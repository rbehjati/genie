package io.rbehjati.jenny;

public class Jenny {

	static {
		System.loadLibrary("jenny");
	}

	public native String[] generator(int argc, String[] argv);

}
