import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;


public class FileDemo {
    public static void main(String[] args) throws IOException {
        File fileFrom = new File("D:/java");
        File fileTo = new File("D:/temp/java_copy");

        copyDir(fileFrom, fileTo);


    }


    /**
     * Copying content from one file to another
     * @param source - File path we're copying from
     * @param target - File path we're copying to
     * */
    public static void copyFile(File source, File target) throws IOException {
        if (!source.isDirectory()) {
            try (InputStream input = new FileInputStream(source);
                 OutputStream output = new FileOutputStream(target)) {

                byte[] buf = new byte[4096];
                int bytesRead;

                while ((bytesRead = input.read(buf)) > 0) {
                    output.write(buf, 0, bytesRead);
                }
            }
        }
    }


    /**
     * Copying a directory
     * @param source - Directory we're copying from
     * @param target - Directory we're copying to
     * */
    public static void copyDir(File source, File target) throws IOException {
        if (!source.exists())
            return;
        if (source.isDirectory()) {
            if (!target.exists()) {
                target.mkdirs();
            }
            String files[] = source.list();
            for (String file : files) {
                File srcFile = new File(source, file);
                File destFile = new File(target, file);
                copyDir(srcFile, destFile);
            }
        } else {
            copyFile(source, target);
        }

    }


    /**
     * Copying a directory with NIO lib
     * @param source - Directory we're copying from
     * @param target - Directory we're copying to
     * */
    public static void copyFileWithNIO(File source, File target) throws IOException {
        try (FileChannel channelin = new FileInputStream(source).getChannel();
             FileChannel channelout = new FileOutputStream(target).getChannel()) {
            channelout.transferFrom(channelin, 0, channelin.size());
        }
    }


    /**
     * Copying a directory with Java 7
     * @param source - Directory we're copying from
     * @param target - Directory we're copying to
     * */
    public static void copyFilesWithJava7(File source, File target) throws IOException {
        Files.copy(source.toPath(),target.toPath());
    }
}


