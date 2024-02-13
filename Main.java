package FinalProject;

public class Main {
    public static void main(String[] args){
        DatabaseControl DB = new DatabaseControl();
        DB.insertRecord(23065, "Almas", 17, "Software Engineer");
        //DB.selectAll();
        DB.updateStudent(23065);
        DB.Close();
    }
}
