package com.sist.r;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.stereotype.Component;

@Component
public class RManager {
	public void sawonSalGraph(){
		try {
			//R server => Connection
			RConnection rc=new RConnection();
			
			//voidEval() 명령문 전송
			//eval() 데이터값 받을때
			rc.voidEval("data<-read.csv(\"/home/sist/emp.csv\", header=T, sep=\",\" )");//파일, 몽고디비 읽는다.
																							//헤더가 있고 ,로 구분된다. header를 안주면 맨위가 1번이 되어버린다.
			//read.csv("path", header) read.table("path") readLines("path")
			//                            타이틀
			//rc.voidEval("barplot(data$sal, names.arg=data$ename, col=rainbow(15))");//sysout에해당, 저장할곳을 미리 저장해줘야 한다.
			//realpath에붙여야 바로 불러올수 있다.
			rc.voidEval("png(\"/home/sist/springDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/RAndJavaProject/main/sal.png\", width=700, height=500)");//그림파일로 저장
			rc.voidEval("barplot(data$sal, names.arg=data$ename, col=rainbow(15))");
			rc.voidEval("dev.off()");
			rc.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
}
