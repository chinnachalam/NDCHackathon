package tcs.ndc.hackathon.ndcrest.model.miles.response;

import java.util.ArrayList;
import java.util.List;

public class Flight {
    private String id;
    private List<Program> programs = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
}
