import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class PatientTest {

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
  public void Task_instantiatesCorrectly_true() {
    Patient myPatient = new Patient("Jake", "2000-10-03", 1);
    assertEquals(true, myPatient instanceof Patient);
  }

  @Test
  public void Task_instantiatesWithDescription_String() {
    Patient myPatient = new Patient("Jake", "2000-10-03", 1);
    assertEquals("Jake", myPatient.getName());
  }
  @Test
  public void getBirthday_works(){
    Patient myPatient = new Patient("Jake", "2000-10-03", 1);
    assertEquals("10-03-2000", myPatient.getBirthday());
  }

  @Test
  public void all_returnsAllInstancesOfPatient_true() {
    Patient firstPatient = new Patient("Jake", "2000-10-03", 1);
    firstPatient.save();
    Patient secondPatient = new Patient("Sara","2000-10-03", 1);
    secondPatient.save();
    assertEquals(true, Patient.all().get(0).equals(firstPatient));
    assertEquals(true, Patient.all().get(1).equals(secondPatient));
  }

  @Test
  public void clear_emptiesAllPatientsFromArrayList_0() {
    Patient myPatient = new Patient("Jake", "2000-10-03", 1);
    assertEquals(Patient.all().size(), 0);
  }

  @Test
  public void getId_tasksInstantiateWithAnID_1() {
    Patient myPatient = new Patient("Jake", "2000-10-03", 1);
    myPatient.save();
    assertTrue(myPatient.getId() > 0);
  }

  @Test
  public void find_returnsPatientWithSameId_secondPatient() {
    Patient firstPatient = new Patient("Jake", "2000-10-03", 1);
    firstPatient.save();
    Patient secondPatient = new Patient("Sara", "2000-10-03", 1);
    secondPatient.save();
    assertEquals(Patient.find(secondPatient.getId()), secondPatient);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Patient firstPatient = new Patient("Jake", "2000-10-03", 1);
    Patient secondPatient = new Patient("Jake", "2000-10-03", 1);
    assertTrue(firstPatient.equals(secondPatient));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Patient myPatient = new Patient("Jake", "2000-10-03", 1);
    myPatient.save();
    assertTrue(Patient.all().get(0).equals(myPatient));
  }

  // @Test
  // public void save_assignsIdToObject() {
  //   Patient myPatient = new Patient("Jake", "2000-10-03", 1);
  //   myPatient.save();
  //   Patient savedPatient = Patient.all().get(0);
  //   assertEquals(myPatient.getId(), savedPatient.getId());
  // }

  // @Test
  // public void save_savesDoctorIdIntoDB_true() {
  //   Doctor myDoctor = new Doctor("Smith", "surgeon");
  //   myDoctor.save();
  //   Patient myPatient = new Patient("Jake", "2000-10-03", myDoctor.getId());
  //   myPatient.save();
  //   Patient savedPatient = Patient.find(myPatient.getId());
  //   assertEquals(savedPatient.getDoctorId(), myDoctor.getId());
  // }


}
