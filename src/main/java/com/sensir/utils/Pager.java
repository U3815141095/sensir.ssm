package com.sensir.utils;

import java.util.List;

/**
 * 用于分页的工具类
 *
 * @author Leorain
 */
public class Pager<T> {

    private List<T> list; //对象记录结果集
    private int total = 0; // 总记录数
    private int limit = 10; // 每页显示记录数
    private int pages = 1; // 总页数
    private int pageNumber = 1; // 当前页
    private int offSet;//limit 第几行开始查

    private boolean isFirstPage = false;        //是否为第一页
    private boolean isLastPage = false;         //是否为最后一页
    private boolean hasPreviousPage = false;   //是否有前一页
    private boolean hasNextPage = false;       //是否有下一页

//    private int navigatePages = 8; //导航页码数
//    private int[] navigatePageNumbers;  //所有导航页号

    public Pager(int pageNumber) {
        this.pageNumber = pageNumber;

        init(10);

        if (pageNumber == 1) {//分页起始行数
            this.offSet = 0;
        } else {
            this.offSet = (pageNumber - 1) * this.limit;
        }
    }

    public Pager(int pageNumber, int limit) {
        this.pageNumber = pageNumber;

        init(limit);

        if (pageNumber == 1) {//分页起始行数
            this.offSet = 0;
        } else {
            this.offSet = (pageNumber - 1) *this.limit;
        }
    }

    private void init(int limit) {
        //设置基本参数
        this.limit = limit;
    }

    /**
     * 设置总记录数
     *
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;//总记录数

        if (this.total == 0)
            this.pages = 1;
        else if (this.total % limit == 0) {
            this.pages = (int) (this.total / limit);
        } else {
            this.pages = (int) (this.total / limit + 1);
        }
        //基本参数设定之后进行导航页面的计算
//        calcNavigatePageNumbers();
        //以及页面边界的判定
        judgePageBoudary();
    }

    /**
     * 计算导航页
     */
//    private void calcNavigatePageNumbers() {
//        //当总页数小于或等于导航页码数时
//        if (pages <= navigatePages) {
//            navigatePageNumbers = new int[pages];
//            for (int i = 0; i < pages; i++) {
//                navigatePageNumbers[i] = i + 1;
//            }
//        } else { //当总页数大于导航页码数时
//            navigatePageNumbers = new int[navigatePages];
//            int startNum = pageNumber - navigatePages / 2;
//            int endNum = pageNumber + navigatePages / 2;
//
//            if (startNum < 1) {
//                startNum = 1;
//                //(最前navPageCount页
//                for (int i = 0; i < navigatePages; i++) {
//                    navigatePageNumbers[i] = startNum++;
//                }
//            } else if (endNum > pages) {
//                endNum = pages;
//                //最后navPageCount页
//                for (int i = navigatePages - 1; i >= 0; i--) {
//                    navigatePageNumbers[i] = endNum--;
//                }
//            } else {
//                //所有中间页
//                for (int i = 0; i < navigatePages; i++) {
//                    navigatePageNumbers[i] = startNum++;
//                }
//            }
//        }
//    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = pageNumber == 1;
        isLastPage = pageNumber == pages && pageNumber != 1;
        hasPreviousPage = pageNumber != 1;
        hasNextPage = pageNumber != pages;
    }


    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 得到当前页的内容
     *
     * @return {List}
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 得到记录总数
     *
     * @return {int}
     */
    public int getTotal() {
        return total;
    }

    /**
     * 得到每页显示多少条记录
     *
     * @return {int}
     */
    public int getLimit() {
        return limit;
    }

    /**
     * 得到页面总数
     *
     * @return {int}
     */
    public int getPages() {
        return pages;
    }


    /**
     * 得到当前页号
     *
     * @return {int}
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * 得到所有导航页号
     *
     * @return {int[]}
     */
//    public int[] getNavigatePageNumbers() {
//        return navigatePageNumbers;
//    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }


    /**
     * 是否有前一页
     *
     * @return
     */
    public boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    /**
     * 获取前一页记录
     *
     * @return
     */
    public int getPreviousLink() {
        if (this.hasPreviousPage) {//如果存在前一页
            return getPageNumber() - 1;//当前页 减去 一页
        } else { //不存在
            return getPageNumber(); //不存在前一页，获取当前页
        }
    }

    /**
     * 是否有下一页
     *
     * @return
     */
    public boolean getHasNextPage() {
        return hasNextPage;
    }

    /**
     * 获取下一页记录
     *
     * @return
     */
    public int getNextLink() {
        if (this.hasNextPage) {
            return getPageNumber() + 1;
        } else {
            return getPageNumber();
        }
    }

    /**
     * mysql limit 查询起始行数
     *
     * @return
     */
    public int getOffSet() {
        return this.offSet;
    }

//    public int

    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("[")
                .append("total=").append(total)
                .append(",pages=").append(pages)
                .append(",pageNumber=").append(pageNumber)
                .append(",limit=").append(limit)
                .append(",isFirstPage=").append(isFirstPage)
                .append(",isLastPage=").append(isLastPage)
                .append(",hasPreviousPage=").append(hasPreviousPage)
                .append(",hasNextPage=").append(hasNextPage)
                .append(",navigatePageNumbers=");
//        if (navigatePageNumbers.length > 0) str.append(navigatePageNumbers[0]);
//        for (int i = 1; i < navigatePageNumbers.length; i++) {
//            str.append(" ").append(navigatePageNumbers[i]);
//        }
        //sb+=",list="+list;
        str.append("]");
        return str.toString();
    }
}