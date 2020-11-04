package com.atguigu.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {

	/**
	 * 把 Map中 的 值 注 入 到 对 应 的 JavaBean属 性 中 。
	 * 
	 * @param <T>
	 * @param value
	 * @param bean
	 * @return
	 */
	public static <T> T copyParamToBean(Map value, T bean) {
		try {
			System.out.println("注入之前" + bean);
			BeanUtils.populate(bean, value);
			System.out.println("注入之后" + bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * 将字符串转换为int类型数据
	 * @param strInt
	 * @param defaultValue
	 * @return
	 */
	public static int parseInt(String strInt, int defaultValue) {

		try {
			return Integer.parseInt(strInt);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return defaultValue;
	}
}
