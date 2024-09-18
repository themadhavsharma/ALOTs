package com.ALOTs.web.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "ALOTsusers")
public class ALOTsUser {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String username;
    @NonNull
    private String name;
    private String email;
    private String phoneNumber;
    @NonNull
    private String password;
    private LocalDateTime date;

}
