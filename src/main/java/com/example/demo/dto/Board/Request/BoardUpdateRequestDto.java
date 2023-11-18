package com.example.demo.dto.Board.Request;

<<<<<<< HEAD
import com.example.demo.model.Position;
=======
>>>>>>> develop
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardUpdateRequestDto {
    @NotBlank(message = "게시물 아이디는 필수 입력 값입니다.")
    private long boardId;

    @NotBlank(message = "게시물 제목은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "게시물 연락은 필수 입력 값입니다.")
    private String contact;

    @NotBlank(message = "게시물 제목은 필수 입력 값입니다.")
    private String content;

    @NotBlank(message = "게시물 모집 분야는 필수 입력 값입니다.")
    private List<Long> positions;
}
