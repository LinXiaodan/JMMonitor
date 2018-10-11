import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.ArrayList;
import java.util.List;

public class testJson2 {
    //json字符串-简单对象型
    private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
    //json字符串-数组类型
    private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
    //复杂格式json字符串
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";


    public static class Student {

        private String studentName;
        private Integer studentAge;

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public Integer getStudentAge() {
            return studentAge;
        }

        public void setStudentAge(Integer studentAge) {
            this.studentAge = studentAge;
        }
    }

    public static class Course {

        private String courseName;
        private Integer code;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }

    public static class Teacher {

        private String teacherName;
        private Integer teacherAge;
        private Course course;
        private List<Student> students;

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public Integer getTeacherAge() {
            return teacherAge;
        }

        public void setTeacherAge(Integer teacherAge) {
            this.teacherAge = teacherAge;
        }

        public Course getCourse() {
            return course;
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }
    }

    /**
     * json字符串-简单对象与JavaBean_obj之间的转换
     */
    public static void testJSONStrToJavaBeanObj(){
        System.out.println("[str to object]");
        Student student = JSON.parseObject(JSON_OBJ_STR, Student.class, Feature.AllowUnQuotedFieldNames);
        //Student student1 = JSONObject.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});//因为JSONObject继承了JSON，所以这样也是可以的

        System.out.println(student.getStudentName()+":"+student.getStudentAge());

    }

    /**
     * json字符串-数组类型与JavaBean_List之间的转换
     */
    public static void testJSONStrToJavaBeanList(){
        System.out.println("[str to array]");
        ArrayList<Student> students = JSON.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});
        //ArrayList<Student> students1 = JSONArray.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});//因为JSONArray继承了JSON，所以这样也是可以的

        for (Student student : students) {
            System.out.println(student.getStudentName()+":"+student.getStudentAge());
        }
    }

    /**
     * 复杂json格式字符串与JavaBean_obj之间的转换
     */
    public static void testComplexJSONStrToJavaBean(){
        System.out.println("[complex str to object]");
        Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});
        //Teacher teacher1 = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});//因为JSONObject继承了JSON，所以这样也是可以的
        String teacherName = teacher.getTeacherName();
        Integer teacherAge = teacher.getTeacherAge();
        Course course = teacher.getCourse();
        List<Student> students = teacher.getStudents();

        System.out.println(teacherName);
        System.out.println(teacherAge);
        System.out.println(course.getCourseName() + ": " + course.getCode());

        for(Student student: students){
            System.out.println(student.getStudentName() + ": " + student.getStudentAge());
        }
    }

    public static void main(String[] args){
        testJSONStrToJavaBeanObj();
        testJSONStrToJavaBeanList();
        testComplexJSONStrToJavaBean();
    }
}
