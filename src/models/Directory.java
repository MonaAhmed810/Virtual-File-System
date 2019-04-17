package models;

import java.util.ArrayList;

public class Directory {
    private String directoryPath;
    private String name;
    private ArrayList<File> files;
    private ArrayList<Directory> subDirectories;

    public Directory() {
    }

    public Directory(String directoryPath, String name) {
        this.directoryPath = directoryPath;
        this.name = name;
        files = new ArrayList<>();
        subDirectories = new ArrayList<>();
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public ArrayList<Directory> getSubDirectories() {
        return subDirectories;
    }

    public void setSubDirectories(ArrayList<Directory> subDirectories) {
        this.subDirectories = subDirectories;
    }

    public void printDirectoryStructure(int level) {
        String tabs = "";
        for (int i = 0; i < level; i++)
            tabs += "\t";
        System.out.println(tabs + "<" + name + ">");
        for (Directory directory : subDirectories)
            directory.printDirectoryStructure(level + 1);
        for (File file : files)
            System.out.println(tabs + "\t" + file.getName());
    }

    public Directory findDirectory(String path) {
        if (directoryPath.equals(path))
            return this;
        if (subDirectories != null) {
            for (int i = 0; i < subDirectories.size(); i++) {
                Directory ret = subDirectories.get(i).findDirectory(path);
                if (ret != null)
                    return ret;
            }
        }
        return null;
    }

}
