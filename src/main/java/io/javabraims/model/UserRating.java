package io.javabraims.model;

import lombok.Data;

import java.util.List;

/**
 * Created by 38066 on 19.03.2019.
 */
@Data
public class UserRating {

    private List<Rating> ratingList;
}
