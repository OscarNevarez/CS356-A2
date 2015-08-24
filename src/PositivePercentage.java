
public class PositivePercentage implements Visitor {
	double  percentage;
	int count=0;
	@Override
	public void visit(Users node) {
		if(node instanceof IndividualUser){
			count+=((IndividualUser) node).getMessages().size();
		}
	}

}
