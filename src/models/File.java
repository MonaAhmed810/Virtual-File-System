package models;

public class File {
    private String filePath;
    private String name;
    private int indexBlock;
    private int startBlock, endBlock;

    public File filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File indexBlock(int indexBlock) {
        this.indexBlock = indexBlock;
        return this;
    }

    public int getIndexBlock() {
        return indexBlock;
    }

    public void setIndexBlock(int indexBlock) {
        this.indexBlock = indexBlock;
    }

    public File startBlock(int startBlock) {
        this.startBlock = startBlock;
        return this;
    }


    public int getStartBlock() {
        return startBlock;
    }

    public void setStartBlock(int startBlock) {
        this.startBlock = startBlock;
    }

    public File endBlock(int endBlock) {
        this.endBlock = endBlock;
        return this;
    }

    public int getEndBlock() {
        return endBlock;
    }

    public void setEndBlock(int endBlock) {
        this.endBlock = endBlock;
    }
}
