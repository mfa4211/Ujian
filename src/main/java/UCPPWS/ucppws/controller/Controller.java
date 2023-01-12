/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UCPPWS.ucppws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import UCPPWS.ucppws.model.entity.BukanManusia;
import UCPPWS.ucppws.model.jpa.BukanManusiaJpaController;

/**
 *
 * @author ThinkPad
 */
@RestController 
@CrossOrigin
@RequestMapping("/BukanManusia")
public class Controller {
    
    BukanManusia jam = new BukanManusia(); //buat manggil entity
    BukanManusiaJpaController nim = new BukanManusiaJpaController(); //manggil jpa
    
    @GetMapping
    public List<BukanManusia> getData(){
        List<BukanManusia> data = new ArrayList<>(); //buat object baru
        try{
            data = nim.findBukanManusiaEntities(); //get alldata
            
        }catch(Exception e){} 
        return data; // return objek data
    }
    
    @GetMapping("/{Id}") // get data by id 
    public List<BukanManusia> getBukanManusiaEntities (@PathVariable("fahri") int id){ 
    List<BukanManusia> dataa = new ArrayList<>(); //create objek baru
    try{
        jam = nim.findBukanManusia(id); //get data dari id
        dataa.add(jam); // nambahin data
    }catch(Exception e){}
        return dataa;
    }
    
    @PostMapping //untuk menambahkan data
    public String insertData(HttpEntity<String> requestdata){ //nerima input data dari user
        String message = "Data anda berhasil ditambahkan"; // memberi message
        BukanManusia ente = null;
        try{
            String json_receive = requestdata.getBody(); 
            ObjectMapper map = new ObjectMapper(); //ngambil dr json dikumpulin 
            ente = map.readValue(json_receive, BukanManusia.class);
            nim.create(ente);
            
        }catch(Exception e){
        message = "data gagal ditambahkan";
        }
        return message;
    }
        @PutMapping //untuk edit
        public String updateData(HttpEntity<String> requestdata){
        String message = "Sukses MengUpdate";
        BukanManusia ente = null;
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            ente = map.readValue(json_receive, BukanManusia.class);
            nim.edit(ente);
            
        }catch(Exception e){
        message = "gagal mengupdate";
        }
        return message;
    }
        @DeleteMapping
        public String deleteData(HttpEntity<String> requestdata){
        String message = "Sukses Menghapus";
        BukanManusia ente = null;
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            ente = map.readValue(json_receive, BukanManusia.class);
            nim.destroy(ente.getId());
            
        }catch(Exception e){
        message = "gagal menghapus";
        }
        return message;
    }
    
}