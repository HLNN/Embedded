package com.topeet.ledtest;

public class led {
	public native int       Open();
    public native int       Close();
    public native int       Ioctl(int num, int en);
}
