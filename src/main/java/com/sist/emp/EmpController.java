package com.sist.emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileWriter;
import java.util.*;

import javax.annotation.PostConstruct;

import com.sist.dao.*;
import com.sist.r.RManager;

import java.text.*;

/**
 * @author sist
 *  1) DI
 *  ==============
 *  	1.Setter DI => p:~ , <propertiy name="" value="">
 *   	2. construct DI
 *   	3. method DI(
 *   	init-method(@PostConstruct): 객체 메모리 할당후에 자동 호출,
 *     destroy-method(@PreDestroy) : 객체 메모리 해제후에 자동 호출,
 *     factory-method : static메소드 호출
 *     )
 *     
 *     객체의 생명주기
 *     	1)메모리 할당 => Class.forName("")
 *     2)생성자에 값 주입
 *     3)Setter메소드 값 주입
 *     4)init-method 호출
 *     5)프로그래머 활용
 *     6)메모리 해제(destroy-method)
 *     7)메모리 회수
 *     
 *     
 *     R==> file(csv, text를 주로 이용한다.), mongodb둘중 하나의 것을 읽는다.
 */
@Controller
public class EmpController {
	@Autowired
	private EmpDAO dao;
	
	/*
	 * jsp에서	<%=application.getRealPath("/") %>로확인한 realpath에 사진을 놔야 새로고침안해도 그림이 바로 나온다.
	 * /home/sist/springDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/RAndJavaProject/ 
	 * 
	 * 톰캣플러그인을 따로 깔아야 이렇게 안해도 된다.
	 * */	
	@Autowired
	private RManager rm;
	
	@PostConstruct //메모리 할당하마자 호출 init-method
	public void init(){//csv파일 만들기
		try {
			List<EmpVO> eList=dao.empAllData();
			String data="empno, ename, job, hiredate, sal, deptno\n";
		
			for (EmpVO vo : eList) {
				data+=vo.getEmpno()+","
						+vo.getEname()+","
						+vo.getJob()+","
						+vo.getHiredate().toString()+","// Date형식일 경우 그냥 쓰면 된다.
						/*+new SimpleDateFormat("yyyy-MM-dd").format(vo.getHiredate())+","*/
						+vo.getSal()+","
						+vo.getDeptno()+"\n";
			}
			data=data.substring(0, data.lastIndexOf("\n"));
			FileWriter fw=new FileWriter("/home/sist/emp.csv");
			fw.write(data);
			fw.close();
			
			//DeptCSV파일 생성
			List<DeptVO> dList=dao.deptAllData();
			data="deptno,dname,loc\n";		
			for (DeptVO vo : dList) {
				data+=vo.getDeptno()+","
						+vo.getDname()+","
						+vo.getLoc()+"\n";					
			}
			data=data.substring(0, data.lastIndexOf("\n"));
			fw=new FileWriter("/home/sist/dept.csv"); //R에서도 join가능하다 inner와 outer모두
			fw.write(data);
			fw.close();
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@RequestMapping("main/list.do")
	public String main_list(Model model){
		
		List<EmpVO> eList=dao.empAllData();
		model.addAttribute("list", eList);
		rm.sawonSalGraph();//그림을 그려 경로에 저장
		//model.
		
		String data="empno, ename, job, hiredate, sal, deptno\n";
		
		/*for (EmpVO vo : eList) {
			data+=vo.getEmpno()+","
					+vo.getEname()+","
					+vo.getJob()+","
					+vo.getHiredate().toString()+","
					+vo.getSal()+","
					+vo.getDeptno()+"\n";
		}
		System.out.println(data);*/
		
		System.out.println("잘실행되나?");
		
		return "main/list";
	}
	
	@RequestMapping("main/graph.do")
	public String main_graph(String no){
		if (no==null) {
			no="1";
		}
		int i=Integer.parseInt(no);
		switch(i){
			case 1:break;
			case 2:break;
			case 3:break;
		
		}
		

		return "main/graph";
	}
}










