package devoirTests.domaine;

import devoir.domaine.Clinic;
import devoir.domaine.Patient;
import devoir.enums.TriageType;
import devoir.enums.VisibleSymptom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class ClinicTests {

    private Clinic clinic;

    @BeforeEach
    public void setUp() {
        clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);
    }

    @Test
    public void givenMigrainePatient_WhenTriageTypeIsFIFO_thenFirstInDoctorListAndNotInRadiologyList() {
        Patient newPatient = new Patient("John Doe", 6, VisibleSymptom.MIGRAINE);
        clinic.triagePatient(newPatient.getName(), newPatient.getGravity(), newPatient.getSymptom());

        LinkedList<Patient> doctorPatients = clinic.GetDoctorPatients();
        LinkedList<Patient> radiologyPatients = clinic.GetRadiologyPatients();

        assertEquals(1, doctorPatients.size());
        assertEquals(newPatient.getName(), doctorPatients.get(0).getName());
        assertEquals(newPatient.getGravity(), doctorPatients.get(0).getGravity());
        assertEquals(newPatient.getSymptom(), doctorPatients.get(0).getSymptom());
        assertEquals(0, radiologyPatients.size());
    }

    @Test
    public void givenASecondPatientWithFlu_WhenTriageTypeIsFIFO_thenSecondInDoctorListAndNotInRadiologyList() {
        Patient firstPatient = new Patient("John Doe", 4, VisibleSymptom.MIGRAINE);
        Patient secondPatient = new Patient("Albert Doe", 4, VisibleSymptom.FLU);

        clinic.triagePatient(firstPatient.getName(), firstPatient.getGravity(), firstPatient.getSymptom());
        clinic.triagePatient(secondPatient.getName(), secondPatient.getGravity(), secondPatient.getSymptom());

        LinkedList<Patient> doctorPatients = clinic.GetDoctorPatients();
        LinkedList<Patient> radiologyPatients = clinic.GetRadiologyPatients();

        assertEquals(2, doctorPatients.size());
        assertEquals(secondPatient.getName(), doctorPatients.get(1).getName());
        assertEquals(secondPatient.getGravity(), doctorPatients.get(1).getGravity());
        assertEquals(secondPatient.getSymptom(), doctorPatients.get(1).getSymptom());
        assertEquals(0, radiologyPatients.size());
    }

    @Test
    public void givenSprainPatient_WhenTriageTypeIsFIFO_thenFirstInDoctorListAndFirstRadiologyList() {
        Patient newPatient = new Patient("John Doe", 6, VisibleSymptom.SPRAIN);
        clinic.triagePatient(newPatient.getName(), newPatient.getGravity(), newPatient.getSymptom());

        LinkedList<Patient> doctorPatients = clinic.GetDoctorPatients();
        LinkedList<Patient> radiologyPatients = clinic.GetRadiologyPatients();

        assertEquals(1, doctorPatients.size());
        assertEquals(newPatient.getName(), doctorPatients.get(0).getName());
        assertEquals(newPatient.getGravity(), doctorPatients.get(0).getGravity());
        assertEquals(newPatient.getSymptom(), doctorPatients.get(0).getSymptom());
        assertEquals(1, radiologyPatients.size());
        assertEquals(newPatient.getName(), radiologyPatients.get(0).getName());
        assertEquals(newPatient.getGravity(), radiologyPatients.get(0).getGravity());
        assertEquals(newPatient.getSymptom(), radiologyPatients.get(0).getSymptom());
    }
}