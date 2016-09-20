import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class DoctorTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctors_office_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deletePatientsQuery = "DELETE FROM patients *;";
      String deleteDoctorQuery = "DELETE FROM doctors *;";
      con.createQuery(deletePatientsQuery).executeUpdate();
      con.createQuery(deleteDoctorQuery).executeUpdate();
    }
  }

  @Test
  public void doctor_instantiatesCorrectly_true() {
    Doctor testDoctor = new Doctor("Smith", "surgeon");
    assertEquals(true, testDoctor instanceof Doctor);
  }

  @Test
  public void getName_doctorInstantiesName_Smith() {
    Doctor testDoctor = new Doctor("Smith", "surgeon");
    assertEquals("Smith", testDoctor.getName());
  }

  @Test
  public void all_returnsInstantiatesOfDoctor_true() {
    Doctor firstDoctor = new Doctor("Smith", "surgeon");
    firstDoctor.save();
    Doctor secondDoctor = new Doctor("Doe", "pediatrics");
    secondDoctor.save();
    assertEquals(true, Doctor.all().get(0).equals(firstDoctor));
    assertEquals(true, Doctor.all().get(1).equals(secondDoctor));
  }

  @Test
  public void find_returnsDoctorWithSameId_secondDoctor() {
    Doctor firstDoctor = new Doctor("Smith", "surgeon");
    firstDoctor.save();
    Doctor secondDoctor = new Doctor("Doe", "pediatrics");
    secondDoctor.save();
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Doctor firstDoctor = new Doctor("Smith", "surgeon");
    Doctor secondDoctor = new Doctor("Smith", "surgeon");
    assertTrue(firstDoctor.equals(secondDoctor));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Doctor myDoctor = new Doctor("Smith", "surgeon");
    myDoctor.save();
    Doctor saveDoctor = Doctor.all().get(0);
    assertTrue(Doctor.all().get(0).equals(myDoctor));
  }

  @Test
  public void save_assignsIdToObject() {
    Doctor myDoctor = new Doctor("Smith", "surgeon");
    myDoctor.save();
    Doctor savedDoctor = Doctor.all().get(0);
    assertEquals(myDoctor.getId(),savedDoctor.getId());
  }

  // @Test
  // public void getDoctors_retrievesAllPatientsFromDatabase_patientsList() {
  //   Doctor myDoctor = new Doctor("Smith", "surgeon");
  //   myDoctor.save();
  //   Patient firstPatient = new Patient("Jake", "2000-10-03", myDoctor.getId());
  //   firstPatient.save();
  //   Patient secondPatient = new Patient("Sara", "1990-03-22", myDoctor.getId());
  //   secondPatient.save();
  //   Patient[] patients = new Patient[] { firstPatient, secondPatient };
  //   assertTrue(myDoctor.getPatients().containsAll(Arrays.asList(patients)));
  // }
}
