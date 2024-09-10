package devoir;

import devoir.domaine.Clinic;
import devoir.enums.TriageType;
import devoir.enums.VisibleSymptom;

public class Main {
    public static void main(String[] args) {
        TriageType doctorTriageType = TriageType.FIFO;
        TriageType radiologyTriageType = TriageType.FIFO;

        Clinic clinic = new Clinic(doctorTriageType, radiologyTriageType);
        clinic.triagePatient("John", 4, VisibleSymptom.MIGRAINE);

        System.out.println(clinic.getRadiologyPatients());
        System.out.println(clinic.getDoctorPatients());
    }
}
