package com.lpp.kiven.util;

public class HtmlUtil {
	private static final String ALL_PAGE_COUNT	= "<span class=\"pg-txt\">共 %s 条记录 &nbsp;&nbsp;%s／%s页</span>";
	private static final String UL_STRING="<ul class=\"ui-page mgt-sm\" f=\"page\">";
	private static final String INDEX_SPAN	    = " <a class=\"disabled\">首页</a> ";
	/*private static final String INDEX_SPAN="<div style=\"background-color: #ffffff;padding: 10px\" class=\"pg-a clearfix mgl-5 js_page\">";*/
	/**
	 * PREVIOUS_PAGING_A:上一页a标签
	 * 
	 */
	private static final String PREVIOUS_PAGING_A     = "<a href=\"javascript:void(0);\" class=\"openLoading\" currentPage=\"%s\" id=\"previousPaging\">上一页</a> ";
	/**
	 * PREVIOUS_PAGING_A:上一页span标签
	 * 
	 */
	private static final String PREVIOUS_PAGING_SPAN  = " <a class=\"disabled\">上一页</a> ";
	/**
	 * NEXT_PAGING_A:下一页A标签
	 * 
	 */
	private static final String NEXT_PAGING_A	 = " <a href=\"javascript:void(0);\" class=\"openLoading\" currentPage=\"%s\" id=\"nextPaging\">下一页</a> ";
	/**
	 * NEXT_PAGING_A:下一页span标签
	 * 
	 */
	private static final String NEXT_PAGING_SPAN      = " <a class=\"disabled\">下一页</a> ";
	/**
	 * PAG_NUM_A:分页数字a标签
	 * 
	 */
	private static final String PAGING_NUM_A	  = " <a class=\"aPagingClass openLoading\" currentPage=\"%s\" href=\"javascript:void(0);\">%s</a> ";
	/**
	 * PAGING_NUM_SPAN:分页数字span
	 * 
	 */
	private static final String PAGING_NUM_SPAN       = " <a class=\"active\">%s</a> ";
	private static final String APOSTROPHE_SPAN       = " <span>...</span> ";
	private static final String INDEX_A	       = " <a class=\"aPagingClass openLoading\" currentPage=\"1\">首页</a> ";
	private static final String END_SPAN	      = " <a class=\"disabled\">尾页</a> ";
	private static final String END_A		 = " <a class=\"aPagingClass openLoading\" currentPage=\"%s\">尾页</a> ";
	
	/**
	 * @Title: toPaginHtml
	 * @Description: 构造分页按钮html
	 * @param count 数据总共长度
	 * @param eachPageNum 每页长度
	 * @param pageIndex 当前第几页
	 * @return html
	 */
	public static String toPaginHtml(double count, double eachPageNum,
			int pageIndex) {
		StringBuilder stringBuilder = new StringBuilder();
		// 总共页数
		double pages = count / eachPageNum;
		int size = getPageSize(pages);
		// 只有一页,或则总长度为0
		stringBuilder.append(String.format(ALL_PAGE_COUNT,(new Double(count)).intValue(), pageIndex, size));
		if (1 >= size && pageIndex == size) {
			stringBuilder.append(INDEX_SPAN);
			stringBuilder.append(PREVIOUS_PAGING_SPAN);
			stringBuilder.append(String.format(PAGING_NUM_SPAN, 1));
			stringBuilder.append(NEXT_PAGING_SPAN);
			stringBuilder.append(END_SPAN);
		} else {

			// 第一页
			if (1 == pageIndex) {
				stringBuilder.append(INDEX_SPAN);
				stringBuilder.append(PREVIOUS_PAGING_SPAN);
			} else {
				stringBuilder.append(INDEX_A);
				stringBuilder.append(String
						.format(PREVIOUS_PAGING_A, pageIndex));
			}
			// 如果总页数大于5,并且当前页数不是第一页
			if (size > 5) {
				int end = 1;
				// 如果不是第一页，则需要设置当前页前面2位页数
				if (1 != pageIndex) {
					// 默认前面有两位
					int len = 2;
					// 算出当前页面前面实际有几位
					if (2 > (size - pageIndex)) {
						len = 4 - (size - pageIndex);
					}
					// 循环将当前页面前面几位拼接
					for (int i = len; i > 0; i--) {
						if (0 < pageIndex - i) {
							stringBuilder.append(String.format(PAGING_NUM_A,
									(pageIndex - i), (pageIndex - i)));
							end++;
						}
					}
				}
				// 设置当前页数
				stringBuilder.append(String.format(PAGING_NUM_SPAN, pageIndex));
				// 是否设置后面省略号
				boolean boo = true;
				// 如果不是最后一页
				if (size != pageIndex) {
					// 循环5减去当前页数前面的页数
					for (int i = 1; i <= 5 - end; i++) {
						stringBuilder.append(String.format(PAGING_NUM_A,
								(pageIndex + i), (pageIndex + i)));
						// 如果是最后一页
						if (size == (pageIndex + i)) {
							// 最后一页不设置省略号
							boo = false;
							break;
						}
					}
				} else {
					// 不设置省略号
					boo = false;
				}
				// 添加省略号
				if (boo && pageIndex < size) {
					stringBuilder.append(APOSTROPHE_SPAN);
				}
			}
			// 总页数小于5
			else {
				for (int i = 1; i <= size; i++) {
					if (i == pageIndex) {
						stringBuilder.append(String.format(PAGING_NUM_SPAN, i));
					} else {
						stringBuilder.append(String.format(PAGING_NUM_A, i, i));
					}
				}
				if (pageIndex > size) {
					stringBuilder.append(String.format(PAGING_NUM_SPAN,
							pageIndex));
				}
			}
			if (size <= pageIndex) {
				stringBuilder.append(NEXT_PAGING_SPAN);
				stringBuilder.append(END_SPAN);
			} else {
				stringBuilder.append(String.format(NEXT_PAGING_A, pageIndex));
				stringBuilder.append(String.format(END_A, size));
			}

		}
		return stringBuilder.toString();
	}
	
	public static int getPageSize(double pageSize)
	{
		int size = 0;
		// 整数
		if(String.valueOf(pageSize).endsWith(".0"))
		{
			size = (int) pageSize;
		}
		else
		{
			// 非整数需要加1
			size = (int) pageSize + 1;
		}
		return size;
	}
}
