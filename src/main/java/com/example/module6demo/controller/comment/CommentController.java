package com.example.module6demo.controller.comment;

import com.example.module6demo.model.Comment;
import com.example.module6demo.model.DTO.CommentDTO;
import com.example.module6demo.service.comment.ICommentService;
import com.example.module6demo.service.house.HouseService;
import com.example.module6demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    ICommentService commentService;
    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;
    @GetMapping("/list")
    public ResponseEntity<Iterable<Comment>> HouseComment() {
        Iterable<Comment> users = commentService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/housecomment")
    public ResponseEntity<Comment> HouseCommentDTO(@RequestBody CommentDTO commentDTO){
//        Comment comment = new Comment();
//        comment.setIsRead(false);
//        comment.setComment(commentDTO.getComment());
//        comment.setHouse(houseService.findById(commentDTO.getHouseId()).get());
//        comment.setUser(userService.findById(commentDTO.getUserId()).get());

        return new ResponseEntity<>(commentService.changeDTO(commentDTO),HttpStatus.OK);
    }
    @GetMapping("/createcomment/{id}")
    public ResponseEntity<Iterable<Comment>> createComment(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(commentService.CommentByHouseId(id),HttpStatus.OK);
    }
    @GetMapping("/detail/{house_id}/{start}")
    public ResponseEntity<Iterable<Comment>> getListCommentByHouseOfUserId(@PathVariable Long house_id, @PathVariable Long start) {
        return new ResponseEntity<>(commentService.getListCommentByHouseOfUserId(house_id, start), HttpStatus.OK);
    }
    @GetMapping("/listCommentRead/{userId}")
    public ResponseEntity<Iterable<Comment>> CommentRead(@PathVariable(name = "userId") Long userId) {
        Iterable<Comment> users = commentService.getAllByCommentAndIsReadTrue(userId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/listCommentNotRead/{userId}")
    public ResponseEntity<Iterable<Comment>> CommentNotRead(@PathVariable(name = "userId") Long userId) {
        Iterable<Comment> users = commentService.getAllByCommentAndIsReadFalse(userId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping("/updateIsRead/{id}")
    public ResponseEntity<Comment> updateIsRead(@PathVariable("id") Long id){
        Comment comment = commentService.findById(id).get();
        comment.setIsRead(true);
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
    @GetMapping("/getCommentByHouseIdPaging/{id}/{start}")
    public ResponseEntity<Iterable<Comment>> commentByHouseIdPagin(@PathVariable("id") Long houseId, @PathVariable("start") long start){
        return new ResponseEntity<>(commentService.getCommentByHouseId(houseId, start), HttpStatus.OK);
    }
}
