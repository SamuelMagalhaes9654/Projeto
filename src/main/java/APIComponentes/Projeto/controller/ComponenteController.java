package APIComponentes.Projeto.controller;

import APIComponentes.Projeto.domain.Componente;
import APIComponentes.Projeto.request.ComponentePostRequestBody;
import APIComponentes.Projeto.request.ComponentePutRequestBody;
import APIComponentes.Projeto.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import APIComponentes.Projeto.service.ComponenteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("componentes")
@Log4j2
@RequiredArgsConstructor

public class ComponenteController {
    private final DateUtil dateUtil;
    private final ComponenteService componenteService;

    @GetMapping
    public ResponseEntity<List<Componente>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(componenteService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Componente> findById(@PathVariable long id){
        return ResponseEntity.ok(componenteService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Componente> save(@RequestBody ComponentePostRequestBody componentePostRequestBody){
        return new ResponseEntity<>(componenteService.save(componentePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        componenteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody ComponentePutRequestBody componentePutRequestBody){
        componenteService.replace(componentePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
