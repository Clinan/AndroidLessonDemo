package cn.edu.hdu.lessontest13;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by arter on 2018/6/11.
 */

public class SeniorMember {

    private String id;
    private String name;
    private Date bornDay;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBornDay() {
        return bornDay;
    }

    public void setBornDay(Date bornDay) {
        this.bornDay = bornDay;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("YY-MM-DD");
        return "SeniorMember [id=" + id + ", name=" + name + ", bornDay=" + format.format(bornDay) + "]";
    }

    public static void main(String[] args) {
        SeniorMember[] seniorMembers = new SeniorMember[6];
        int snoStart = 125;

        for (int i = 0; i < seniorMembers.length; i++) {
            SeniorMember s = seniorMembers[i];
            s.setId("0000" + (i + snoStart));
            s.setName("Tom"+i);
            s.setBornDay(new Date());
        }

        //第一题
        for (SeniorMember member:seniorMembers){
            System.out.println(member.toString());
        }


    }
}
