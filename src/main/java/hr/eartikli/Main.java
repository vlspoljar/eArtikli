package main.java.hr.eartikli;

import main.java.hr.eartikli.utils.OutputUtil;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        if (args != null && args.length >= 2) {
            OutputUtil outputUtil = new OutputUtil(args[0], args[1]);
            try {
                outputUtil.getTecajnaLista();
            } catch (IOException e) {
                e.printStackTrace();
            }
            outputUtil.writeArtikli();
            outputUtil.writeProdajnaMjesta();
        }
    }
}
