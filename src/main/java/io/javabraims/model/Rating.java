package io.javabraims.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 38066 on 19.03.2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    private String movieId;
    private int rating;
}
