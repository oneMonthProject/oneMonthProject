package com.example.demo.service;

import static com.example.demo.constant.ProjectStatus.RECRUITING;
import static com.example.demo.model.QBoard.board;
import static com.example.demo.model.QBoardPosition.boardPosition;
import static com.example.demo.model.QProject.project;

import com.example.demo.dto.Board.Request.BoardSearchRequestDto;
import com.example.demo.dto.Board.Response.BoardCreateResponseDto;
import com.example.demo.dto.Board.Response.BoardSearchResponseDto;
import com.example.demo.dto.Board.Response.BoardUpdateResponseDto;
import com.example.demo.dto.BoardProject.Request.BoardProjectCreateRequestDto;
import com.example.demo.dto.BoardProject.Request.BoardProjectUpdateRequestDto;
import com.example.demo.dto.BoardProject.Response.BoardProjectCreateResponseDto;
import com.example.demo.dto.BoardProject.Response.BoardProjectUpdateResponseDto;
import com.example.demo.dto.Project.Response.ProjectCreateResponseDto;
import com.example.demo.dto.Project.Response.ProjectUpdateResponseDto;
import com.example.demo.global.exception.customexception.BoardCustomException;
import com.example.demo.global.exception.errorcode.BoardErrorCode;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BoardService {

    @Autowired PositionRepository positionRepository;

    @Autowired BoardRepository boardRepository;

    @Autowired UserRepository userRepository;

    @Autowired ProjectRepository projectRepository;

    @Autowired BoardPositionRepository boardPositionRepository;

    @Autowired private JPAQueryFactory queryFactory;

    /**
     * 게시글 목록 검색
     *
     * @param dto
     * @return List<BoardSearchResponseDto>
     */
    @Transactional(readOnly = true)
    public List<BoardSearchResponseDto> search(BoardSearchRequestDto dto) {
        QBoard board = QBoard.board;
        QProject project = QProject.project;
        QUser user = QUser.user;

        BooleanBuilder builder = new BooleanBuilder();

        if (dto.getKeyWord() != null && !dto.getKeyWord().isEmpty()) {
            builder.or(board.title.eq(dto.getKeyWord()));
            builder.or(board.content.eq(dto.getKeyWord()));
        }

        if (dto.getPositionIds().size() > 0) {
            builder.or(containsPositionIds(dto.getPositionIds()));
        }

        List<Board> boards =
                queryFactory
                        .select(board)
                        .from(board)
                        .leftJoin(board.project, project)
                        .fetchJoin()
                        .where(builder)
                        .fetch();

        List<BoardSearchResponseDto> boardSearchResponseDtos = new ArrayList<>();

        for (Board boardEntity : boards) {
            boardSearchResponseDtos.add(BoardSearchResponseDto.of(boardEntity));
        }

        return boardSearchResponseDtos;
    }

    public BooleanExpression containsPositionIds(List<Position> positionIds) {
        QBoardPosition qBoardPosition = boardPosition;
        Long count =
                queryFactory
                        .select(qBoardPosition.count())
                        .from(qBoardPosition)
                        .where(qBoardPosition.position.in(positionIds))
                        .fetchFirst();

        if (count > 0) {
            return Expressions.asBoolean(true);
        } else {
            return Expressions.asBoolean(false);
        }
    }

    /**
     * 게시글, 프로젝트 생성
     * @param dto
     * @return
     */
    public BoardProjectCreateResponseDto create(BoardProjectCreateRequestDto dto){
        User tempUser = userRepository.findById(1L).orElseThrow(() -> BoardCustomException.NOT_FOUND_BOARD); // 나중에 Security로 고쳐야 함.

        // project 생성
        Project project =
                Project.builder()
                        .name(dto.getProject().getProjectName())
                        .subject(dto.getProject().getProjectSubject())
                        .trustGrade(dto.getProject().getProjectTrust())
                        .user(tempUser)
                        .status(RECRUITING)
                        .crewNumber(dto.getProject().getProjectCrewNumber())
                        .startDate(dto.getProject().getProjectStartDate())
                        .endDate(dto.getProject().getProjectEndDate())
                        .build();

        Project savedProject = projectRepository.save(project);

        // board 생성
        Board board =
                Board.builder()
                        .title(dto.getBoard().getTitle())
                        .content(dto.getBoard().getContent())
                        .project(savedProject)
                        .user(tempUser)
                        .contact(dto.getBoard().getContent())
                        .build();

        Board savedBoard = boardRepository.save(board);

        // boardPosition 생성
        List<BoardPosition> boardPositionList = new ArrayList<>();
        for (Position position : dto.getBoard().getPositions()) {
            BoardPosition boardPosition = new BoardPosition(savedBoard, position);
            boardPositionRepository.save(boardPosition);
        }
        savedBoard.setPositions(boardPositionList);

        // response값 생성
        BoardCreateResponseDto boardCreateResponseDto = BoardCreateResponseDto.of(board);
        ProjectCreateResponseDto projectCreateResponseDto = ProjectCreateResponseDto.of(project);

        return new BoardProjectCreateResponseDto(boardCreateResponseDto, projectCreateResponseDto);
    }

    /**
     * 게시글, 프로젝트 업데이트
     * @param dto
     * @return
     */
    public BoardProjectUpdateResponseDto update(BoardProjectUpdateRequestDto dto) {
        Project project = projectRepository.findById(dto.getProject().getProjectId()).get();
        User tempUser = userRepository.findById(1L).get(); // 나중에 Security로 고쳐야 함.

        // project 생성
        Project newProject =
                Project.builder()
                        .id(project.getId())
                        .name(dto.getProject().getProjectName())
                        .subject(dto.getProject().getProjectSubject())
                        .trustGrade(dto.getProject().getProjectTrust())
                        .user(project.getUser())
                        .status(project.getStatus())
                        .crewNumber(dto.getProject().getProjectCrewNumber())
                        .startDate(dto.getProject().getProjectStartDate())
                        .endDate(dto.getProject().getProjectEndDate())
                        .build();

        Project savedProject = projectRepository.save(newProject);

        // board 생성
        Board board = boardRepository.findById(dto.getBoard().getBoardId()).get();

        Board newBoard =
                Board.builder()
                        .id(board.getId())
                        .title(dto.getBoard().getTitle())
                        .content(dto.getBoard().getContent())
                        .project(board.getProject())
                        .user(board.getUser())
                        .contact(dto.getBoard().getContent())
                        .build();

        Board savedBoard = boardRepository.save(newBoard);

        // position 받아서 다시 보드-포지션 연결
        List<BoardPosition> boardPositionList = new ArrayList<>();
        for (Position position : dto.getBoard().getPositions()) {
            BoardPosition boardPosition = new BoardPosition(savedBoard, position);
            boardPositionRepository.save(boardPosition);
            boardPositionList.add(boardPosition);
        }
        savedBoard.setPositions(boardPositionList);

        // response값 생성
        BoardUpdateResponseDto boardUpdateResponseDto = BoardUpdateResponseDto.of(board);
        ProjectUpdateResponseDto projectUpdateResponseDto = ProjectUpdateResponseDto.of(project);

        return new BoardProjectUpdateResponseDto(boardUpdateResponseDto, projectUpdateResponseDto);
    }

    /**
     * 게시글 삭제
     * @param boardId
     */
    public void delete(Long boardId){
        Board board = boardRepository.findById(boardId).get();
        boardRepository.delete(board);
    }
}
