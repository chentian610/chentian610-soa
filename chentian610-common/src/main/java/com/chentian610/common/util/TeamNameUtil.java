package com.chentian610.common.util;

import com.chentian610.common.DictConstants;

import java.util.Date;

public class TeamNameUtil {
	
	//根据入学年份换算出班级名称
	public static String getClassname(String school_type, Integer enrollment_year, Integer class_num) {
		int year=DateUtil.getYearOfTerm(new Date());
		//当前学年减去入学年份+1，代表当前年级
		int grade=year-enrollment_year+1;
		switch (school_type) {
			case DictConstants.SCHOOL_JUNIOR :
				return getClassNameOfJunior(grade,class_num);
			case DictConstants.SCHOOL_MIDDLE : 
				return getClassNameOfMiddle(grade,class_num);
			case DictConstants.SCHOOL_HIGH : 
				return getClassNameOfHigh(grade,class_num);
			case DictConstants.SCHOOL_KINDERGARTEN : 
				return getClassNameOfKindergarten(grade,class_num);
			default:return "培训班（"+class_num+"）班";
		}
	}
		
		
	//根据入学年份换算年级名称
	public static String getGradename(String school_type, Integer enrollment_year) {
		int year=DateUtil.getYearOfTerm(new Date());
		//当前学年减去入学年份+1，代表当前年级
		int grade=year-enrollment_year+1;
		switch (school_type) {
			case DictConstants.SCHOOL_JUNIOR : 
				return getGradeNameOfJunior(grade);
			case DictConstants.SCHOOL_MIDDLE : 
				return getGradeNameOfMiddle(grade);
			case DictConstants.SCHOOL_HIGH : 
				return getGradeNameOfHigh(grade);
			case DictConstants.SCHOOL_KINDERGARTEN : 
				return getGradeNameOfKindergarten(grade);
			default:return "培训班";
		}
	}
	
	/**
	 * 生成幼儿园班级名称
	 * @param grade
	 * @param class_num
	 * @return
	 */
	private static String getClassNameOfKindergarten(int grade, Integer class_num) {
		switch (grade) {
			case 1 : return "小班（"+class_num+"）班";
			case 2 : return "中班（"+class_num+"）班";
			case 3 : return "大班（"+class_num+"）班";
			case 4 : return "学前班（"+class_num+"）班";
			default: return "幼儿班（"+class_num+"）班";
		}
	}


	/**
	 * 生成高中班级名称
	 * @param grade
	 * @param class_num
	 * @return
	 */
	private static String getClassNameOfHigh(int grade, Integer class_num) {
		switch (grade) {
			case 1 : return "高一（"+class_num+"）班";
			case 2 : return "高二（"+class_num+"）班";
			case 3 : return "高三（"+class_num+"）班";
			default: return "其他年级（"+class_num+"）班";
		}
	}


	/**
	 * 生成初中班级名称
	 * @param grade
	 * @param class_num
	 * @return
	 */
	private static String getClassNameOfMiddle(int grade, Integer class_num) {
		switch (grade) {
			case 1 : return "初一（"+class_num+"）班";
			case 2 : return "初二（"+class_num+"）班";
			case 3 : return "初三（"+class_num+"）班";
			default: return "其他年级（"+class_num+"）班";
		}
	}



	/**
	 * 生成小学班级名称
	 * @param grade
	 * @param class_num
	 * @return
	 */
	private static String getClassNameOfJunior(int grade, Integer class_num) {
		switch (grade) {
			case 1 : return "一年级（"+class_num+"）班";
			case 2 : return "二年级（"+class_num+"）班";
			case 3 : return "三年级（"+class_num+"）班";
			case 4 : return "四年级（"+class_num+"）班";
			case 5 : return "五年级（"+class_num+"）班";
			case 6 : return "六年级（"+class_num+"）班";
			case 7 : return "七年级（"+class_num+"）班";
			case 8 : return "八年级（"+class_num+"）班";
			case 9 : return "九年级（"+class_num+"）班";
			default: return "其他年级（"+class_num+"）班";
		}
	}
	
	/**
	 * 生成幼儿园班级名称
	 * @param grade
	 * @param class_num
	 * @return
	 */
	private static String getGradeNameOfKindergarten(int grade) {
		switch (grade) {
			case 1 : return "小班";
			case 2 : return "中班";
			case 3 : return "大班";
			case 4 : return "学前班";
			default: return "幼儿班";
		}
	}


	/**
	 * 生成高中班级名称
	 * @param grade
	 * @param class_num
	 * @return
	 */
	private static String getGradeNameOfHigh(int grade) {
		switch (grade) {
			case 1 : return "高一年级";
			case 2 : return "高二年级";
			case 3 : return "高三年级";
			default: return "其他年级";
		}
	}


	/**
	 * 生成初中班级名称
	 * @param grade
	 * @param class_num
	 * @return
	 */
	private static String getGradeNameOfMiddle(int grade) {
		switch (grade) {
		case 1 : return "初一年级";
		case 2 : return "初二年级";
		case 3 : return "初三年级";
		default: return "其他年级";
		}
	}



	/**
	 * 生成小学班级名称
	 * @param grade
	 * @param class_num
	 * @return
	 */
	private static String getGradeNameOfJunior(int grade) {
		switch (grade) {
			case 1 : return "一年级";
			case 2 : return "二年级";
			case 3 : return "三年级";
			case 4 : return "四年级";
			case 5 : return "五年级";
			case 6 : return "六年级";
			case 7 : return "七年级";
			case 8 : return "八年级";
			case 9 : return "九年级";
			default: return "其他年级";
		}
	}
}
