import Utils.FileUtils;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = FileUtils.openFile();
        FileUtils.readLines(br);
    }

}
