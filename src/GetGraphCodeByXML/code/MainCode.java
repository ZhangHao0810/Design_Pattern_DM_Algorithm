package GetGraphCodeByXML.code;

import GetGraphCodeByXML.bean.EdgeBean;
import GetGraphCodeByXML.bean.VertexBean;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MainCode {

	/*
	 * author:ZhangHao time:2020年1月20日12:20:55
	 * 
	 * code for : get the 3 column data,as the Ullmann algorithm input.
	 * 
	 * Thinking:
	 * 
	 * 1.get all target and source words,put into a Set as the Vertex, number is
	 * list num ,weight is 0;
	 * 
	 * 2.get all target and source words,identify these words from the List,get
	 * the list num as edge<i,j>
	 * 
	 * edge weight : Generalization 2, Association 3 , Dependency5 , Realization
	 * 7 , Aggregation 11 , Composite 13 ;
	 * 
	 * Through this method ,we can get the 3 column type graph code.
	 * 
	 * 
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<VertexBean, Integer> vertexs = new LinkedHashMap();

		List<EdgeBean> edges = new ArrayList<>();

		Integer count = 0;

		Integer tempSource = 0;
		Integer tempTarget = 0;

//		String filePath = "Junit_P3.txt";
		
		String filePath = "src/GetGraphCodeByXML/Flyweight.txt";

		try {

			// System.out.println("213123");
			BufferedReader q_br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));

			String lineData = q_br.readLine();

			// lineData = q_br.readLine();
			// lineData = q_br.readLine();
			// lineData = q_br.readLine();
			// System.out.println(lineData.indexOf("source"));
			// //StringObject.indexOf("substring");统计的是光标从string 第一个字符前开始
			// 静止时为0,光标定位到这个字符前移动的次数.

			if (lineData.startsWith("<ralations>")) {
				lineData = q_br.readLine();
			}

			/**
			 * 1.获取所有的顶点信息,封装并存到Map中 信息格式: v vNumber 0
			 */
			while (lineData.startsWith("    <ralation name=\"")) { // 定位到含有关系信息的行.

				int flagSource = 0;
				int flagTarget = 0;

				// 1. 获取 顶点位置信息.
				int indexD = lineData.indexOf("source=") + 9;

				// 2.获取边的位置信息.
				int indexEdge1 = lineData.indexOf(" \" target=") + 12;
				int indexEdge2 = lineData.indexOf("\" >");

				// 得到Java类名 (顶点).
				String source = lineData.substring(indexD, indexEdge1 - 12);
				String target = lineData.substring(indexEdge1, indexEdge2);

				// System.out.println(source);
				// System.out.println(target);

				// 判断顶点是否已存在
				for (VertexBean key : vertexs.keySet()) {
					// System.out.println("Key="+key+"\tvalue="+vertexs.get(key));
					if (source.equals(key.getVertexName())) {
						flagSource = 1;
					}
				}

				// 要把对集合修改的操作放在迭代之外.否则会报 ConcurrentModificationException
				if (flagSource == 0) {
					// 将顶点封装
					VertexBean vertex = new VertexBean();
					vertex.setVertexName(source);
					vertex.setNumber(count.toString());

					tempSource = count;
					count++;

					// 添加进集合
					vertexs.put(vertex, 0);
				}

				// 判断顶点是否已存在
				for (VertexBean key : vertexs.keySet()) {
					if (target.equals(key.getVertexName())) {
						flagTarget = 1;
					}
				}

				if (flagTarget == 0) {
					// 将顶点封装
					VertexBean vertex = new VertexBean();
					vertex.setVertexName(target);
					vertex.setNumber(count.toString());

					tempTarget = count;
					count++;

					// 添加进集合
					vertexs.put(vertex, 0);
				}

				/**
				 * 得到所有的边,输出到边Map中. 信息格式: e sourceNum targetNum weight
				 */

				// 获得关系位置信息
				int indexRelation = lineData.indexOf("name") + 7;
				// 得到关系
				String relation = lineData.substring(indexRelation, indexD - 12);

				EdgeBean edge = new EdgeBean();
				edge.setSourceV(tempSource.toString());
				edge.setTargetV(tempTarget.toString());

				Integer value = 0;

				// * edge weight : Generalization 2, Association 3 ,
				// * Dependency5 , Realization 7 , Aggregation 11 , Composite 13
				// ;

				switch (relation) {
				case "Generalization":
					value = 2;
					break;
				case "Association":
					value = 3;
					break;
				case "Dependency":
					value = 5;
					break;
				case "Realisation":
					value = 7;
					break;
				case "Aggregation":
					value = 11;
					break;
				case "Composite":
					value = 13;
					break;
				default:
					break;
				}
				edge.setWeight(value.toString());

				edges.add(edge);

				lineData = q_br.readLine();

			}

			System.out.println("t # 0");

			/**
			 * 将得到的数据格式化输出到文件中!!!
			 */

			if (lineData.startsWith("</ralations> ")) { // 数据读到末尾,输出文件,退出.

				// 获得合理的顶点数据
				for (VertexBean key : vertexs.keySet()) {
//					System.out.println(key.getVertexName() + " " + key.getNumber() + " " + vertexs.get(key));
					System.out.println("v " + key.getNumber() + " " + vertexs.get(key));
				}

				// 获得合理 边 的数据
				Iterator<EdgeBean> iter = edges.iterator();
				while (iter.hasNext()) {
					EdgeBean edgeBean = iter.next();
					System.out.println(
							"e " + edgeBean.getSourceV() + " " + edgeBean.getTargetV() + " " + edgeBean.getWeight());
				}
				
				System.out.println("t # -1");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
