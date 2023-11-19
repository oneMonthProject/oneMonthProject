package com.example.demo.dto.board.Request;

import com.example.demo.model.Position;
import com.example.demo.model.TechnologyStack;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardSearchRequestDto {
    private List<Long> technologyIds;
    private String keyWord;
    private List<Long> positionIds;
}
