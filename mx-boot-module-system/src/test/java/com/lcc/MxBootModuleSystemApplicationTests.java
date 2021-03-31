package com.lcc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MxBootModuleSystemApplicationTests {
    @Test
    public void dirction(){
        System.out.println(System.getProperty("user.dir"));
    }

}
