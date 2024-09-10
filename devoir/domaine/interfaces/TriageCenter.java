package devoir.domaine.interfaces;

import devoir.domaine.Patient;
import devoir.enums.VisibleSymptom;
import java.util.LinkedList;

public interface TriageCenter {
    void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom);
}
