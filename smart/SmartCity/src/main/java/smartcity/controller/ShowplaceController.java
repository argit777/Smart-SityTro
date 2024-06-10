package ru.smartcity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.smartcity.dto.ResponseResult;
import ru.smartcity.model.Showplace;
import ru.smartcity.service.ShowplaceService;
import ru.smartcity.util.ShowplaceType;

import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/showplace")
public class ShowplaceController {
    private final ShowplaceService showplaceService;

    @PostMapping
    public ResponseEntity<ResponseResult<Showplace>> addShowplace(@RequestBody Showplace showplace){
        try {
            this.showplaceService.add(showplace);
            return new ResponseEntity<>(new ResponseResult<>(null, showplace), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Showplace>>> getShowplaces(@RequestParam ShowplaceType type){
        if (type == null){
            List<Showplace> showplaces = this.showplaceService.get();
            return new ResponseEntity<>(new ResponseResult<>(null, showplaces), HttpStatus.OK);
        }else {
            try {
                List<Showplace> showplaces = this.showplaceService.getByType(type);
                return new ResponseEntity<>(new ResponseResult<>(null, showplaces), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/{showplaceId}")
    public ResponseEntity<ResponseResult<Showplace>> getShowplace(@PathVariable long showplaceId){
        try {
            Showplace showplace = this.showplaceService.getById(showplaceId);
            return new ResponseEntity<>(new ResponseResult<>(null, showplace), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Showplace>> update(@RequestBody Showplace showplace){
        try {
            Showplace update = this.showplaceService.update(showplace);
            return new ResponseEntity<>(new ResponseResult<>(null, update), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{showplaceId}")
    public ResponseEntity<ResponseResult<Showplace>> delete(@PathVariable long showplaceId){
        try {
            Showplace delete = this.showplaceService.delete(showplaceId);
            return new ResponseEntity<>(new ResponseResult<>(null, delete), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
