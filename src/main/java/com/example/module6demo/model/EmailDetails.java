package com.example.module6demo.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailDetails {
    //    nguoi nhan
    private String recipient;
    //    body
    private String msgBody;
    //    tieu de
    private String subject;
    private String attachment;
}
