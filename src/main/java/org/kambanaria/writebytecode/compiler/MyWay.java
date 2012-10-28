package org.kambanaria.writebytecode.compiler;
public class MyWay {
    public static void main(String[] args) {
        I i = new I();
        You you = new You();
        System.out.println(you.wouldDo(i.singOutOfTune()).getClass());
    }
}
// What would you do if I sang out of tune ? The Beatles
