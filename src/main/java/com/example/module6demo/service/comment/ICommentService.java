package com.example.module6demo.service.comment;

import com.example.module6demo.model.Comment;
import com.example.module6demo.model.DTO.CommentDTO;
import com.example.module6demo.service.IGeneralService;

public interface ICommentService extends IGeneralService<Comment> {
    Iterable<Comment> CommentByHouseId(Long id);
//    Iterable<Order> createComment(Long id, Long houses_id);
    Iterable<Comment> getListCommentByHouseOfUserId(Long userId, Long start);
    Iterable<Comment> getAllByCommentAndIsReadTrue(Long userId);
    Iterable<Comment> getAllByCommentAndIsReadFalse(Long userId);

    Iterable<Comment> getCommentByHouseId(Long id, long start);
    Comment changeDTO(CommentDTO commentDTO);

}
