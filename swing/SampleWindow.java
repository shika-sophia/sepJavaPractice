package javaPractice.swing;

import javax.swing.JFrame;

public class SampleWindow {

    public static void main(String[] args) {
        final JFrame frame =new JFrame("Sample Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,480);
        frame.setVisible(true);
    }//main()

}//class

/*
#
# A fatal error has been detected by the Java Runtime Environment:
#
#  EXCEPTION_ACCESS_VIOLATION (0xc0000005) at pc=0x00007ffb6df5fa16, pid=13268, tid=3160
#
# JRE version: OpenJDK Runtime Environment (11.0.6+10) (build 11.0.6+10)
# Java VM: OpenJDK 64-Bit Server VM (11.0.6+10, mixed mode, tiered, compressed oops, g1 gc, windows-amd64)
# Problematic frame:
# C  [awt.dll+0x8fa16]
#
# No core dump will be written. Minidumps are not enabled by default on client versions of Windows
#
# An error report file with more information is saved as:
# C:\pleiades202003\workspace_web\sepJavaRecurrent\hs_err_pid13268.log
#
# If you would like to submit a bug report, please visit:
#   https://github.com/AdoptOpenJDK/openjdk-support/issues
# The crash happened outside the Java Virtual Machine in native code.
# See problematic frame for where to report the bug.
#
C:\Users\6A16\Desktop>java -version
openjdk version "11.0.7" 2020-04-14
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.7+10)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.7+10, mixed mode)

C:\Users\6A16\Desktop>java SampleWindow // ->実行可能

*/