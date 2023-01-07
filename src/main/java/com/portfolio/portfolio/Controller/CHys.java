package com.portfolio.portfolio.Controller;

import com.portfolio.portfolio.Entity.hys;
import com.portfolio.portfolio.Security.Controller.Mensaje;
import com.portfolio.portfolio.Service.Shys;
import com.portfolio.portfolio.dto.dtohys;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/skill")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://portfolio-ef698.web.app/")
public class CHys {
    @Autowired
    Shys shys;
    @GetMapping("/lista")
    public ResponseEntity<List<hys>> list(){
        List<hys> list = shys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody dtohys dtoHys){
        if(StringUtils.isBlank(dtoHys.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(shys.existsByNombre(dtoHys.getNombre()))
            return new ResponseEntity (new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        
        hys Hys = new hys(dtoHys.getNombre(), dtoHys.getPorcentaje(), dtoHys.getColor());
        shys.save(Hys);
        
        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtohys dtoHys){
        //validamos si existe el ID
        if(!shys.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        //compara nombres de educacion
        if(shys.existsByNombre(dtoHys.getNombre()) && shys.getByNombre(dtoHys.getNombre()).get().getId() != id)
             return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        //No puede estar vacio
        if(StringUtils.isBlank(dtoHys.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        hys Hys = shys.getOne(id).get();
        Hys.setNombre(dtoHys.getNombre());
        Hys.setPorcentaje(dtoHys.getPorcentaje());
        Hys.setColor(dtoHys.getColor());
        
        shys.save(Hys);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<hys> getById(@PathVariable("id") int id){
        if(!shys.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        hys Hys = shys.getOne(id).get();
        return new ResponseEntity(Hys, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
     //validamos si existe el ID
        if(!shys.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        shys.delete(id);
        
        return new ResponseEntity(new Mensaje("Skill Eliminada"), HttpStatus.OK);
    }
}
