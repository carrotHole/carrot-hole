package com.carrothole.carrot.entity.vo;

import com.carrothole.carrot.entity.table.AuDeptTableDef;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryOrderBy;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.util.LambdaGetter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:  <br>
 * Date: 2024/9/2 13:46 <br>
 *
 * @author moon
 * @since
 */
@Data
@NoArgsConstructor
public class PageVO implements Serializable {

    /**
     * 页码,第一页为1
     */
    @Schema(description = "页码,第一页为1")
    private Long pageNumber = 1L;

    /**
     * 页大小,默认10
     */
    @Schema(description = "页大小,默认10")
    private Long pageSize = 10L;

    /**
     * 排序
     */
    private List<Order> orders = new ArrayList<>(0);

    /**
     * 获取分页起始位置
     *
     * @return 起始位置
     */
    public long getPageStart() {
        return (this.getPageNumber() - 1) * this.pageSize;
    }

    /**
     * 获取分页结束位置
     *
     * @return 结束位置
     */
    public Long getPageEnd() {
        return this.getPageNumber() * this.pageSize;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * 排序对象
     */
    @Data
    @NoArgsConstructor
    public static class Order {
        private String field;
        /**
         * 排序 默认降序
         */
        private Boolean desc = true;

    }

    public <T> Page<T> buildPage() {
        Page<T> tPage = new Page<>(this.getPageNumber(), this.getPageSize());
        tPage.setOptimizeCountQuery(false);
        return tPage;
    }


    public <T> QueryWrapper appendOrderBy(QueryWrapper queryWrapper) {
        return appendOrderBy(queryWrapper, null);
    }

    /**
     * 添加排序字段,如果没有传入排序字段,则使用默认的排序字段
     *
     * @param queryWrapper QueryWrapper
     * @param queryOrderBys 排序
     * @return QueryWrapper
     */
    public <T> QueryWrapper appendOrderBy(QueryWrapper queryWrapper, QueryOrderBy... queryOrderBys) {
        List<Order> orderList = this.getOrders();
        if (orderList.isEmpty()) {
            if (queryOrderBys == null) {
                return queryWrapper;
            }
            return queryWrapper.orderBy(queryOrderBys);
        }
        QueryOrderBy[] orderByList = orderList.stream()
                .map(each -> new QueryOrderBy(new QueryColumn(each.getField()), each.getDesc()? "desc" : "asc"))
                .collect(Collectors.toSet()).toArray(new QueryOrderBy[0]);
        queryWrapper.orderBy(orderByList);

        return queryWrapper;
    }

}
