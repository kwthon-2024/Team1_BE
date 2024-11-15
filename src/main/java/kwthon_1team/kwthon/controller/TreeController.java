import kwthon_1team.kwthon.common.BaseErrorResponse;
import kwthon_1team.kwthon.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tree")
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<TreeResponseDto> getTreeInfo(@PathVariable Long memberId) {
        try {
            TreeResponseDto responseDto = treeService.getTreeInfo(memberId);
            return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getStatus()));
        } catch (Exception e) {
            TreeResponseDto errorResponse = new TreeResponseDto(500, "서버 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}