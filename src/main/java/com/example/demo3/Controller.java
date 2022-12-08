package com.example.demo3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo3.jaden.GoodJadenDao;
import com.example.demo3.tony.GoodTonyDao;

@RestController
public class Controller {

    @Autowired
    private GoodJadenDao jadenDao;

    @GetMapping("/jaden")
    @ResponseBody
    public String getJadenGood(){
        String data="";
        List<Good> goods=jadenDao.findAll();
        for(Good good: goods){
            data+=good.getName()+"\t";
            data+=good.getCompany()+"\n";
        }
        return data;
    }

    @Autowired
    private GoodTonyDao tonyDao;

    @GetMapping("/tony")
    @ResponseBody
    public String getTonyGood(){
        String data="";
        List<Good> goods=tonyDao.findAll();
        for(Good good: goods){
            data+=good.getName()+"\t";
            data+=good.getCompany()+"\n";
        }
        return data;
    }    
}
