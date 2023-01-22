package assessments;

public class User {

	private int id2;
	private String name2;
	private String date2;
	private String address2;
	private long phone2;
	private String userrole2;

	public User(int id2, String name2, String date2, String address2, long phone2, String userrole2) {
		this.id2 = id2;
		this.name2 = name2;
		this.date2 = date2;
		this.address2 = address2;
		this.phone2 = phone2;
		this.userrole2 = userrole2;

	}

	public int getId() {
		return id2;

	}

	public String getName() {
		return name2;

	}

	public String getDate() {
		return date2;

	}

	public String getAddress() {
		return address2;

	}

	public long getPhone() {
		return phone2;

	}

	public String getUserRole() {
		return userrole2;

	}

}
