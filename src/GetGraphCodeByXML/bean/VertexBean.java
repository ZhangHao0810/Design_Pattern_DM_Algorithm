package GetGraphCodeByXML.bean;
public class VertexBean { 
	//“v i j” 表示图的顶点i的label是j 
	/** 图的数据格式
	 *  t # 0 表示第0个图
		v 0 2
		v 1 2
		v 2 2
		v 3 2
		v 4 2
		e 0 1 2
		e 1 2 2
		e 2 3 2
		e 3 4 2
	 */
	private String vertexName;
	private String number;
	public String getVertexName() {
		return vertexName;
	}
	public void setVertexName(String vertexName) {
		this.vertexName = vertexName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((vertexName == null) ? 0 : vertexName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VertexBean other = (VertexBean) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (vertexName == null) {
			if (other.vertexName != null)
				return false;
		} else if (!vertexName.equals(other.vertexName))
			return false;
		return true;
	}
	
	
	
	
}