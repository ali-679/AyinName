package ir.eyrsa.app.ayinname.Model;

public class Questions_Model {
    int id,idExam;
    String linkImage,nameQuestion,op1,op2,op3,op4,help;
    int gozine,opCorrect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getNameQuestion() {
        return nameQuestion;
    }

    public void setNameQuestion(String nameQuestion) {
        this.nameQuestion = nameQuestion;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public int getGozine() {
        return gozine;
    }

    public void setGozine(int gozine) {
        this.gozine = gozine;
    }

    public int getOpCorrect() {
        return opCorrect;
    }

    public void setOpCorrect(int opCorrect) {
        this.opCorrect = opCorrect;
    }
}
