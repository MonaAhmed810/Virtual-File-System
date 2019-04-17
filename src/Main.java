import allocationTechniques.*;
import models.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int allocationType, numberOfBlocks, blockSize;
        System.out.println("Enter 1, 2 or 3 for allocation type: ");
        System.out.println("1 - allocationTechniques.Contiguous allocation\n" +
                "2 - allocationTechniques.Linked allocation\n" +
                "3 - allocationTechniques.Indexed allocation");
        allocationType = scanner.nextInt();
        System.out.print("Enter number of blocks: ");
        numberOfBlocks = scanner.nextInt();
        System.out.print("Enter block size: ");
        blockSize = scanner.nextInt();
        AllocationTechnique allocationTechnique = null;
        if (allocationType == 1)
            allocationTechnique = new Contiguous(numberOfBlocks);
        else if (allocationType == 2)
            allocationTechnique = new Linked(numberOfBlocks);
        else
            allocationTechnique = new Indexed(numberOfBlocks);
        Directory root = new Directory("root", "root");
        while (true) {
            String command;
            command = scanner.nextLine();
            String arr[] = command.split(" ");
            String path = "", name = "";
            Directory curDirectory = null;
            if (arr.length > 1) {
                for (int i = arr[1].length() - 1; i >= 0; i--) {
                    if (arr[1].charAt(i) == '/') {
                        path = arr[1].substring(0, i);
                        name = arr[1].substring(i + 1);
                        break;
                    }
                }
                curDirectory = root.findDirectory(path);
                if (curDirectory == null) {
                    System.out.println("Path Doesn't Exist..");
                    continue;
                }
            }
            switch (arr[0]) {
                case "CreateFile": {
                    int totalSize = Integer.parseInt(arr[2]);
                    int requiredBlocks = (totalSize + blockSize - 1) / blockSize;
                    boolean isFileExist = false;
                    for (File file : curDirectory.getFiles()) {
                        if (file.getFilePath().equals(arr[1])) {
                            isFileExist = true;
                            break;
                        }
                    }
                    if (isFileExist)
                        System.out.println("models.File Already Exists..");
                    else {
                        File newFile = null;
                        if (allocationType == 1) {
                            int startBlock = allocationTechnique.allocate(requiredBlocks);
                            if (startBlock == -1) {
                                System.out.println("Not enough space available for allocationTechniques.Contiguous allocation of this file!");
                                break;
                            }
                            int endBlock = startBlock + requiredBlocks;
                            newFile = new File().filePath(arr[1]).name(name).startBlock(startBlock).endBlock(endBlock);

                        } else if (allocationType == 2) {
                            int startBlock = allocationTechnique.allocate(requiredBlocks);
                            if (startBlock == -1) {
                                System.out.println("Not enough space available for allocationTechniques.Linked allocation of this file!");
                                break;
                            }
                            newFile = new File().filePath(arr[1]).name(name).startBlock(startBlock);
                        } else {
                            int indexBlock = allocationTechnique.allocate(requiredBlocks);
                            if (indexBlock == -1) {
                                System.out.println("Not enough space available for allocationTechniques.Indexed allocation of this file!");
                                break;
                            }
                            newFile = new File().filePath(arr[1]).name(name).indexBlock(indexBlock);
                        }
                        curDirectory.getFiles().add(newFile);
                        System.out.println("models.File Created Successfully!");
                    }
                    break;
                }
                case "CreateFolder": {
                    boolean isDirectoryExist = false;
                    for (Directory directory : curDirectory.getSubDirectories()) {
                        if (directory.getDirectoryPath().equals(arr[1])) {
                            isDirectoryExist = true;
                            break;
                        }
                    }
                    if (isDirectoryExist)
                        System.out.println("Folder Already Exists..");
                    else {
                        Directory newDirectory = new Directory(arr[1], name);
                        curDirectory.getSubDirectories().add(newDirectory);
                        System.out.println("Folder Created Successfully!");
                    }
                    break;
                }
                case "DeleteFile": {
                    boolean isFileExist = false;
                    for (File file : curDirectory.getFiles()) {
                        if (file.getFilePath().equals(arr[1])) {
                            isFileExist = true;
                            if (allocationType == 1)
                                allocationTechnique.de_allocate(file.getStartBlock(), file.getEndBlock());
                            else if (allocationType == 2)
                                allocationTechnique.de_allocate(file.getStartBlock());
                            else
                                allocationTechnique.de_allocate(file.getIndexBlock());
                            curDirectory.getFiles().remove(file);
                            System.out.println("models.File Deleted Successfully!");
                            break;
                        }
                    }
                    if (!isFileExist)
                        System.out.println("models.File Doesn't Exist..");
                    break;
                }
                case "DeleteFolder": {
                    boolean isDirectoryExist = false;
                    for (Directory directory : curDirectory.getSubDirectories()) {
                        if (directory.getDirectoryPath().equals(arr[1])) {
                            isDirectoryExist = true;
                            curDirectory.getSubDirectories().remove(directory);
                            System.out.println("Folder Deleted Successfully!");
                            break;
                        }
                    }
                    if (!isDirectoryExist)
                        System.out.println("Folder Doesn't Exist..");
                    break;
                }
                case "DisplayDiskStatus": {
                    allocationTechnique.displayDiskStatus();
                    break;
                }
                case "DisplayDiskStructure": {
                    root.printDirectoryStructure(0);
                    break;
                }
            }
        }
    }
}
