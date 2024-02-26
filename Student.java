public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private String major;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getMajor() {
        return major;
    }

    public Student(Integer id, String name, Integer age, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.major = major;
    }


}
