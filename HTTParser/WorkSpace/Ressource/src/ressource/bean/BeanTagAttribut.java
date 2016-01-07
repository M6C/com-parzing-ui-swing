package ressource.bean;

public class BeanTagAttribut {

	private String name;
	private String value;

	public BeanTagAttribut(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public BeanTagAttribut(String text) {
		if (text!=null && !text.equals("")) {
			int i = text.indexOf('=');
			if (i>0) {
				this.name = text.substring(0, i);
				this.value = text.substring(i+1);
			}
			else {
				this.name = text;
			}
		}
	}

	public boolean equals(BeanTagAttribut obj) {
		return getName().equalsIgnoreCase(obj.getName()) &&
		((getValue()==null && obj.getValue()==null) ||
			(getValue()!=null && getValue().equalsIgnoreCase(obj.getValue())));
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
