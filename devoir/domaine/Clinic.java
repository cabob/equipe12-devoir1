package devoir.domaine;

import devoir.enums.TriageType;
import devoir.enums.VisibleSymptom;

import java.util.LinkedList;

public class Clinic {

    private final TriageType doctorTriageType;
    private final TriageType radiologyTriageType;

    private final LinkedList<Patient> doctorPatients;
    private final LinkedList<Patient> radiologyPatients;

    public Clinic(TriageType doctorTriageType, TriageType radiologyTriageType) {
        this.doctorTriageType = doctorTriageType;
        this.radiologyTriageType = radiologyTriageType;
        doctorPatients = new LinkedList<>();
        radiologyPatients = new LinkedList<>();
    }

    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom) {
        Patient newPatient = new Patient(name, gravity, visibleSymptom);

        triage(newPatient, doctorPatients, doctorTriageType);

        if (visibleSymptom == VisibleSymptom.BROKEN_BONE || visibleSymptom == VisibleSymptom.SPRAIN) {
            triage(newPatient, radiologyPatients, radiologyTriageType);
        }
    }

    private void triage(Patient patient, LinkedList<Patient> patientsList, TriageType triageType) {
        if (triageType == TriageType.FIFO) {
            patientsList.addLast(patient);
        } else if (triageType == TriageType.GRAVITY) {
            if (patient.getGravity() > 5) {
                patientsList.addFirst(patient);
            } else {
                patientsList.addLast(patient);
            }
        }
    }

    public LinkedList<Patient> getDoctorPatients() {
        return doctorPatients;
    }

    public LinkedList<Patient> getRadiologyPatients() {
        return radiologyPatients;
    }
}
