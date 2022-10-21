package ir.eyrsa.app.ayinname.Model;

public class Exams_Model {
    int id;
    String nameExam;
    byte free;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameExam() {
        return nameExam;
    }

    public void setNameExam(String nameExam) {
        this.nameExam = nameExam;
    }

    public byte getFree() {
        return free;
    }

    public void setFree(byte free) {
        this.free = free;
    }
}
