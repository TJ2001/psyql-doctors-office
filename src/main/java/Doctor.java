import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Doctor{
  private String name;
  private String specialty;
  private int id;

  public Doctor(String name, String specialty){
    this.name = name;
    this.specialty = specialty;
  }

  public String getName() {
    return name;
  }

  public String getSpecialty() {
    return specialty;
  }

  public static List<Doctor> all() {
    String sql = "SELECT * FROM doctors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Doctor.class);
    }
  }

  public int getId() {
    return id;
  }

  public static Doctor find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM doctors where id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Doctor.class);
    }
  }

  public List<Patient> getPatients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients where doctorId=:id;";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Patient.class);
    }
  }

  @Override
  public boolean equals(Object otherDoctor) {
    if(!(otherDoctor instanceof Doctor)) {
      return false;
    } else {
      Doctor newDoctor = (Doctor) otherDoctor;
      return this.getName().equals(newDoctor.getName()) &&
              this.getId() == newDoctor.getId() &&
              this.getSpecialty().equals(newDoctor.getSpecialty());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO doctors (name, specialty) VALUES(:name, :specialty)";
        this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("specialty", this.specialty)
        .executeUpdate()
        .getKey();
    }
  }

  public void delete(){
    String sql = "DELETE from doctors WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
    String sql2 = "DELETE from patients WHERE doctorId = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql2)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }
}
