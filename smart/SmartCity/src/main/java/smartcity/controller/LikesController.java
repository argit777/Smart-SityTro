package ru.smartcity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.smartcity.dto.ResponseResult;
import ru.smartcity.model.Showplace;
import ru.smartcity.service.UserLikesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {
    private final UserLikesService userLikesService;

    @PostMapping
    public ResponseEntity<ResponseResult<String>> addLike(@RequestParam long userId, @RequestParam long showplaceId){
        try {
            this.userLikesService.add(userId, showplaceId);
            return new ResponseEntity<>(new ResponseResult<>(null, "Showplace added to likes"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseResult<List<Showplace>>> getUserLikes(@PathVariable long userId){
        List<Showplace> userLikes = this.userLikesService.getUserLikes(userId);
        return new ResponseEntity<>(new ResponseResult<>(null, userLikes), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseResult<String>> deleteLike(@RequestParam long userId, @RequestParam long showplaceId){
        try {
            this.userLikesService.delete(userId, showplaceId);
            return new ResponseEntity<>(new ResponseResult<>(null, "Showplace deleted from likes"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
