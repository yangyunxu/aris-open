package com.ss.aris.open.console;


import com.ss.aris.open.TargetVersion;

@TargetVersion(3)
public interface InputCallback {
    void onInput(String character);
}
