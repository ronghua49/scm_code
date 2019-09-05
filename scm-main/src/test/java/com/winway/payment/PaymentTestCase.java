package com.winway.payment;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.base.id.IdGenerator;
import com.winway.scm.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class PaymentTestCase {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    protected IdGenerator idGenerator;

    @Test
    public void Test() {
        logger.debug("X7 payment test initialize.");
    }


    @Test
    public void test() {
        String json = "阿莫灵(20袋/盒),奥先干混(120袋/盒),奥先干混(12袋/盒),奥先干混(24袋/盒),奥先片(120片/盒),奥先片(12片/盒),奥先片(6片/盒),澳广(960粒/盒),澳广(480粒/盒),澳广(12粒/盒),澳博灵(10粒/盒),澳博灵(1000粒/盒),澳达先(10片/盒),澳达先(20片/盒),澳石林(60ml/瓶),奥松(50丸/瓶),奥松(150丸/瓶),奥贝爽(10g/支),奥贝爽(15g/支),奥络干混(12包/盒),奥络(15g/支),奥络(10g/支),奥络(5g/支),奥肯能(20粒/盒),奥肯能(10粒/盒),奥肯能(1000粒/盒),澳特斯(150ml/瓶),澳特斯(10ml*150袋),澳特斯(100ml/瓶),澳特斯(10ml*30袋),澳特斯(60ml/瓶),奥青(10g/支),奥青(20g/支),奥青(5g/支),奥亭(10ml*15袋),奥亭(10ml*150袋),奥亭(10ml*30袋),奥亭(150ml),尿通(80粒/盒),澳夫清(10g/支),澳夫清(30g/支),澳米沙(30ml/瓶),澳能(10g/支),澳能(15g/支),澳能(5g/支),澳琪(5g/支),宁之助（5g/支）,澳锐(960粒/盒),澳锐(24粒/盒),澳特欣(40粒/盒),澳特欣(30粒/盒),澳特欣(1000粒/盒),澳可修(20g/支),澳可修(2片/盒),奥泰灵0.75g(30粒/盒香港版),奥泰灵0.75g(500粒/盒),奥泰灵0.75g(30粒/盒海口版),奥泰灵0.75g(10粒/盒),奥泰灵0.24g(36粒/盒),奥泰灵0.75g(60粒/盒),奥泰灵0.75g(20粒/盒香港版),奥勃抒(10ml*15袋),奥勃抒(10ml*30袋),奥来舒(15g/支),奥来舒(5g/支),澳博达(30ml/瓶),澳博达(20ml/瓶),澳博达(10ml/瓶),澳托芬(20g/盒),奥亭片(24片/盒),奥亭片(600片/盒),奥亭片(12片/盒)";
        String[] split = json.split(".");
        System.out.println(split.length);
    }

}
