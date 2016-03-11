package EPI;

import java.io.*;
import java.util.AbstractMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;
/**
 * EPI 11.1
 * Assume n = 5
 * Created by vppriyad on 10/4/2015.
 */
public class MergeFiles {

    public void mergeSortedFiles(String[] fileNames, String out_filename, int n){
        BufferedReader[] bfReaders = new BufferedReader[n];

        // each element is a pair of element and the fileIndex from which the element came.
        PriorityQueue<FileElement> minHeap = new PriorityQueue<>();
        int i;

        // create input readers for each of the n input files.
        for (i = 0; i < n; i++){
            try {
                bfReaders[i] = new BufferedReader(new FileReader(fileNames[i]));
            }
            catch(FileNotFoundException e){
                System.out.println("The File is not found in the path " + e);
            }
        }


        // initialize min_heap with first entry of each of the n files.
        String line = null;
        for (i = 0; i < n; i++){
            try{
                if ((line = bfReaders[i].readLine()) != null){
                    Integer k = Integer.parseInt(line.trim());
                    FileElement currEntry = new FileElement(k,i);
                    minHeap.add(currEntry);
                }
            }
            catch(IOException e){
                System.out.println("Error opening file "+ fileNames[i] + e);
            }
        }

        Integer min_element;
        Integer min_fileIndex;
        BufferedWriter bfWriter = null;
        try{
            try {
                // create output writer for the output file.
                bfWriter = new BufferedWriter(new FileWriter(out_filename));

                while(!(minHeap.isEmpty())){
                    FileElement min_entry = minHeap.poll();
                    min_element = min_entry.getElement();
                    min_fileIndex = min_entry.getFileIndex();

                    // write to the output file
                    System.out.println(min_element);
                    bfWriter.write(min_element.toString());
                    bfWriter.newLine();

                    if ((line = bfReaders[min_fileIndex].readLine()) != null){
                        Integer k = Integer.parseInt(line.trim());
                        FileElement nextEntry = new FileElement(k,min_fileIndex);
                        minHeap.add(nextEntry);
                    }
                }
            }
            finally{
                // close can throw IOException, and hence nested try block.
                if (bfWriter != null) {
                    bfWriter.close();
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("The File is not found in the path " + e);
        }
        catch(IOException e){
                System.out.println("Error opening file "+ e);
        }

        System.out.println("Done merging");

        // make sure you close all the bfreaders and bfwriters to prevent resource leak.
        // if you have return or continue or break statements in try, do this cleanup in finally code.
        // finally executes even if there is such statement in try.
        try {
            // close readers for each of the n files.
            for (i = 0; i < n; i++) {
                if (bfReaders[i] != null)
                    bfReaders[i].close();
            }
            // close writer
            if (bfWriter != null) {
                bfWriter.close();
            }
        }
        catch(IOException e){
            System.out.println("Error opening file "+ fileNames[i] + e);
        }
    }

    public static void main(String args[]){
        // construct Array of Files containing sorted integers to be merged.
        int n = 5;
        String[] fileNames = new String[n];
        for (int i = 0; i < n; i++){
            fileNames[i] = "mergeinput"+i+".txt";
        }
        String out_filename = "mergeoutput.txt";

        MergeFiles merger = new MergeFiles();
        merger.mergeSortedFiles(fileNames, out_filename, n);
    }

}


