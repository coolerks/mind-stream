package top.integer.blog.model.vo;

import com.mybatisflex.core.paginate.Page;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class PageVo<T> {
    /**
     * 是否为首页
     */
    private final boolean isFirstPage;
    /**
     * 是否为最后一页
     */
    private final boolean isLastPage;
    /**
     * 当前页
     */
    private final long pageNumber;
    /**
     * 总的数据量
     */
    private final long total;
    /**
     * 单页大小
     */
    private final long pageSize;
    /**
     * 数据
     */
    private final List<T> data;
    /**
     * 总页数
     */
    private final long totalPages;

    public static <T> PageVo<T> of(Page<T> page) {
        return new PageVo<>(page.getRecords(), page.getPageNumber(), page.getPageSize(), page.getTotalRow());
    }

    public PageVo(List<T> data, long pageNumber, long pageSize, long total) {
        this.data = data;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.total = total;
        this.isFirstPage = pageNumber <= 1;
        this.totalPages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        this.isLastPage = this.pageNumber >= this.totalPages;
    }

}
