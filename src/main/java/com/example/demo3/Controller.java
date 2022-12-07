package com.example.demo3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
