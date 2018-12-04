package testcase;


import model.Member;
import org.junit.BeforeClass;
import org.junit.Test;

public class MemberTest {
    static Member m=new Member();

    @BeforeClass
    public static void beforeClassMember(){
        m.loadMember();
        m.setContent("$.email", "xxx@email.com"); //模版中已存在参数
        m.setContent("$.mobile", String.valueOf(System.currentTimeMillis())); //模版中已存在参数
        m.putContent("$","age", "18");  //模版不存在参数,动态增加可选参数
        System.out.println(m.getContent());
    }

    @Test
    public void addRight(){

    }


}
