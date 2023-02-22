package com.l2m.model.global;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

import org.springframework.data.domain.PageRequest;

/**
 * pageable 및 pageRequest인터페이스등을 그대로 사용할 경우 여러 문제점이 있다.
 * 1) 쓸떄는 매우 편하지만 swagger이나 javadoc등에 pageable이 상속받은 모든 파라미터가 노출되는 점
 * 2) page가 0부터 시작해서 ui적으로 불편한 점
 * 3) 해당 pageable및 그 상속체에 setter가 제공되지 않는다는 점
 *
 * 이에 대한 부분으로 차라리 내가 필요한 파라미터만을 받아 setter만을 제공하고
 * 여기서 pageRequest를 만들어 내려주는 반환메서드를 작성하였다.
 */
@Getter
@Setter
public class PageModel {

  @Schema(description = "페이지번호 (기본값 1)", example = "1")
  @Positive(message = "페이지번호는 양수 값이어야 합니다.")
  protected int page;

  @Schema(description = "한 페이지당 사이즈 (기본값 10)", example = "10")
  @Positive(message = "페이지 사이즈는 양수 값이어야 합니다.")
  protected int size;

  public PageRequest makePageable() {
    //사용은 1로 하고 실제 pageable은 0이 시작이기 때문에 그에 맞춰준다.
    return PageRequest.of(this.page <= 0 ? 0 : this.page - 1, this.size);
  }
}
