package devoir.domaine;

import devoir.enums.VisibleSymptom;

public class Patient {
    private String name;
    private int gravity;
    private VisibleSymptom symptom;

    public Patient(String name, int gravity, VisibleSymptom symptom) {
        this.name = name;
        this.gravity = gravity;
        this.symptom = symptom;
    }

    public String getName() {
        return name;
    }

    public int getGravity() {
        return gravity;
    }

    public VisibleSymptom getSymptom() {
        return symptom;
    }
}
