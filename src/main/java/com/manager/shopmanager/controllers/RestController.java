package com.manager.shopmanager.controllers;


import com.manager.shopmanager.model.Boutique;
import com.manager.shopmanager.service.BoutiqueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Resource
    private BoutiqueServiceImpl boutiqueService;



    @RequestMapping("/Boutique")
    public @ResponseBody String greeting() {
        StringBuilder res = new StringBuilder();
        for (Boutique b : boutiqueService.getAllboutiques()
             ) {
            res.append(b.toString());
        }
        return res.toString();
    }




}
