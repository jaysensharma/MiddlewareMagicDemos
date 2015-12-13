package beans;

public class MathBean {
     private String firstNo="1000";
     private String secondNo="2000";

	public String getFirstNo() {
		return firstNo;
	}

	public void setFirstNo(String firstNo) {
		this.firstNo = firstNo;
	}

	public String getSecondNo() {
		return secondNo;
	}

	public void setSecondNo(String secondNo) {
		this.secondNo = secondNo;
	}
 
    public String addTwoNumbers(String firstNo, String secondNo) {
        System.out.println("[MathBean] addTwoNumbers("+firstNo+","+secondNo+") invoked.");
        int sum = Integer.parseInt(firstNo) + Integer.parseInt(secondNo);
        String result=    "<HTML>" +
                          "   <HEAD><TITLE> Add Numbers Camel Servlet test </TITLE></HEAD>" +
                          "   <BODY>" +
                          "      <H2> [MathBean] Sum of " +firstNo+" And "+secondNo+ " is = " + sum + "</H2>" + 
                          "   </BODY>" +
                          "</HTML>";
            return result;
        }
}

