

public class Main {
    public static void main(String[] args){
        DatabaseControl DB = new DatabaseControl();
        //DB.insertRecord(230655, "Medeu", 18, "Software Engineer");
        DB.selectAll();
        DB.deleteStudent(23065);
        DB.selectAll();
        DB.Close();
    }
}
