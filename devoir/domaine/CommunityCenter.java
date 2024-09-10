package devoir.domaine;

import devoir.domaine.interfaces.TriageCenter;
import devoir.enums.TriageType;
import devoir.enums.VisibleSymptom;

import java.util.LinkedList;

public class CommunityCenter implements TriageCenter {

    private final TriageType generalTriageType;
    private final LinkedList<Patient> generalPatients;

    public CommunityCenter(TriageType generalTriageType) {
        this.generalTriageType = generalTriageType;
        generalPatients = new LinkedList<>();
    }

    @Override
    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom) {
        Patient newPatient = new Patient(name, gravity, visibleSymptom);
        triage(newPatient, generalPatients, generalTriageType);
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

    public LinkedList<Patient> getGeneralPatients() {
        return generalPatients;
    }
}
