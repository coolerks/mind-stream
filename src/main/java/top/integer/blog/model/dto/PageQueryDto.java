package top.integer.blog.model.dto;

import com.mybatisflex.core.paginate.Page;
import lombok.ToString;

/**
 * 分页查询
 *
 * @author singx
 */
@ToString
public class PageQueryDto {
    private Integer pageNumber = 1;
    private Integer pageSize = 10;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber == null ? 1 : pageNumber;
        this.pageNumber = this.pageNumber <= 0 ? 1 : this.pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize == null ? 10 : pageSize;
        this.pageSize = this.pageSize > 100 ? 100 : this.pageSize;
    }

    public static <T> Page<T> toPage(PageQueryDto pageQueryDto, Class<T> clazz) {
        return new Page<>(pageQueryDto.getPageNumber(), pageQueryDto.getPageSize());
    }
}
