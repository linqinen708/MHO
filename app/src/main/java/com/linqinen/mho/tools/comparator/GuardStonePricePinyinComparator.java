package com.linqinen.mho.tools.comparator;

import com.linqinen.mho.bean.GuardStonePrice;

import java.util.Comparator;

/**
 * 根据首字母排序联系人
 * */
public class GuardStonePricePinyinComparator implements Comparator<GuardStonePrice> {

	@Override
	public int compare(GuardStonePrice lhs, GuardStonePrice rhs) {
		return sort(lhs, rhs);
	}

	private int sort(GuardStonePrice lhs, GuardStonePrice rhs) {
		// 获取ascii值
		int lhs_ascii = lhs.getFirstPinYin().toUpperCase().charAt(0);
		int rhs_ascii = rhs.getFirstPinYin().toUpperCase().charAt(0);
		// 判断若不是字母，则排在字母之后
		if (lhs_ascii < 65 || lhs_ascii > 90)
			return 1;
		else if (rhs_ascii < 65 || rhs_ascii > 90)
			return -1;
		else
			return lhs.getPinYin().toUpperCase().compareTo(rhs.getPinYin().toUpperCase());
	}

}
