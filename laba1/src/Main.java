import java.io.File;

public class Main {
    public static void main(String[] args) {
        File currentDir = new File(".");
        printDirectoryTree(currentDir, 0);
    }

    public static void printDirectoryTree(File folder, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.println(folder.getName());
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    printDirectoryTree(file, indent + 1);
                }
            }
        }
    }
}