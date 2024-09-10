package devoir.domaine;
import devoir.enums.TriageType;
import devoir.enums.VisibleSymptom;

import java.util.LinkedList;
import java.util.ListIterator;

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

        if(doctorTriageType == TriageType.FIFO) {
            if(gravity > 5){
                doctorPatients.addFirst(newPatient);
            }
            else{
                doctorPatients.addLast(newPatient);
            }
        } else if(doctorTriageType == TriageType.GRAVITY) {
            addPatientInDoctorListByGravity(newPatient, gravity);
        }

        if(visibleSymptom == VisibleSymptom.BROKEN_BONE || visibleSymptom == VisibleSymptom.SPRAIN){
            if(radiologyTriageType == TriageType.FIFO) {
                radiologyPatients.addLast(newPatient);
            }
        }
    }

    public LinkedList<Patient> GetDoctorPatients() {
        return doctorPatients;
    }

    public LinkedList<Patient> GetRadiologyPatients() {
        return radiologyPatients;
    }

    private void addPatientInDoctorListByGravity(Patient patient, int gravity) {

        if (doctorPatients.isEmpty()) {
            doctorPatients.add(patient);
            return;
        }

        ListIterator<Patient> iterator = doctorPatients.listIterator();
        while (iterator.hasNext()) {
            Patient current = iterator.next();

            if (current.getGravity() < gravity) {
                iterator.previous();
                iterator.add(patient);
                return;
            }
        }

        doctorPatients.addLast(patient);
    }
}