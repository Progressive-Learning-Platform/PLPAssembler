package org.plp.implementations.plpisa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This will hold the meta data needed for the {@link PlpProgram}
 */
public class PlpProgramMetaData {
    private final String version = "7.0";
    private final String programName;
    private final List<String> programFiles;

    /**
     * This will create a metadata for the {@link PlpProgram}
     * @param programName Name of the program
     */
    public PlpProgramMetaData(String programName) {
        this.programName = programName;
        programFiles = new ArrayList<>();
    }

    /**
     * This will create a metadata for the {@link PlpProgram}
     * @param programName Name of the program
     * @param programFiles list of files present in the {@link PlpProgram}
     */
    @JsonCreator
    public PlpProgramMetaData(@JsonProperty("programName") String programName,
                              @JsonProperty("programFiles") List<String> programFiles) {
        this.programName = programName;
        this.programFiles = new ArrayList<>(programFiles);
    }

    /**
     * Get the Name of the {@link PlpProgram}
     * @return name of the program
     */
    public String getProgramName() {
        return this.programName;
    }

    /**
     * Get the Version of {@link PlpProgram}
     * @return current version of PLP
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * This will add the name of {@link PlpFile} added to {@link PlpProgram}
     * @param fileName name of {@link PlpFile}
     */
    public void addFileToProgram(String fileName) {
        programFiles.add(fileName);
    }

    /**
     * This will add the name of {@link PlpFile} added to {@link PlpProgram} at a particular order
     * @param fileName name of {@link PlpFile}
     * @param index position of the file to be added
     */
    public void addFileToProgramAtIndex(String fileName, int index) {
        if(index >= programFiles.size()) {
            addFileToProgram(fileName);
        } else {
            programFiles.add(index, fileName);
        }
    }

    /**
     * Delete the {@link PlpFile} from the {@link PlpProgram}
     * @param fileName name of the {@link PlpFile} to be deleted
     */
    public void deleteFileInProgram(String fileName) {
        programFiles.remove(fileName);
    }

    /**
     * Get the list of {@link PlpFile}'s present in the {@link PlpProgram}
     * @return list of PlpFile
     */
    public List<String> getProgramFiles() {
        return Collections.unmodifiableList(programFiles);
    }

}
