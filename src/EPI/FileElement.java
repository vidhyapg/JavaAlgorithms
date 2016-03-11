package EPI;

/**
 * Created by vppriyad on 10/4/2015.
 */
public class FileElement implements Comparable<FileElement>{
    private Integer element;
    private Integer fileIndex;
    public FileElement(Integer ele, Integer fIdx){
        element = ele;
        fileIndex = fIdx;
    }
    public int compareTo(FileElement fileElement){
        return element.compareTo(fileElement.element);
    }

    public Integer getElement(){
        return element;
    }

    public Integer getFileIndex(){
        return fileIndex;
    }
}