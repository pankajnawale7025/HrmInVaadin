package org.vaadin.example.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response {
    private boolean isSuccess;
    private List<String> errorMessage=new ArrayList<>();
    private Object responseData;

}
