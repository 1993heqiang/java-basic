package org.rookie.test;

public class Employee{
	public String name;
	private int age;

	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public Employee() {
		name = "hehe";
		age = 18;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Employee Class is already destroyed!");
	}
}
