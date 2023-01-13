package com.example.module6demo.service.comment;

import com.example.module6demo.model.Comment;
import com.example.module6demo.model.DTO.CommentDTO;
import com.example.module6demo.repository.ICommentRepository;
import com.example.module6demo.service.house.IHouseService;
import com.example.module6demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CommentService implements ICommentService{
    @Autowired
    ICommentRepository commentRepository;

    @Autowired
    IHouseService houseService;
    @Autowired
    IUserService userService;
    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Iterable<Comment> CommentByHouseId(Long id) {
        return commentRepository.CommentByHouseId(id);
    }

    @Override
    public Iterable<Comment> getListCommentByHouseOfUserId(Long userId, Long start) {
        return commentRepository.getListCommentByHouseOfUserId(userId, 5 * start);
    }

    @Override
    public Iterable<Comment> getAllByCommentAndIsReadTrue(Long userId) {
        return commentRepository.getAllByCommentAndIsReadTrue(userId);
    }

    @Override
    public Iterable<Comment> getAllByCommentAndIsReadFalse(Long userId) {
        return commentRepository.getAllByCommentAndIsReadFalse(userId);
    }

    @Override
    public Iterable<Comment> getCommentByHouseId(Long id, long start) {
        return getCommentByHouseId(id, start * 5);
    }

    public Comment changeDTO(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setIsRead(false);
        comment.setComment(commentDTO.getComment());
        comment.setHouse(houseService.findById(commentDTO.getHouseId()).get());
        comment.setUser(userService.findById(commentDTO.getUserId()).get());
        return save(comment);
    }
}
