package com.atguigu.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {

	/**
	 * �� Map�� �� ֵ ע �� �� �� Ӧ �� JavaBean�� �� �� ��
	 * 
	 * @param <T>
	 * @param value
	 * @param bean
	 * @return
	 */
	public static <T> T copyParamToBean(Map value, T bean) {
		try {
			System.out.println("ע��֮ǰ" + bean);
			BeanUtils.populate(bean, value);
			System.out.println("ע��֮��" + bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * ���ַ���ת��Ϊint��������
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
