package com.sist.dao;


import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface EmpMapper {
	@Select("Select empno, ename, job, hiredate, sal, deptno"
			+ " From emp")
	public List<EmpVO> empAllData();
	
	@Select("Select deptno, dname, loc From dept")
	public List<DeptVO> deptAllData();
}
