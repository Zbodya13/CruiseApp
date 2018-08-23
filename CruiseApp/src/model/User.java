package model;

public class User 
{

	private String login;
	private String password;
	private String name;
	private String surname;
	private String telefon;
	private String role;
	private int cash;
	
	public User() 
	{	}	
	
	public User(String login, String password, String name, String surname, String telefon, String role, int cash) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.telefon = telefon;
		this.role = role;
		this.cash = cash;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	

}
