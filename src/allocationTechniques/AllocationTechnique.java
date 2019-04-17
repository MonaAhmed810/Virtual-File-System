package allocationTechniques;

import models.*;

import java.util.ArrayList;

public class AllocationTechnique {
    protected int numberOfBlocks;
    protected ArrayList<Block> blocks;

    public AllocationTechnique() {
    }

    public int allocate(int requiredBlocks) {
        return -1;
    }

    public void de_allocate(int startBlock, int endBlock) {
    }

    public void de_allocate(int startBlock) {
    }

    public void displayDiskStatus() {
        int emptySpace = 0;
        for (int i = 0; i < numberOfBlocks; i++)
            if (blocks.get(i).isAvailable())
                emptySpace++;
        System.out.println("Empty space: " + emptySpace);
        System.out.println("Empty blocks: ");
        for (int i = 0; i < numberOfBlocks; i++)
            if (blocks.get(i).isAvailable())
                System.out.print(i + " ");
        System.out.println();
        int allocatedSpace = numberOfBlocks - emptySpace;
        System.out.println("Allocated space: " + allocatedSpace);
        for (int i = 0; i < numberOfBlocks; i++)
            if (!blocks.get(i).isAvailable())
                System.out.print(i + " ");
        System.out.println();
    }
}
