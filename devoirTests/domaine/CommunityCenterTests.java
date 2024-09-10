package devoirTests.domaine;

import devoir.domaine.CommunityCenter;
import devoir.domaine.Patient;
import devoir.enums.TriageType;
import devoir.enums.VisibleSymptom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class CommunityCenterTests {

    private CommunityCenter communityCenter;

    @BeforeEach
    public void setUp() {
        // Initialize the CommunityCenter object before each test
    }

    @Test
    public void givenMigrainePatient_WhenTriageTypeIsFIFO_thenFirstInGeneralList() {
        communityCenter = new CommunityCenter(TriageType.FIFO);
        Patient newPatient = new Patient("John Doe", 6, VisibleSymptom.MIGRAINE);
        communityCenter.triagePatient(newPatient.getName(), newPatient.getGravity(), newPatient.getSymptom());

        LinkedList<Patient> generalPatients = communityCenter.getGeneralPatients();

        assertEquals(1, generalPatients.size());
        assertEquals(newPatient.getName(), generalPatients.get(0).getName());
        assertEquals(newPatient.getGravity(), generalPatients.get(0).getGravity());
        assertEquals(newPatient.getSymptom(), generalPatients.get(0).getSymptom());
    }

    @Test
    public void givenASecondPatientWithFlu_WhenTriageTypeIsFIFO_thenSecondInGeneralList() {
        communityCenter = new CommunityCenter(TriageType.FIFO);
        Patient firstPatient = new Patient("John Doe", 4, VisibleSymptom.MIGRAINE);
        Patient secondPatient = new Patient("Albert Doe", 4, VisibleSymptom.FLU);

        communityCenter.triagePatient(firstPatient.getName(), firstPatient.getGravity(), firstPatient.getSymptom());
        communityCenter.triagePatient(secondPatient.getName(), secondPatient.getGravity(), secondPatient.getSymptom());

        LinkedList<Patient> generalPatients = communityCenter.getGeneralPatients();

        assertEquals(2, generalPatients.size());
        assertEquals(secondPatient.getName(), generalPatients.get(1).getName());
        assertEquals(secondPatient.getGravity(), generalPatients.get(1).getGravity());
        assertEquals(secondPatient.getSymptom(), generalPatients.get(1).getSymptom());
    }

    @Test
    public void givenSprainPatient_WhenTriageTypeIsFIFO_thenFirstInGeneralListAndMarkedForSpecialCare() {
        communityCenter = new CommunityCenter(TriageType.FIFO);
        Patient newPatient = new Patient("John Doe", 6, VisibleSymptom.SPRAIN);
        communityCenter.triagePatient(newPatient.getName(), newPatient.getGravity(), newPatient.getSymptom());

        LinkedList<Patient> generalPatients = communityCenter.getGeneralPatients();

        assertEquals(1, generalPatients.size());
        assertEquals(newPatient.getName(), generalPatients.get(0).getName());
        assertEquals(newPatient.getGravity(), generalPatients.get(0).getGravity());
        assertEquals(newPatient.getSymptom(), generalPatients.get(0).getSymptom());
    }

    @Test
    public void givenASecondPatientWithHigherGravity_WhenTriageTypeIsGravity_thenFirstInGeneralList() {
        communityCenter = new CommunityCenter(TriageType.GRAVITY);
        Patient firstPatient = new Patient("John Doe", 4, VisibleSymptom.MIGRAINE);
        Patient secondPatient = new Patient("Albert Doe", 7, VisibleSymptom.FLU);

        communityCenter.triagePatient(firstPatient.getName(), firstPatient.getGravity(), firstPatient.getSymptom());
        communityCenter.triagePatient(secondPatient.getName(), secondPatient.getGravity(), secondPatient.getSymptom());

        LinkedList<Patient> generalPatients = communityCenter.getGeneralPatients();

        assertEquals(2, generalPatients.size());
        assertEquals(secondPatient.getName(), generalPatients.get(0).getName());
        assertEquals(secondPatient.getGravity(), generalPatients.get(0).getGravity());
        assertEquals(secondPatient.getSymptom(), generalPatients.get(0).getSymptom());
    }
}
