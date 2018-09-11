import Models.Line;
import Utils.FileUtils;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = FileUtils.openFile();
        List<Line> lines = FileUtils.readLines(br);
        FileUtils.writeLines(lines);
    }

}
