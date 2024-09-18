package com.ALOTs.web.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "iAmUser")
public class iAmUser {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true) // will make the username unique and indexed for better filtering
    private String username;
    @NonNull
    private String password;

    @DBRef // used to make this alotsUser a reference database to ALOTsusers
    private List<ALOTsUser> alotsUser = new ArrayList<>(); // new ArrayList<>() because we don't want to initialize the list to null but to empty list :)


}
