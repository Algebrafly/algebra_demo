package com.algebra.jpa.web;

import com.algebra.jpa.entity.SysDictionary;
import com.algebra.jpa.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author al
 * @date 2021/2/5 11:41
 * @description
 */
@RestController
public class DictionaryController {

    @Autowired
    DictionaryService dictionaryService;

    @GetMapping("/getDictionaryOneByType")
    public SysDictionary getDictionaryOneByType(@RequestParam("type") String type){
        return dictionaryService.getOneByType(type);
    }

    @GetMapping("/getDictionaryAll")
    public List<SysDictionary> getDictionaryAll(){
        return dictionaryService.getAll();
    }

}
