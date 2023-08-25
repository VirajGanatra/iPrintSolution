package com.example.iPrintSolution;
import com.sun.jna.win32.StdCallLibrary;

public interface AccDLL extends StdCallLibrary {
    int update(String a, int b);
}
