import java.io.*;



public class FileDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/java");
        File file1 = new File("D:/temp/java_copy");

        copyDir(file,file1);


    }

    public static void copyFile(File source, File target) throws IOException {
        if (!source.isDirectory()) {
            try (InputStream input = new FileInputStream(source);
                 OutputStream output = new FileOutputStream(target)) {

                byte[] buf = new byte[4096];
                int bytesRead;

                while ((bytesRead = input.read(buf)) > 0) {
                    output.write(buf, 0, bytesRead);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyDir(File source, File target) throws IOException {
        if(!source.exists())
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
            copyFile(source,target);
        }

    }

    public static void copyFileWithNIO(File source, File target) {

    }
}


