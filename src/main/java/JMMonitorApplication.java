import constant.JobType;

public class JMMonitorApplication {

    public static void main(String[] args){
//        String[] resultList = TaskManager.addJob(1, "0/10 * * * * ? ", "", "", "","", "");
//        System.out.println(resultList[1]);
        String result = TaskManager.addJob(1, "0/10 * * * * ? ", JobType.SPIDER_JOB);
        System.out.println(result);
    }
}
