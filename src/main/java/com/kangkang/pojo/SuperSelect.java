package com.kangkang.pojo;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Data
public class SuperSelect {
    String value;
    String label;
    ArrayList<SuperSelect>children;
}
